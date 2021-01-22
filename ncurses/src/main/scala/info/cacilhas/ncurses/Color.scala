package info.cacilhas.ncurses

import scala.scalanative.unsafe.CShort
import scala.util.chaining.scalaUtilChainingOps

final class Color private(val value: Int) extends AnyVal {

  def init(red: Double, green: Double, blue: Double): Int =
    nassert(lowlevel init_color (%(value), %(red), %(green), %(blue))).toTry.get

  def pair(foreground: Color = this, background: Color = this): Color.Pair =
    nassert(lowlevel init_pair (%(value), %(foreground.value), %(background.value))) match {
      case Right(_)  => pair
      case Left(exc) => throw exc
    }

  def pair: Color.Pair = lowlevel COLOR_PAIR %(value)

  private def %(value: Double): CShort = (1000 * value).toShort

  private def %(value: Int): CShort = value.toShort
}

object Color {

  type Pair = Int

  implicit def apply(value: Int): Color = new Color(value)

  def apply(value: Int, red: Double, green: Double, blue: Double): Color =
    apply(value) tap {_ init (red, green, blue)}

  def start: Int = nassert(_start).toTry.get

  private lazy val _start: Int = lowlevel.start_color()

  lazy val Black: Color   = c_header.COLOR_BLACK
  lazy val Red: Color     = c_header.COLOR_RED
  lazy val Green: Color   = c_header.COLOR_GREEN
  lazy val Yellow: Color  = c_header.COLOR_YELLOW
  lazy val Blue: Color    = c_header.COLOR_BLUE
  lazy val Magenta: Color = c_header.COLOR_MAGENTA
  lazy val Cyan: Color    = c_header.COLOR_CYAN
  lazy val White: Color   = c_header.COLOR_WHITE
}
