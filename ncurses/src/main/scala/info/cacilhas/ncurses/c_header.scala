package info.cacilhas.ncurses

import scala.scalanative.unsafe._

object c_header {

  val A_NORMAL: CLong     = 0
  val A_ATTRIBUTES: CLong = -256
  val A_CHARTEXT: CLong   = 255
  val A_COLOR: CLong      = 65280
  val A_STANDOUT: CLong   = 65536
  val A_UNDERLINE: CLong  = 131072
  val A_REVERSE: CLong    = 262144
  val A_BLINK: CLong      = 524288
  val A_DIM: CLong        = 1048576
  val A_BOLD: CLong       = 2097152
  val A_ALTCHARSET: CLong = 4194304
  val A_INVIS: CLong      = 8388608
  val A_PROTECT: CLong    = 16777216
  val A_HORIZONTAL: CLong = 33554432
  val A_LEFT: CLong       = 67108864
  val A_LOW: CLong        = 134217728
  val A_RIGHT: CLong      = 268435456
  val A_TOP: CLong        = 536870912
  val A_VERTICAL: CLong   = 1073741824
  val A_ITALIC: CLong     = -2147483648

  val COLOR_BLACK: CInt   = 0
  val COLOR_RED: CInt     = 1
  val COLOR_GREEN: CInt   = 2
  val COLOR_YELLOW: CInt  = 3
  val COLOR_BLUE: CInt    = 4
  val COLOR_MAGENTA: CInt = 5
  val COLOR_CYAN:  CInt   = 6
  val COLOR_WHITE: CInt   = 7

  val ERR: CInt = -1
  var OK: CInt  = 0
}
