package info.cacilhas.ncurses

final case class RGB(red: Double, green: Double, blue: Double) {

  assert(red >= 0 && red <= 1, s"red out of range: $red")
  assert(green >= 0 && green <= 1, s"green out of range: $green")
  assert(blue >= 0 && blue <= 1, s"blue out of range: $blue")

  def color(idx: Int): Color = Color(idx, red, green, blue)
}


object RGB {
  final class RG private[ncurses](red: Double, green: Double) {
    def ##(blue: Double): RGB = RGB(red, green, blue)
  }
}
