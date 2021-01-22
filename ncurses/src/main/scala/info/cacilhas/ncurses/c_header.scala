package info.cacilhas.ncurses

import info.cacilhas.ncurses.lowlevel.acs_map

import scala.scalanative.unsafe._
import scala.scalanative.unsigned.UnsignedRichLong

object c_header {

  lazy val A_NORMAL: UWord     = 0.toLong.toULong
  lazy val A_ATTRIBUTES: UWord = (-256).toLong.toULong
  lazy val A_CHARTEXT: UWord   = 255.toLong.toULong
  lazy val A_COLOR: UWord      = 65280.toLong.toULong
  lazy val A_STANDOUT: UWord   = 65536.toLong.toULong
  lazy val A_UNDERLINE: UWord  = 131072.toLong.toULong
  lazy val A_REVERSE: UWord    = 262144.toLong.toULong
  lazy val A_BLINK: UWord      = 524288.toLong.toULong
  lazy val A_DIM: UWord        = 1048576.toLong.toULong
  lazy val A_BOLD: UWord       = 2097152.toLong.toULong
  lazy val A_ALTCHARSET: UWord = 4194304.toLong.toULong
  lazy val A_INVIS: UWord      = 8388608.toLong.toULong
  lazy val A_PROTECT: UWord    = 16777216.toLong.toULong
  lazy val A_HORIZONTAL: UWord = 33554432.toLong.toULong
  lazy val A_LEFT: UWord       = 67108864.toLong.toULong
  lazy val A_LOW: UWord        = 134217728.toLong.toULong
  lazy val A_RIGHT: UWord      = 268435456.toLong.toULong
  lazy val A_TOP: UWord        = 536870912.toLong.toULong
  lazy val A_VERTICAL: UWord   = 1073741824.toLong.toULong
  lazy val A_ITALIC: UWord     = (-2147483648).toLong.toULong


  lazy val ACS_ULCORNER: UWord = acs_map('l'.toInt)
  lazy val ACS_LLCORNER: UWord = acs_map('m'.toInt)
  lazy val ACS_URCORNER: UWord = acs_map('k'.toInt)
  lazy val ACS_LRCORNER: UWord = acs_map('j'.toInt)
  lazy val ACS_LTEE: UWord     = acs_map('t'.toInt)
  lazy val ACS_RTEE: UWord     = acs_map('u'.toInt)
  lazy val ACS_BTEE: UWord     = acs_map('v'.toInt)
  lazy val ACS_TTEE: UWord     = acs_map('w'.toInt)
  lazy val ACS_HLINE: UWord    = acs_map('q'.toInt)
  lazy val ACS_VLINE: UWord    = acs_map('x'.toInt)
  lazy val ACS_PLUS: UWord     = acs_map('n'.toInt)
  lazy val ACS_S1: UWord       = acs_map('o'.toInt)
  lazy val ACS_S9: UWord       = acs_map('s'.toInt)
  lazy val ACS_DIAMOND: UWord  = acs_map('`'.toInt)
  lazy val ACS_CKBOARD: UWord  = acs_map('a'.toInt)
  lazy val ACS_DEGREE: UWord   = acs_map('f'.toInt)
  lazy val ACS_PLMINUS: UWord  = acs_map('g'.toInt)
  lazy val ACS_BULLET: UWord   = acs_map('~'.toInt)
  lazy val ACS_LARROW: UWord   = acs_map(','.toInt)
  lazy val ACS_RARROW: UWord   = acs_map('+'.toInt)
  lazy val ACS_DARROW: UWord   = acs_map('.'.toInt)
  lazy val ACS_UARROW: UWord   = acs_map('-'.toInt)
  lazy val ACS_BOARD: UWord    = acs_map('h'.toInt)
  lazy val ACS_LANTERN: UWord  = acs_map('i'.toInt)
  lazy val ACS_BLOCK: UWord    = acs_map('0'.toInt)
  lazy val ACS_S3: UWord       = acs_map('p'.toInt)
  lazy val ACS_S7: UWord       = acs_map('r'.toInt)
  lazy val ACS_LEQUAL: UWord   = acs_map('y'.toInt)
  lazy val ACS_GEQUAL: UWord   = acs_map('z'.toInt)
  lazy val ACS_PI: UWord       = acs_map('{'.toInt)
  lazy val ACS_NEQUAL: UWord   = acs_map('|'.toInt)
  lazy val ACS_STERLING: UWord = acs_map('}'.toInt)

  lazy val COLOR_BLACK: CInt   = 0
  lazy val COLOR_RED: CInt     = 1
  lazy val COLOR_GREEN: CInt   = 2
  lazy val COLOR_YELLOW: CInt  = 3
  lazy val COLOR_BLUE: CInt    = 4
  lazy val COLOR_MAGENTA: CInt = 5
  lazy val COLOR_CYAN:  CInt   = 6
  lazy val COLOR_WHITE: CInt   = 7

  lazy val ERR: CInt = -1
  lazy val OK: CInt  = 0
}
