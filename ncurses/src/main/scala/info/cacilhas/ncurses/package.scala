package info.cacilhas

import scala.scalanative.unsafe

package object ncurses {
  lazy val version: String = unsafe.fromCString(lowlevel.curses_version())

  def cbreak(enable: Boolean): Unit = if (enable) lowlevel.cbreak() else lowlevel.nocbreak()
  def echo(enable: Boolean): Unit = if (enable) lowlevel.echo() else lowlevel.noecho()
  def endwin(): Unit = lowlevel.endwin()
  def nl(enable: Boolean): Unit = if (enable) lowlevel.nl() else lowlevel.nonl()
}
