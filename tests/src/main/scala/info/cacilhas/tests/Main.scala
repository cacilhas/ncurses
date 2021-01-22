package info.cacilhas.tests

import info.cacilhas.ncurses
import info.cacilhas.ncurses.{Color, Window}

import scala.util.Using

object Main extends App {

  Using(Window.standardScreen) { window =>
    Color.start
    ncurses cbreak false
    ncurses echo   false
    ncurses nl     false
    window  keypad true
    window  delay  false

    window.clear
    window attron Color.Green.pair(foreground = Color.Magenta)
    window box ('#', '*')
    window attron Color.Cyan.pair(background = Color.Black)
    window.print(5, 5)(ncurses.version)
    window.refresh
    while (ncurses.getch != 27.toChar) Thread sleep 100
  }

  ncurses.endwin
}
