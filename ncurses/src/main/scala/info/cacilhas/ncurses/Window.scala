package info.cacilhas.ncurses

import info.cacilhas.ncurses.lowlevel.WINDOW

import scala.collection.mutable.{Map => MutableMap}
import scala.concurrent.duration.Duration
import scala.scalanative.libc.stdio.FILE
import scala.scalanative.unsafe.{CChar, CString, Ptr, UWord, Zone, fromCString, stackalloc}
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

  def background: Char = lowlevel.getbkgd(win).toChar

  def background_=(attr: Char): Int = background = attr.toULong

  def background_=(attr: Long): Int = background = attr.toULong

  def background_=(attr: Color.Pair): Int = background = attr.toChar

  def background_=(attr: UWord): Int = nassert(lowlevel wbkgd (win, attr))

  def bottom: Int = lowlevel getmaxy win

  def box: Int = ???

  def box_=(idx: Int): Int = {
    try box = BorderPair predefs idx
    catch {case _: IndexOutOfBoundsException => throw new IllegalArgumentException (s"invalid index $idx")}
  }

  def box_=(borders: BorderPair): Int =
    nassert(lowlevel box (win, borders.vertical, borders.horizontal))

  def chgat(idx: Int, attr: Char, color: Color): Int = chgat(idx, attr.toULong, color)

  def chgat(idx: Int, attr: Long, color: Color): Int = chgat(idx, attr.toULong, color)

  def chgat(idx: Int, attr: UWord, color: Color): Int =
    nassert(lowlevel wchgat (win, idx, attr, color.pair, null))

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

  def delete(coord: Coord): Int = nassert(lowlevel mvwdelch (win, coord.y, coord.x))

  def deleteln: Int = nassert(lowlevel wdeleteln win)

  def erase: Int = nassert(lowlevel werase win)

  def getch(coord: Coord): Char = lowlevel.mvwgetch(win, coord.y, coord.x).toChar

  def getstr(coord: Coord, len: Int): String = Zone { implicit zone: Zone =>
    val str: CString = stackalloc[CChar]((len + 1).toUInt)
    nassert(lowlevel mvwgetnstr (win, coord.y, coord.x, str, len))
    fromCString(str)
  }

  def hardwareInsertDelete: Boolean = lowlevel is_idlok win

  def hardwareInsertDelete_=(enable: Boolean): Int = nassert(lowlevel idlok (win, enable))

  def hline(ch: Char, coord: Coord, width: Int): Int = hline(ch.toULong, coord, width)

  def hline(ch: Long, coord: Coord, width: Int): Int = hline(ch.toULong, coord, width)

  def hline(ch: UWord, coord: Coord, width: Int): Int =
    nassert(lowlevel mvwhline (win, coord.y, coord.x, ch, width))

  def insdelln(lines: Int): Int = nassert(lowlevel winsdelln (win, lines))

  def insertln: Int = nassert(lowlevel winsertln win)

  def instr(coord: Coord, len: Int): String = Zone { implicit zone: Zone =>
    val str: CString = stackalloc[CChar]((len + 1).toUInt)
    nassert(lowlevel mvwinnstr (win, coord.y, coord.x, str, len))
    fromCString(str)
  }

  def isPad: Boolean = lowlevel is_pad win

  def isSubwin: Boolean = lowlevel is_subwin win

  def keypad: Boolean = lowlevel is_keypad win

  def keypad_=(enable: Boolean): Int = nassert(lowlevel keypad (win, enable))

  def leaveok: Boolean = lowlevel is_leaveok win

  def leaveok_=(enable: Boolean): Int = nassert(lowlevel leaveok (win, enable))

  def left: Int = lowlevel getbegx win

  def left(relative: Boolean): Int = if (relative) lowlevel getparx win else left

  def linetouched(line: Int): Boolean = lowlevel is_linetouched (win, line)

  def overlay(dest: Window): Int = nassert(lowlevel overlay (this.win, dest.win))

  def move(coord: Coord): Int = nassert(lowlevel wmove (win, coord.y, coord.x))

  def print(coord: Coord, text: String): Seq[Int] = text.zipWithIndex map { case ch -> i =>
    nassert(lowlevel mvwaddch (win, coord.y,  coord.x+i, ch.toULong))
  }

  def put(fp: Ptr[FILE]): Int = nassert(lowlevel putwin (win, fp))

  def redraw: Int = nassert(lowlevel redrawwin win)

  def refresh: Int = nassert(lowlevel wrefresh win)

  def resize(size: Size): Int = nassert(lowlevel wresize (win, size.height, size.width))

  def right: Int = lowlevel getmaxx win

  def scroll(lines: Int): Int = nassert(lowlevel wscrl (win, lines))

  def scrollok: Boolean = lowlevel is_scrollok win

  def scrollok_=(enable: Boolean): Int =
    if (enable) {hardwareInsertDelete = true; nassert(lowlevel scrollok (win, true))}
    else nassert(lowlevel scrollok (win, false))

  def subpad(size: Size, coord: Coord): Window =
    Window getInstance nassert(lowlevel subpad (win, size.height, size.width, coord.y, coord.x))

  def subwin(size: Size, coord: Coord): Window =
    Window getInstance nassert(lowlevel subwin (win, size.height, size.width, coord.y, coord.x))

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

  def vline(ch: Char, coord: Coord, height: Int): Int = vline(ch.toULong, coord, height)

  def vline(ch: Long, coord: Coord, height: Int): Int = vline(ch.toULong, coord, height)

  def vline(ch: UWord, coord: Coord, height: Int): Int =
    nassert(lowlevel mvwvline (win, coord.y, coord.x, ch, height))
}

object Window {

  private val instances: Ptr[WINDOW] MutableMap Window = MutableMap()

  private def getInstance(win: Ptr[WINDOW]): Window = instances getOrElse (win, new Window(win))

  lazy val standardScreen: Window = getInstance(lowlevel.initscr())

  def apply(size: Size, coord: Coord): Window =
    getInstance(lowlevel newwin (size.height, size.width, coord.y, coord.x))

  def get(fp: Ptr[FILE]): Window = getInstance(lowlevel getwin fp)

  def pad(size: Size): Window = getInstance(lowlevel newpad (size.height, size.width))
}
