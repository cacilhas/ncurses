package info.cacilhas.tests

import info.cacilhas.ncurses
import info.cacilhas.ncurses.Window

object Main extends App {

  val window = Window.standardScreen
  try {
    ncurses cbreak false
    ncurses echo false
    ncurses nl false
    window keypad true
    window delay false

    window.clear()
    window.print(5, 5)("Hello, World!!")
    window.refresh()
    Thread sleep 5000

  } finally ncurses.endwin()
}
