package info.cacilhas.ncurses

import scala.scalanative.unsafe.UWord

final case class BorderPair(horizontal: UWord, vertical: UWord) {

  lazy val register: Int = BorderPair add this
}

object BorderPair {
  import Implicits._

  private[ncurses] var predefs: Seq[BorderPair] = Seq(' '|-' ', 0|-0)

  def add(pair: BorderPair): Int = {
    predefs :+= pair
    predefs.size - 1
  }
}
