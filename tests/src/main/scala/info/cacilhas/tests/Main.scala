package info.cacilhas.tests

import info.cacilhas.ncurses
import info.cacilhas.ncurses.{Color, Window}

object Main extends App {

  val window = Window.standardScreen
  try {
    Color.start()
    ncurses cbreak false
    ncurses echo false
    ncurses nl false
    window keypad true
    window delay false

    window.clear()
    window attron Color.Cyan.pair(background = Color.Black)
    window.print(5, 5)(ncurses.version)
    window.refresh()
    Thread sleep 5000

  } finally ncurses.endwin()
}
