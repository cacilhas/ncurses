package info.cacilhas.ncurses

import scala.scalanative.libc.stdio.FILE
import scala.scalanative.unsafe._

@link("ncurses")
@extern
private object lowlevel {

  type NCURSES_SCREEN_CB = extern
  type NCURSES_WINDOW_CB = extern
  type SCREEN = extern
  type WINDOW = extern

  @name("SLcurses_Acs_Map")
  val acs_map: Array[UWord] = extern

  def baudrate(): CInt = extern

  def beep(): CInt = extern

  def box(win: Ptr[WINDOW], vert: UWord, hor: UWord): CInt = extern

  def can_change_color(): CBool = extern

  def cbreak(): CInt = extern

  def clearok(win: Ptr[WINDOW], bf: CBool): CInt = extern

  def color_content(idx: CShort, r: Ptr[CShort], g: Ptr[CShort], b: Ptr[CShort]): CInt = extern

  def COLOR_PAIR(value: CInt): CInt = extern

  def curs_set(visibility: CInt): CInt = extern

  def curses_version(): CString = extern

  def def_prog_mode(): CInt = extern

  def def_shell_mode(): CInt = extern

  def delscreen(scr: Ptr[SCREEN]): Unit = extern

  def delwin(win: Ptr[WINDOW]): Int = extern

  def echo(): CInt = extern

  def endwin(): CInt = extern

  def init_color(idx: CShort, r: CShort, g: CShort, b: CShort): CInt = extern

  def init_pair(idx: CShort, fg: CShort, bg: CShort): CInt = extern

  def initscr(): Ptr[WINDOW] = extern

  def keypad(win: Ptr[WINDOW], enable: CBool): CInt = extern

  def mvwaddch(win: Ptr[WINDOW], y: CInt, x: CInt, ch: UWord): CInt = extern

  def mvwdelch(win: Ptr[WINDOW], y: CInt, x: CInt): CInt = extern

  def newwin(nlines: CInt, ncols: CInt, y0: CInt, x0: CInt): Ptr[WINDOW] = extern

  def nl(): CInt = extern

  def napms(ms: CInt): CInt = extern

  def newterm(tpe: CString, outfd: Ptr[FILE], infd: Ptr[FILE]): Ptr[SCREEN] = extern

  def nocbreak(): CInt = extern

  def nodelay(win: Ptr[WINDOW], enable: Boolean): CInt = extern

  def noecho(): CInt = extern

  def nonl(): CInt = extern

  def reset_prog_mode(): CInt = extern

  def reset_shell_mode(): CInt = extern

  def resetty(): CInt = extern

  def savetty(): CInt = extern

  def set_term(scr: Ptr[SCREEN]): Ptr[SCREEN] = extern

  def start_color(): CInt = extern

  def use_screen(scr: Ptr[SCREEN], cb: NCURSES_SCREEN_CB, data: Ptr[Byte]): CInt = extern

  def use_window(win: Ptr[WINDOW], cb: NCURSES_WINDOW_CB, data: Ptr[Byte]): CInt = extern

  def wattroff(win: Ptr[WINDOW], attr: CInt): CInt = extern

  def wattron(win: Ptr[WINDOW], attr: CInt): CInt = extern

  def wchgat(win: Ptr[WINDOW], n: CInt, attr: UWord, color: CShort, opts: Ptr[Byte]): CInt = extern

  def wclear(win: Ptr[WINDOW]): CInt = extern

  def wcrltobot(win: Ptr[WINDOW]): CInt = extern

  def wcrltoeol(win: Ptr[WINDOW]): CInt = extern

  def wdelch(win: Ptr[WINDOW]): CInt = extern

  def wrefresh(win: Ptr[WINDOW]): CInt = extern
}
