package info.cacilhas.tests

import info.cacilhas.ncurses
import info.cacilhas.ncurses.CursorVisibility.Invisible
import info.cacilhas.ncurses.Implicits._
import info.cacilhas.ncurses.{Color, Window}

import scala.concurrent.duration._
import scala.util.Using

object Main extends App {

  val res = Using(Window.standardScreen) { window =>
    Color.start

    val orange = 1##0.5##0        color 100
    val gray   = 0.25##0.25##0.25 color 101
    val bg     = 0##0##0          color 102 pair (foreground = gray)

    ncurses cbreak true      // catch each pressed key
    ncurses echo   false     // no echo in screen
    ncurses nl     false     // no rolling
    ncurses curSet Invisible // hide cursor

    window.keypad     = true     // enable capture Fn
    window.delay      = false    // no delay
    window.background = '.' | bg // dotted background
    window.clear

    val version  = ncurses.version
    val width    = window.right
    val height   = window.bottom
    val versionX = (width - version.length) / 2
    val versionY = (height - 7) / 2
    window attron Color.Green.pair(foreground = Color.Magenta)
    window.box = '#'-|'*'
    window attron Color.Cyan.pair(background = Color.Black)
    window.print(versionX\\versionY, version)

    val message  = "Hello, World!!"
    val subwinWi = message.length + 4
    val subwinX  = (width - subwinWi) / 2
    val subwinY  = versionY + 2
    Using(window subwin (subwinWi<>5, subwinX\\subwinY)) { subwin =>

      subwin.clear
      subwin.background = gray.pair()
      subwin attron Color.Green.pair
      subwin.box = 1
      subwin.refresh

      subwin attron orange.pair(background = gray)
      subwin.print(2\\2, message)
      subwin.refresh

      window.refresh
      while (running) ncurses sleep 100.millis
    }
  }.flatten
  ncurses.endwin
  res.get

  def running: Boolean = ncurses.getch match {
    case 27 | 'Q' | 'q' => false
    case _              => true
  }
}
