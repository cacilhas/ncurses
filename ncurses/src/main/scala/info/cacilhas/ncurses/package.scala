package info.cacilhas

import info.cacilhas.macros.NcursesAssertMacro
import info.cacilhas.ncurses.c_header.ERR

import scala.concurrent.duration.Duration
import scala.scalanative.unsafe._

package object ncurses {

  lazy val acs: Seq[Char]  = Option(lowlevel.acs_map) map {_.toSeq} getOrElse Nil map {_.toChar}
  lazy val version: String = fromCString(lowlevel.curses_version())

  def cbreak(enable: Boolean): Int = if (enable) lowlevel.cbreak() else lowlevel.nocbreak()

  def baudrate: Int = lowlevel.baudrate()

  def beep: Int = lowlevel.beep()

  def echo(enable: Boolean): Int = if (enable) lowlevel.echo() else lowlevel.noecho()

  def endwin: Int = lowlevel.endwin()

  def flash: Int = lowlevel.flash()

  def flushinp: Int = lowlevel.flushinp()

  def hasInsertDelete: InsertDeleteCapability = {
    if (lowlevel.has_ic())      InsertDeleteCapability.Yes
    else if (lowlevel.has_il()) InsertDeleteCapability.Emulated
         else                   InsertDeleteCapability.No
  }

  def keyname(key: Int): Option[String] = lowlevel keyname key match {
    case null => None
    case str  => Option(fromCString(str))
  }

  def napms(ms: Int): Int = lowlevel napms ms

  def nl(enable: Boolean): Int = if (enable) lowlevel.nl() else lowlevel.nonl()

  def curSet(visibility: CursorVisibility): Int = lowlevel curs_set visibility.index

  def getch: Char = lowlevel.getch().toChar

  def getstr: Option[String] = Zone { implicit zone: Zone =>
    val str: CString = stackalloc[CChar](256)
    lowlevel getstr str match {
      case ERR => None
      case _   => Option(fromCString(str))
    }
  }

  def raw(enable: Boolean = true): Int = if (enable) lowlevel.raw() else lowlevel.noraw()

  def resetProgMode: Int = lowlevel.reset_prog_mode()

  def resetShellMode: Int = lowlevel.reset_shell_mode()

  def resetTTY: Int = lowlevel.resetty()

  def saveProgMode: Int = lowlevel.def_prog_mode()

  def saveShellMode: Int = lowlevel.def_shell_mode()

  def saveTTY: Int = lowlevel.savetty()

  def scrDump(filename: String): Int = Zone {implicit zone: Zone => lowlevel scr_dump toCString(filename)}

  def scrRestore(filename: String): Int = Zone { implicit zone: Zone =>
    lowlevel scr_restore toCString(filename)
  }

  def setterm(term: String): Int = Zone {implicit zone: Zone => lowlevel setterm toCString(term)}

  def sleep(dur: Duration): Int = napms(dur.toMillis.toInt)

  def termname: Option[String] = Option(lowlevel.termname()) map {fromCString(_)}

  /*--------------------------------------------------------------------------*/

  private[ncurses] def nassert[A](code: A): A = macro NcursesAssertMacro.impl
}
