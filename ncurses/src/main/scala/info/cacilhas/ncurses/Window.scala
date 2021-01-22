package info.cacilhas.ncurses

import scala.scalanative.unsafe.Ptr

import info.cacilhas.ncurses.lowlevel.WINDOW

import scala.scalanative.unsigned.UnsignedRichLong

final class Window private(win: Ptr[WINDOW]) {

  assert(win != null, "could not initialise screen")

  def attron(attr: Int, enable: Boolean = true): Int = {
    if (enable) nassert(lowlevel wattron  (win, attr))
    else        nassert(lowlevel wattroff (win, attr))
  }.toTry.get

  def attroff(attr: Int): Unit = attron(attr, enable = false)

  def box(horizontal: Char, vertical: Char): Int =
    nassert(lowlevel box (win, vertical.toLong.toULong, horizontal.toLong.toULong)).toTry.get

  def chgat(idx: Int, attr: Char, color: Color): Int =
    nassert(lowlevel wchgat (win, idx, attr.toLong.toULong, color.pair, null)).toTry.get

  def clear: Int = nassert(lowlevel wclear win).toTry.get

  def clearok: Int = clearok(true)

  def clearok(enable: Boolean): Int = nassert(lowlevel clearok (win, enable)).toTry.get

  def crltobot: Int = nassert(lowlevel wcrltobot win).toTry.get

  def crltoeol: Int = nassert(lowlevel wcrltoeol win).toTry.get

  def delay(enable: Boolean): Int = nassert(lowlevel nodelay (win, !enable)).toTry.get

  def delete: Int = nassert(lowlevel wdelch win).toTry.get

  def delete(x: Int, y: Int): Int = nassert(lowlevel mvwdelch (win, y, x)).toTry.get

  def keypad(enable: Boolean): Int = nassert(lowlevel keypad (win, enable)).toTry.get

  def print(x: Int, y: Int)(text: String): Seq[Int] = text.zipWithIndex map { case ch -> i =>
    nassert(lowlevel mvwaddch (win, y,  x+i, ch.toLong.toULong)).toTry.get
  }

  def refresh: Int = nassert(lowlevel wrefresh win).toTry.get
}

object Window {

  lazy val standardScreen: Window = new Window(lowlevel.initscr())

  def apply(width: Int, height: Int, x: Int, y: Int): Window =
    new Window(lowlevel newwin (height, width, y, x))
}
