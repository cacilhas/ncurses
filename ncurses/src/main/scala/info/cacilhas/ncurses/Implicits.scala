package info.cacilhas.ncurses

object Implicits {

  implicit class CoordInt(val value: Int) extends AnyVal {

    def \\(y: Int): Coord = Coord(x = value, y = y)

    def <>(height: Int): Size = Size(width = value, height = height)
  }
}
