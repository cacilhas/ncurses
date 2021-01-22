package info.cacilhas.ncurses

import io.scalaland.enumz.Enum

sealed trait InsertDeleteCapability

object InsertDeleteCapability {

  case object No       extends InsertDeleteCapability
  case object Yes      extends InsertDeleteCapability
  case object Emulated extends InsertDeleteCapability

  private lazy val enum = Enum[InsertDeleteCapability]

  def fromString(value: String): Option[InsertDeleteCapability] = enum withNameInsensitiveOption value
}
