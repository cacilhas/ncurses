package info.cacilhas.ncurses

import scala.scalanative.unsafe._

object lowlevel_h {

  type WINDOW = Ptr[CChar]

  val COLOR_BLACK: CInt   = 0
  val COLOR_RED: CInt     = 1
  val COLOR_GREEN: CInt   = 2
  val COLOR_YELLOW: CInt  = 3
  val COLOR_BLUE: CInt    = 4
  val COLOR_MAGENTA: CInt = 5
  val COLOR_CYAN:  CInt   = 6
  val COLOR_WHITE: CInt   = 7
}
