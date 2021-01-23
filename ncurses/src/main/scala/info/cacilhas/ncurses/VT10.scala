package info.cacilhas.ncurses

import scala.scalanative.unsafe.UWord

object VT10 {

  import c_header._

  lazy val block: UWord    = ACS_BLOCK
  lazy val board: UWord    = ACS_BOARD
  lazy val btee: UWord     = ACS_BTEE
  lazy val bullet: UWord   = ACS_BULLET
  lazy val ckboard: UWord  = ACS_CKBOARD
  lazy val darrow: UWord   = ACS_DARROW
  lazy val degree: UWord   = ACS_DEGREE
  lazy val diamond: UWord  = ACS_DIAMOND
  lazy val gequal: UWord   = ACS_GEQUAL
  lazy val hline: UWord    = ACS_HLINE
  lazy val lantern: UWord  = ACS_LANTERN
  lazy val larrow: UWord   = ACS_LARROW
  lazy val lequal: UWord   = ACS_LEQUAL
  lazy val llcorner: UWord = ACS_LLCORNER
  lazy val lrcorner: UWord = ACS_LRCORNER
  lazy val ltee: UWord     = ACS_LTEE
  lazy val nequal: UWord   = ACS_NEQUAL
  lazy val pi: UWord       = ACS_PI
  lazy val plminus: UWord  = ACS_PLMINUS
  lazy val plus: UWord     = ACS_PLUS
  lazy val rarrow: UWord   = ACS_RARROW
  lazy val rtee: UWord     = ACS_RTEE
  lazy val s1: UWord       = ACS_S1
  lazy val s3: UWord       = ACS_S3
  lazy val s7: UWord       = ACS_S7
  lazy val s9: UWord       = ACS_S9
  lazy val sterling: UWord = ACS_STERLING
  lazy val ttee: UWord     = ACS_TTEE
  lazy val uarrow: UWord   = ACS_UARROW
  lazy val ulcorner: UWord = ACS_ULCORNER
  lazy val urcorner: UWord = ACS_URCORNER
  lazy val vline: UWord    = ACS_VLINE
}
