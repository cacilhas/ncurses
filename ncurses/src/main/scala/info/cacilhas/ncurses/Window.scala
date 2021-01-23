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

  def autorefresh: Boolean = lowlevel is_immedok win

  def autorefresh_=(enable: Boolean): Int = nassert(lowlevel immedok (win, enable))

  def bottom: Int = lowlevel getmaxy win

  def box(horizontal: Char, vertical: Char): Int =
    nassert(lowlevel box (win, vertical.toLong.toULong, horizontal.toLong.toULong))

  def chgat(idx: Int, attr: Char, color: Color): Int =
    nassert(lowlevel wchgat (win, idx, attr.toLong.toULong, color.pair, null))

  def clear: Int = nassert(lowlevel wclear win)

  def clearok: Boolean = lowlevel is_cleared win

  def clearok_=(enable: Boolean): Int = nassert(lowlevel clearok (win, enable))

  def close(): Unit = lowlevel delwin win

  def crltobot: Int = nassert(lowlevel wcrltobot win)

  def crltoeol: Int = nassert(lowlevel wcrltoeol win)

  def cursor: (Int, Int) = (lowlevel getcurx win, lowlevel getcury win)

  def delay: Boolean = !lowlevel.is_nodelay(win)

  def delay_=(enable: Boolean): Int = nassert(lowlevel nodelay (win, !enable))

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

  def hardwareInsertDelete: Boolean = lowlevel is_idlok win

  def hardwareInsertDelete_=(enable: Boolean): Int = nassert(lowlevel idlok (win, enable))

  def hline(ch: Char, x: Int, y: Int, width: Int): Int =
    nassert(lowlevel mvwhline (win, y, x, ch.toLong.toULong, width))

  def insdelln(lines: Int): Int = nassert(lowlevel winsdelln (win, lines))

  def insertln: Int = nassert(lowlevel winsertln win)

  def instr(x: Int, y: Int, len: Int): String = Zone { implicit zone: Zone =>
    val str: CString = stackalloc[CChar]((len + 1).toUInt)
    nassert(lowlevel mvwinnstr (win, y, x, str, len))
    fromCString(str)
  }

  def keypad: Boolean = lowlevel is_keypad win

  def keypad_=(enable: Boolean): Int = nassert(lowlevel keypad (win, enable))

  def leaveok: Boolean = lowlevel is_leaveok win

  def leaveok_=(enable: Boolean): Int = nassert(lowlevel leaveok (win, enable))

  def left: Int = lowlevel getbegx win

  def left(relative: Boolean): Int = if (relative) lowlevel getparx win else left

  def linetouched(line: Int): Boolean = lowlevel is_linetouched (win, line)

  def overlay(dest: Window): Int = nassert(lowlevel overlay (this.win, dest.win))

  def move(x: Int, y: Int): Int = nassert(lowlevel wmove (win, y, x))

  def pad: Boolean = lowlevel is_pad win

  def print(x: Int, y: Int)(text: String): Seq[Int] = text.zipWithIndex map { case ch -> i =>
    nassert(lowlevel mvwaddch (win, y,  x+i, ch.toLong.toULong))
  }

  def put(fp: Ptr[FILE]): Int = nassert(lowlevel putwin (win, fp))

  def redraw: Int = nassert(lowlevel redrawwin win)

  def refresh: Int = nassert(lowlevel wrefresh win)

  def resize(width: Int, height: Int): Int = nassert(lowlevel wresize (win, height, width))

  def right: Int = lowlevel getmaxx win

  def scroll(lines: Int): Int = nassert(lowlevel wscrl (win, lines))

  def scrollok: Boolean = lowlevel is_scrollok win

  def scrollok_=(enable: Boolean): Int =
    if (enable) {hardwareInsertDelete = true; nassert(lowlevel scrollok (win, true))}
    else nassert(lowlevel scrollok (win, false))

  def subwin: Boolean = lowlevel is_subwin win

  def timeout: Boolean = !lowlevel.is_notimeout(win)

  def timeout_=(enable: Boolean): Int = nassert(lowlevel notimeout (win, !enable))

  def timeout_=(delay: Duration): Unit = lowlevel wtimeout (win, delay.toMillis.toInt)

  def top: Int = lowlevel getbegy win

  def top(relative: Boolean): Int = if (relative) lowlevel getpary win else top

  def touchline(line: Int, count: Int = 1): Int = nassert(lowlevel touchline (win, line, count))

  def touch: Int = nassert(lowlevel touchwin win)

  def touched: Boolean = lowlevel is_wintouched win

  def untouch: Int = nassert(lowlevel touchwin win)

  def untouchline(line: Int, count: Int = 1): Int = nassert(lowlevel touchline (win, line, count))

  def use(callback: callback, data: Any*): Int = ???

  def vline(ch: Char, x: Int, y: Int, height: Int): Int =
    nassert(lowlevel mvwvline (win, y, x, ch.toLong.toULong, height))
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
