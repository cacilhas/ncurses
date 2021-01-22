package info.cacilhas.ncurses

import scala.scalanative.unsafe._

@link ("ncurses")
@extern
@name("ncurses")
private object lowlevel {

  type WINDOW = Ptr[CChar]

  val NCURSES_VERSION_MAJOR: CInt = extern
  val NCURSES_VERSION_MINOR: CInt = extern
  val NCURSES_VERSION_PATCH: CInt = extern

  def cbreak(): CInt = extern

  def echo(): CInt = extern

  def endwin(): CInt = extern

  def initscr(): WINDOW = extern

  def keypad(window: WINDOW, enable: CBool): CInt = extern

  def mvwaddch(window: WINDOW, y: CInt, x: CInt, ch: UWord): CInt = extern

  def newwin(nlines: CInt, ncols: CInt, y0: CInt, x0: CInt): WINDOW = extern

  def nl(): CInt = extern

  def nocbreak(): CInt = extern

  def nodelay(window: WINDOW, enable: Boolean): CInt = extern

  def noecho(): CInt = extern

  def nonl(): CInt = extern

  def wclear(window: WINDOW): CInt = extern

  def wrefresh(window: WINDOW): CInt = extern
}
