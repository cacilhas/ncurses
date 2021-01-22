package info.cacilhas.ncurses

import io.scalaland.enumz.Enum

sealed class CursorVisibility(private[ncurses] val index: Int)

object CursorVisibility {

  case object Invisible extends CursorVisibility(0)
  case object Normal    extends CursorVisibility(1)
  case object High      extends CursorVisibility(2)

  private lazy val enum = Enum[CursorVisibility]

  def from(value: Int): Option[CursorVisibility] = enum.values find {_.index == value}
}
