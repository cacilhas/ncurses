package info.cacilhas

import info.cacilhas.macros.NcursesAssertMacro

import scala.concurrent.duration.Duration
import scala.scalanative.unsafe
import scala.util.Try

package object ncurses {

  lazy val acs: Seq[Char]  = Option(lowlevel.acs_map) map {_.toSeq} getOrElse Nil map {_.toChar}
  lazy val version: String = unsafe fromCString lowlevel.curses_version()

  def cbreak(enable: Boolean): Throwable Either Int =
    nassert(if (enable) lowlevel.cbreak() else lowlevel.nocbreak())

  def baudrate: Int = lowlevel.baudrate()

  def beep: Throwable Either Int = nassert(lowlevel.beep())

  def echo(enable: Boolean): Throwable Either Int =
    nassert(if (enable) lowlevel.echo() else lowlevel.noecho())

  def endwin: Throwable Either Int = nassert(lowlevel.endwin())

  def napms(ms: Int): Throwable Either Int = nassert(lowlevel napms ms)

  def nl(enable: Boolean): Throwable Either Int =
    nassert(if (enable) lowlevel.nl() else lowlevel.nonl())

  def curSet(visibility: CursorVisibility): Throwable Either Int =
    nassert(lowlevel curs_set visibility.index)

  def resetProgMode: Throwable Either Int = nassert(lowlevel.reset_prog_mode())

  def resetShellMode: Throwable Either Int = nassert(lowlevel.reset_shell_mode())

  def resetTTY: Throwable Either Int = nassert(lowlevel.resetty())

  def saveProgMode: Throwable Either Int = nassert(lowlevel.def_prog_mode())

  def saveShellMode: Throwable Either Int = nassert(lowlevel.def_shell_mode())

  def saveTTY: Throwable Either Int = nassert(lowlevel.savetty())

  def sleep(dur: Duration): Throwable Either Int = napms(dur.toMillis.toInt)

  /*--------------------------------------------------------------------------*/

  private[ncurses] def nassert[A](code: A): Throwable Either A = Try(_nassert(code)).toEither

  private def _nassert[A](code: A): A = macro NcursesAssertMacro.impl
}
