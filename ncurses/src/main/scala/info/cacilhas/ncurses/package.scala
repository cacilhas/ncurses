package info.cacilhas

import info.cacilhas.macros.NcursesAssertMacro

import scala.concurrent.duration.Duration
import scala.scalanative.unsafe._
import scala.util.Try

package object ncurses {

  lazy val acs: Seq[Char]  = Option(lowlevel.acs_map) map {_.toSeq} getOrElse Nil map {_.toChar}
  lazy val version: String = fromCString(lowlevel.curses_version())

  def cbreak(enable: Boolean): Throwable Either Int =
    nassert(if (enable) lowlevel.cbreak() else lowlevel.nocbreak())

  def baudrate: Int = lowlevel.baudrate()

  def beep: Throwable Either Int = nassert(lowlevel.beep())

  def echo(enable: Boolean): Throwable Either Int =
    nassert(if (enable) lowlevel.echo() else lowlevel.noecho())

  def endwin: Throwable Either Int = nassert(lowlevel.endwin())

  def flash: Throwable Either Int = nassert(lowlevel.flash())

  def flushinp: Throwable Either Int = nassert(lowlevel.flushinp())

  def hasInsertDelete: InsertDeleteCapability = {
    if (lowlevel.has_ic())      InsertDeleteCapability.Yes
    else if (lowlevel.has_il()) InsertDeleteCapability.Emulated
         else                   InsertDeleteCapability.No
  }

  def keyname(key: Int): String = fromCString(lowlevel keyname key)

  def napms(ms: Int): Throwable Either Int = nassert(lowlevel napms ms)

  def nl(enable: Boolean): Throwable Either Int =
    nassert(if (enable) lowlevel.nl() else lowlevel.nonl())

  def curSet(visibility: CursorVisibility): Throwable Either Int =
    nassert(lowlevel curs_set visibility.index)

  def getch: Char = lowlevel.getch().toChar

  def getstr: Throwable Either String = Zone { implicit zone: Zone =>
    val str: CString = stackalloc[CChar](256)
    nassert(lowlevel getstr str) match {
      case Right(_)  => Right(fromCString(str))
      case Left(exc) => Left(exc)
    }
  }

  def raw(enable: Boolean = true): Throwable Either Int =
    nassert(if (enable) lowlevel.raw() else lowlevel.noraw())

  def resetProgMode: Throwable Either Int = nassert(lowlevel.reset_prog_mode())

  def resetShellMode: Throwable Either Int = nassert(lowlevel.reset_shell_mode())

  def resetTTY: Throwable Either Int = nassert(lowlevel.resetty())

  def saveProgMode: Throwable Either Int = nassert(lowlevel.def_prog_mode())

  def saveShellMode: Throwable Either Int = nassert(lowlevel.def_shell_mode())

  def saveTTY: Throwable Either Int = nassert(lowlevel.savetty())

  def scrDump(filename: String): Throwable Either Int = Zone { implicit zone: Zone =>
    nassert(lowlevel scr_dump toCString(filename))
  }

  def scrRestore(filename: String): Throwable Either Int = Zone { implicit zone: Zone =>
    nassert(lowlevel scr_restore toCString(filename))
  }

  def setterm(term: String): Throwable Either Int = Zone { implicit zone: Zone =>
    nassert(lowlevel setterm toCString(term))
  }

  def sleep(dur: Duration): Throwable Either Int = napms(dur.toMillis.toInt)

  def termname: Option[String] = Option(lowlevel.termname()) map {fromCString(_)}

  /*--------------------------------------------------------------------------*/

  private[ncurses] def nassert[A](code: A): Throwable Either A = Try(_nassert(code)).toEither

  private def _nassert[A](code: A): A = macro NcursesAssertMacro.impl
}
