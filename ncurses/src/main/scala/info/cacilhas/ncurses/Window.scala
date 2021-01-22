package info.cacilhas.ncurses

import info.cacilhas.ncurses.lowlevel.WINDOW

import scala.scalanative.unsigned.UnsignedRichLong

final class Window private(win: WINDOW) {

  def attron(attr: Int, enable: Boolean = true): Unit = {
    if (enable) lowlevel wattron  (win, attr)
    else        lowlevel wattroff (win, attr)
  }

  def attroff(attr: Int): Unit = attron(attr, enable = false)

  def clear(): Unit = lowlevel wclear win

  def delay(enable: Boolean): Unit = lowlevel nodelay (win, !enable)

  def keypad(enable: Boolean): Unit = lowlevel keypad (win, enable)

  def print(x: Int, y: Int)(text: String): Unit = text.zipWithIndex foreach { case ch -> i =>
    lowlevel mvwaddch (win, y,  x+i, ch.toLong.toULong)
  }

  def refresh(): Unit = lowlevel wrefresh win
}

object Window {

  lazy val standardScreen: Window = new Window(lowlevel.initscr())

  def apply(width: Int, height: Int, x: Int, y: Int): Window =
    new Window(lowlevel newwin (height, width, y, x))
}
