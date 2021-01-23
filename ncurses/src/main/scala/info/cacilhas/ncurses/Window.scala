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
  }

  def attroff(attr: Int): Unit = attron(attr, enable = false)

  def autorefresch(enable: Boolean): Int = nassert(lowlevel immedok (win, enable))

  def box(horizontal: Char, vertical: Char): Int =
    nassert(lowlevel box (win, vertical.toLong.toULong, horizontal.toLong.toULong))

  def chgat(idx: Int, attr: Char, color: Color): Int =
    nassert(lowlevel wchgat (win, idx, attr.toLong.toULong, color.pair, null))

  def clear: Int = nassert(lowlevel wclear win)

  def clearok: Int = clearok(true)

  def clearok(enable: Boolean): Int = nassert(lowlevel clearok (win, enable))

  def close(): Unit = lowlevel delwin win

  def crltobot: Int = nassert(lowlevel wcrltobot win)

  def crltoeol: Int = nassert(lowlevel wcrltoeol win)

  def delay(enable: Boolean): Int = nassert(lowlevel nodelay (win, !enable))

  def delete: Int = nassert(lowlevel wdelch win)

  def delete(x: Int, y: Int): Int = nassert(lowlevel mvwdelch (win, y, x))

  def deleteln: Int = nassert(lowlevel wdeleteln win)

  def erase: Int = nassert(lowlevel werase win)

  def getbkgd: Char = lowlevel.getbkgd(win).toChar

  def getch(x: Int, y: Int): Char = lowlevel.mvwgetch(win, y, x).toChar

  def getstr(x: Int, y: Int, len: Int): String = Zone { implicit zone: Zone =>
    val str: CString = stackalloc[CChar]((len + 1).toUInt)
    nassert(lowlevel mvwgetnstr (win, y, x, str, len))
    fromCString(str)
  }

  def hardwareInsertDelete(enable: Boolean): Int = nassert(lowlevel idlok (win, enable))

  def hline(ch: Char, x: Int, y: Int, width: Int): Int =
    nassert(lowlevel mvwhline (win, y, x, ch.toLong.toULong, width))

  def insdelln(lines: Int): Int = nassert(lowlevel winsdelln (win, lines))

  def insertln: Int = nassert(lowlevel winsertln win)

  def instr(x: Int, y: Int, len: Int): String = Zone { implicit zone: Zone =>
    val str: CString = stackalloc[CChar]((len + 1).toUInt)
    nassert(lowlevel mvwinnstr (win, y, x, str, len))
    fromCString(str)
  }

  def linetouched(line: Int): Boolean = lowlevel is_linetouched (win, line)

  def vline(ch: Char, x: Int, y: Int, height: Int): Int =
    nassert(lowlevel mvwvline (win, y, x, ch.toLong.toULong, height))

  def keypad(enable: Boolean): Int = nassert(lowlevel keypad (win, enable))

  def overlay(dest: Window): Int = nassert(lowlevel overlay (this.win, dest.win))

  def print(x: Int, y: Int)(text: String): Seq[Int] = text.zipWithIndex map { case ch -> i =>
    nassert(lowlevel mvwaddch (win, y,  x+i, ch.toLong.toULong))
  }

  def put(fp: Ptr[FILE]): Int = nassert(lowlevel putwin (win, fp))

  def redraw: Int = nassert(lowlevel redrawwin win)

  def refresh: Int = nassert(lowlevel wrefresh win)

  def scroll(lines: Int): Int = nassert(lowlevel wscrl (win, lines))

  def scrollok(enable: Boolean): Int =
    if (enable) {hardwareInsertDelete(true); nassert(lowlevel scrollok (win, true))}
    else nassert(lowlevel scrollok (win, false))

  def resize(width: Int, height: Int): Int = nassert(lowlevel wresize (win, height, width))

  def timeout(enable: Boolean): Int = nassert(lowlevel notimeout (win, !enable))

  def timeout(delay: Duration): Unit = lowlevel wtimeout (win, delay.toMillis.toInt)

  def touchline(line: Int, count: Int = 1): Int = nassert(lowlevel touchline (win, line, count))

  def touch: Int = nassert(lowlevel touchwin win)

  def touched: Boolean = lowlevel is_wintouched win

  def untouch: Int = nassert(lowlevel touchwin win)

  def untouchline(line: Int, count: Int = 1): Int = nassert(lowlevel touchline (win, line, count))

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
