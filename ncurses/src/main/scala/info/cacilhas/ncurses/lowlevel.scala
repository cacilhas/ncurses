package info.cacilhas.ncurses

import scala.scalanative.unsafe._

@link("ncurses")
@extern
private object lowlevel {

  type WINDOW = extern

  @name("SLcurses_Acs_Map")
  val acs_map: Array[UWord] = extern

  def cbreak(): CInt = extern

  def COLOR_PAIR(value: CInt): CInt = extern

  def curses_version(): CString = extern

  def echo(): CInt = extern

  def endwin(): CInt = extern

  def init_color(idx: CShort, red: CShort, green: CShort, blue: CShort): CInt = extern

  def init_pair(idx: CShort, foreground: CShort, background: CShort): CInt = extern

  def initscr(): WINDOW = extern

  def keypad(window: WINDOW, enable: CBool): CInt = extern

  def mvwaddch(window: WINDOW, y: CInt, x: CInt, ch: UWord): CInt = extern

  def newwin(nlines: CInt, ncols: CInt, y0: CInt, x0: CInt): WINDOW = extern

  def nl(): CInt = extern

  def nocbreak(): CInt = extern

  def nodelay(window: WINDOW, enable: Boolean): CInt = extern

  def noecho(): CInt = extern

  def nonl(): CInt = extern

  def start_color(): CInt = extern

  def wattroff(window: WINDOW, attr: CInt): CInt = extern

  def wattron(window: WINDOW, attr: CInt): CInt = extern

  def wclear(window: WINDOW): CInt = extern

  def wrefresh(window: WINDOW): CInt = extern
}
