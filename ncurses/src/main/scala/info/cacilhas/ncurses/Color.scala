package info.cacilhas.ncurses

import scala.scalanative.libc.stdlib.{free, malloc}
import scala.scalanative.unsafe.{CShort, Ptr, sizeof}
import scala.util.chaining.scalaUtilChainingOps

final class Color private(val value: Int) extends AnyVal {

  import Color.{CShortDouble, CShortInt}

  def content: (Double, Double, Double) = {
    val r = malloc(sizeof[Ptr[CShort]]).asInstanceOf[Ptr[CShort]]
    val g = malloc(sizeof[Ptr[CShort]]).asInstanceOf[Ptr[CShort]]
    val b = malloc(sizeof[Ptr[CShort]]).asInstanceOf[Ptr[CShort]]

    try {
      nassert(lowlevel color_content (value.$, r, g, b))
      (!r / 1000.0, !g / 1000.0, !b / 1000.0)

    }
    finally {
      free(r.asInstanceOf[Ptr[Byte]])
      free(g.asInstanceOf[Ptr[Byte]])
      free(b.asInstanceOf[Ptr[Byte]])
    }
  }

  def init(red: Double, green: Double, blue: Double): Int =
    nassert(lowlevel init_color (value.$, red.$, green.$, blue.$))

  def pair(foreground: Color = this, background: Color = this): Color.Pair = {
    nassert(lowlevel init_pair (value.$, foreground.value.$, background.value.$))
    pair
  }

  def pair: Color.Pair = lowlevel.COLOR_PAIR(value).$
}

object Color {

  implicit private class CShortDouble(val value: Double) extends AnyVal {
    def $: CShort = (1000 * value).toShort
  }

  implicit private class CShortInt(val value: Int) extends AnyVal {
    def $: CShort = value.toShort
  }

  type Pair = Short

  implicit def apply(value: Int): Color = new Color(value)

  def apply(value: Int, red: Double, green: Double, blue: Double): Color =
    apply(value) tap {_ init (red, green, blue)}

  def changeable: Boolean = lowlevel.can_change_color()

  lazy val start: Int =
    if (lowlevel.has_colors()) nassert(_start)
    else throw new AssertionError("no colour support found")

  private lazy val _start: Int = lowlevel.start_color()

  lazy val Black: Color   = c_header.COLOR_BLACK
  lazy val Blue: Color    = c_header.COLOR_BLUE
  lazy val Cyan: Color    = c_header.COLOR_CYAN
  lazy val Green: Color   = c_header.COLOR_GREEN
  lazy val Magenta: Color = c_header.COLOR_MAGENTA
  lazy val Red: Color     = c_header.COLOR_RED
  lazy val White: Color   = c_header.COLOR_WHITE
  lazy val Yellow: Color  = c_header.COLOR_YELLOW
}
