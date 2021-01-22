package info.cacilhas.ncurses

import info.cacilhas.ncurses.lowlevel.WINDOW

import scala.collection.mutable.{Map => MutableMap}
import scala.concurrent.duration.Duration
import scala.scalanative.libc.stdio.FILE
import scala.scalanative.unsafe.{CChar, CString, Ptr, Zone, fromCString, stackalloc}
import scala.scalanative.unsigned.UnsignedRichLong

final class Window private(private val win: Ptr[WINDOW]) extends AutoCloseable {

  type callback = (Window, Any) => Int

  assert(win != null, "could not initialise window")

  def attron(attr: Int, enable: Boolean = true): Int = {
    if (enable) nassert(lowlevel wattron  (win, attr))
    else        nassert(lowlevel wattroff (win, attr))
  }.toTry.get

  def attroff(attr: Int): Unit = attron(attr, enable = false)

  def autorefresch(enable: Boolean): Int = nassert(lowlevel immedok (win, enable)).toTry.get

  def box(horizontal: Char, vertical: Char): Int =
    nassert(lowlevel box (win, vertical.toLong.toULong, horizontal.toLong.toULong)).toTry.get

  def chgat(idx: Int, attr: Char, color: Color): Int =
    nassert(lowlevel wchgat (win, idx, attr.toLong.toULong, color.pair, null)).toTry.get

  def clear: Int = nassert(lowlevel wclear win).toTry.get

  def clearok: Int = clearok(true)

  def clearok(enable: Boolean): Int = nassert(lowlevel clearok (win, enable)).toTry.get

  def close(): Unit = lowlevel delwin win

  def crltobot: Int = nassert(lowlevel wcrltobot win).toTry.get

  def crltoeol: Int = nassert(lowlevel wcrltoeol win).toTry.get

  def delay(enable: Boolean): Int = nassert(lowlevel nodelay (win, !enable)).toTry.get

  def delete: Int = nassert(lowlevel wdelch win).toTry.get

  def delete(x: Int, y: Int): Int = nassert(lowlevel mvwdelch (win, y, x)).toTry.get

  def deleteln: Int = nassert(lowlevel wdeleteln win).toTry.get

  def erase: Int = nassert(lowlevel werase win).toTry.get

  def getbkgd: Char = lowlevel.getbkgd(win).toChar

  def getch(x: Int, y: Int): Char = lowlevel.mvwgetch(win, y, x).toChar

  def getstr(x: Int, y: Int, len: Int): String = Zone { implicit zone: Zone =>
    val str: CString = stackalloc[CChar]((len + 1).toUInt)
    nassert(lowlevel mvwgetnstr (win, y, x, str, len)).toTry.get
    fromCString(str)
  }

  def hardwareInsertDelete(enable: Boolean): Int = nassert(lowlevel idlok (win, enable)).toTry.get

  def hline(ch: Char, x: Int, y: Int, width: Int): Int =
    nassert(lowlevel mvwhline (win, y, x, ch.toLong.toULong, width)).toTry.get

  def insdelln(lines: Int): Int = nassert(lowlevel winsdelln (win, lines)).toTry.get

  def insertln: Int = nassert(lowlevel winsertln win).toTry.get

  def instr(x: Int, y: Int, len: Int): String = Zone { implicit zone: Zone =>
    val str: CString = stackalloc[CChar]((len + 1).toUInt)
    nassert(lowlevel mvwinnstr (win, y, x, str, len)).toTry.get
    fromCString(str)
  }

  def linetouched(line: Int): Boolean = lowlevel is_linetouched (win, line)

  def vline(ch: Char, x: Int, y: Int, height: Int): Int =
    nassert(lowlevel mvwvline (win, y, x, ch.toLong.toULong, height)).toTry.get

  def keypad(enable: Boolean): Int = nassert(lowlevel keypad (win, enable)).toTry.get

  def overlay(dest: Window): Int = nassert(lowlevel overlay (this.win, dest.win)).toTry.get

  def print(x: Int, y: Int)(text: String): Seq[Int] = text.zipWithIndex map { case ch -> i =>
    nassert(lowlevel mvwaddch (win, y,  x+i, ch.toLong.toULong)).toTry.get
  }

  def put(fp: Ptr[FILE]): Int = nassert(lowlevel putwin (win, fp)).toTry.get

  def redraw: Int = nassert(lowlevel redrawwin win).toTry.get

  def refresh: Int = nassert(lowlevel wrefresh win).toTry.get

  def scroll(lines: Int): Int = nassert(lowlevel wscrl (win, lines)).toTry.get

  def scrollok(enable: Boolean): Int =
    if (enable) {hardwareInsertDelete(true); nassert(lowlevel scrollok (win, true)).toTry.get}
    else nassert(lowlevel scrollok (win, false)).toTry.get

  def resize(width: Int, height: Int): Int = nassert(lowlevel wresize (win, height, width)).toTry.get

  def timeout(enable: Boolean): Int = nassert(lowlevel notimeout (win, !enable)).toTry.get

  def timeout(delay: Duration): Unit = nassert(lowlevel wtimeout (win, delay.toMillis.toInt)).toTry.get

  def touchline(line: Int, count: Int = 1): Int = nassert(lowlevel touchline (win, line, count)).toTry.get

  def touch: Int = nassert(lowlevel touchwin win).toTry.get

  def touched: Boolean = lowlevel is_wintouched win

  def untouch: Int = nassert(lowlevel touchwin win).toTry.get

  def untouchline(line: Int, count: Int = 1): Int = nassert(lowlevel touchline (win, line, count)).toTry.get

  def use(callback: callback, data: Any*): Int = ???
}

object Window {

  private val instances: Ptr[WINDOW] MutableMap Window = MutableMap()

  private def getInstance(win: Ptr[WINDOW]): Window = instances getOrElse (win, new Window(win))

  lazy val standardScreen: Window = getInstance(lowlevel.initscr())

  def apply(width: Int, height: Int, x: Int, y: Int): Window =
    getInstance(lowlevel newwin (height, width, y, x))

  def get(fp: Ptr[FILE]): Window = getInstance(lowlevel getwin fp)

  def pad(width: Int, height: Int): Window = getInstance(lowlevel newpad (height, width))
}
