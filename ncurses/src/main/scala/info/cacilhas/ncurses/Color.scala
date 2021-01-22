package info.cacilhas.ncurses

import scala.scalanative.unsafe.CShort
import scala.util.chaining.scalaUtilChainingOps

final class Color private(val value: Int) extends AnyVal {

  def init(red: Double, green: Double, blue: Double): Unit =
    lowlevel init_color (%(value), %(red), %(green), %(blue))

  def pair(foreground: Color = this, background: Color = this): Color.Pair =
    lowlevel init_pair (%(value), %(foreground.value), %(background.value)) pipe {_ => pair}

  def pair: Color.Pair = lowlevel COLOR_PAIR %(value)

  private def %(value: Double): CShort = (1000 * value).toShort

  private def %(value: Int): CShort = value.toShort
}

object Color {

  type Pair = Int

  implicit def apply(value: Int): Color = new Color(value)

  def apply(value: Int, red: Double, green: Double, blue: Double): Color =
    apply(value) tap {_ init (red, green, blue)}

  def start(): Unit = assert(_start == 0)

  private lazy val _start: Int = lowlevel.start_color()

  lazy val Black: Color   = lowlevel_h.COLOR_BLACK
  lazy val Red: Color     = lowlevel_h.COLOR_RED
  lazy val Green: Color   = lowlevel_h.COLOR_GREEN
  lazy val Yellow: Color  = lowlevel_h.COLOR_YELLOW
  lazy val Blue: Color    = lowlevel_h.COLOR_BLUE
  lazy val Magenta: Color = lowlevel_h.COLOR_MAGENTA
  lazy val Cyan: Color    = lowlevel_h.COLOR_CYAN
  lazy val White: Color   = lowlevel_h.COLOR_WHITE
}
