package info.cacilhas.ncurses

import scala.scalanative.unsafe._

@link("ncurses")
@extern
private object lowlevel {

  type WINDOW = extern

  @name("SLcurses_Acs_Map")
  val acs_map: Array[UWord] = extern

  def baudrate(): CInt = extern

  def beep(): CInt = extern

  def box(window: Ptr[WINDOW], vert: UWord, hor: UWord): CInt = extern

  def can_change_color(): CBool = extern

  def cbreak(): CInt = extern

  def clearok(window: Ptr[WINDOW], bf: CBool): CInt = extern

  def color_content(idx: CShort, r: Ptr[CShort], g: Ptr[CShort], b: Ptr[CShort]): CInt = extern

  def COLOR_PAIR(value: CInt): CInt = extern

  def curs_set(visibility: CInt): CInt = extern

  def curses_version(): CString = extern

  def def_prog_mode(): CInt = extern

  def def_shell_mode(): CInt = extern

  def echo(): CInt = extern

  def endwin(): CInt = extern

  def init_color(idx: CShort, r: CShort, g: CShort, b: CShort): CInt = extern

  def init_pair(idx: CShort, fg: CShort, bg: CShort): CInt = extern

  def initscr(): Ptr[WINDOW] = extern

  def keypad(window: Ptr[WINDOW], enable: CBool): CInt = extern

  def mvwaddch(window: Ptr[WINDOW], y: CInt, x: CInt, ch: UWord): CInt = extern

  def mvwdelch(window: Ptr[WINDOW], y: CInt, x: CInt): CInt = extern

  def newwin(nlines: CInt, ncols: CInt, y0: CInt, x0: CInt): Ptr[WINDOW] = extern

  def nl(): CInt = extern

  def napms(ms: CInt): CInt = extern

  def nocbreak(): CInt = extern

  def nodelay(window: Ptr[WINDOW], enable: Boolean): CInt = extern

  def noecho(): CInt = extern

  def nonl(): CInt = extern

  def reset_prog_mode(): CInt = extern

  def reset_shell_mode(): CInt = extern

  def resetty(): CInt = extern

  def savetty(): CInt = extern

  def start_color(): CInt = extern

  def wattroff(window: Ptr[WINDOW], attr: CInt): CInt = extern

  def wattron(window: Ptr[WINDOW], attr: CInt): CInt = extern

  def wchgat(window: Ptr[WINDOW], n: CInt, attr: UWord, color: CShort, opts: Ptr[Byte]): CInt = extern

  def wclear(window: Ptr[WINDOW]): CInt = extern

  def wcrltobot(window: Ptr[WINDOW]): CInt = extern

  def wcrltoeol(window: Ptr[WINDOW]): CInt = extern

  def wdelch(window: Ptr[WINDOW]): CInt = extern

  def wrefresh(window: Ptr[WINDOW]): CInt = extern
}
