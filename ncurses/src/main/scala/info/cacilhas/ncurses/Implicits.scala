package info.cacilhas.ncurses

import scala.scalanative.unsafe.UWord
import scala.scalanative.unsigned.UnsignedRichLong

object Implicits {

  implicit class CoordInt(val value: Int) extends AnyVal {
    def \\(y: Int): Coord     = Coord(x = value, y = y)
    def <>(height: Int): Size = Size(width = value, height = height)
  }

  implicit class BorderChar(val value: Char) extends AnyVal {
    def -|(other: Char): BorderPair  = value.toLong -| other.toLong
    def -|(other: Long): BorderPair  = value.toLong -| other
    def -|(other: UWord): BorderPair = value.toLong -| other
    def |-(other: Char): BorderPair  = value.toLong |- other.toLong
    def |-(other: Long): BorderPair  = value.toLong |- other
    def |-(other: UWord): BorderPair = value.toLong |- other
  }

  implicit class BorderLong(val value: Long) extends AnyVal {
    def -|(other: Char): BorderPair  = value -| other.toLong
    def -|(other: Long): BorderPair  = value.toULong -| other.toULong
    def -|(other: UWord): BorderPair = value.toULong -| other
    def |-(other: Char): BorderPair  = value |- other.toLong
    def |-(other: Long): BorderPair  = value.toULong |- other.toULong
    def |-(other: UWord): BorderPair = value.toULong |- other
  }

  implicit class BorderUWord(val value: UWord) extends AnyRef {
    def -|(other: Char): BorderPair  = value -| other.toLong
    def -|(other: Long): BorderPair  = value -| other.toULong
    def -|(other: UWord): BorderPair = BorderPair(horizontal = value, vertical = other)
    def |-(other: Char): BorderPair  = value |- other.toLong
    def |-(other: Long): BorderPair  = value |- other.toULong
    def |-(other: UWord): BorderPair = BorderPair(vertical = value, horizontal = other)
  }

  implicit class RGBDouble(val value: Double) extends AnyVal {
    def ##(green: Double): RGB.RG = new RGB.RG(value, green)
  }

  implicit class RGBLong(val value: Long) extends AnyVal {
    def ##(green: Double): RGB.RG = new RGB.RG(value.toDouble, green)
  }
}
