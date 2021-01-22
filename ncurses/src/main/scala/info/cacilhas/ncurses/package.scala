package info.cacilhas

import info.cacilhas.macros.NcursesAssertMacro

import scala.scalanative.unsafe
import scala.util.Try

package object ncurses {
  lazy val version: String = unsafe.fromCString(lowlevel.curses_version())

  def cbreak(enable: Boolean): Throwable Either Int =
    nassert(if (enable) lowlevel.cbreak() else lowlevel.nocbreak())

  def echo(enable: Boolean): Throwable Either Int =
    nassert(if (enable) lowlevel.echo() else lowlevel.noecho())

  def endwin: Throwable Either Int = nassert(lowlevel.endwin())

  def nl(enable: Boolean): Throwable Either Int =
    nassert(if (enable) lowlevel.nl() else lowlevel.nonl())

  private[ncurses] def nassert[A](code: A): Throwable Either A = Try(_nassert(code)).toEither

  private def _nassert[A](code: A): A = macro NcursesAssertMacro.impl
}
