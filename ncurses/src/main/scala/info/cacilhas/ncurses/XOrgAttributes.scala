package info.cacilhas.ncurses

import scala.scalanative.unsafe.UWord

object XOrgAttributes {

  import c_header._

  lazy val altcharset: UWord = A_ALTCHARSET
  lazy val attributes: UWord = A_ATTRIBUTES
  lazy val blink: UWord      = A_BLINK
  lazy val bold: UWord       = A_BOLD
  lazy val chartext: UWord   = A_CHARTEXT
  lazy val color: UWord      = A_COLOR
  lazy val dim: UWord        = A_DIM
  lazy val horizontal: UWord = A_HORIZONTAL
  lazy val invis: UWord      = A_INVIS
  lazy val italic: UWord     = A_ITALIC
  lazy val left: UWord       = A_LEFT
  lazy val low: UWord        = A_LOW
  lazy val normal: UWord     = A_NORMAL
  lazy val protect: UWord    = A_PROTECT
  lazy val reverse: UWord    = A_REVERSE
  lazy val right: UWord      = A_RIGHT
  lazy val standout: UWord   = A_STANDOUT
  lazy val underline: UWord  = A_UNDERLINE
  lazy val top: UWord        = A_TOP
  lazy val vertical: UWord   = A_VERTICAL
}
