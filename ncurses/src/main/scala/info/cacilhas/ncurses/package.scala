package info.cacilhas

package object ncurses {

  def cbreak(enable: Boolean): Unit = if (enable) lowlevel.cbreak() else lowlevel.nocbreak()
  def echo(enable: Boolean): Unit = if (enable) lowlevel.echo() else lowlevel.noecho()
  def endwin(): Unit = lowlevel.endwin()
  def nl(enable: Boolean): Unit = if (enable) lowlevel.nl() else lowlevel.nonl()
}
