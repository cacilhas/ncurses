package info.cacilhas.tests

import info.cacilhas.ncurses
import info.cacilhas.ncurses.CursorVisibility.Invisible
import info.cacilhas.ncurses.{Color, Window}
import info.cacilhas.ncurses.Implicits._

import scala.concurrent.duration._
import scala.util.Using

object Main extends App {

  try {
    Using(Window.standardScreen) { window =>
      Color.start

      ncurses cbreak true      // catch each pressed key
      ncurses echo   false     // no echo in screen
      ncurses nl     false     // no rolling
      ncurses curSet Invisible // hide cursor

      window.keypad = true  // enable capture Fn
      window.delay  = false // no delay

      window.clear
      window attron Color.Green.pair(foreground = Color.Magenta)
      window box (horizontal = '#', vertical = '*')
      window attron Color.Cyan.pair(background = Color.Black)
      window.print(5\\5, ncurses.version)

      window move (0\\0)
      Using(Window(17<>5, 10\\7)) { subwin =>

        val gray = Color(101, red = 0.25, green = 0.25, blue = 0.25)
        gray.pair()

        // FIXME: not working 😢
        subwin.background = gray.pair
        subwin.clear
        subwin attron Color.Green.pair
        subwin box (0, 0)
        subwin overlay window
        subwin.refresh

        window attron Color(100, red = 1, green = 0.5, blue = 0).pair(background = gray)
        window.print(12\\9, "Hello, World!")

        window.refresh
        while (running) ncurses sleep 100.millis
      }
    }.flatten.get
  } finally ncurses.endwin

  def running: Boolean = ncurses.getch match {
    case 27 | 'Q' | 'q' => false
    case _              => true
  }
}
