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

  def flash(): CInt = extern

  def flushinp(): CInt = extern

  def getbkgd(win: Ptr[WINDOW]): UWord = extern

  def getch(): CInt = extern

  def getbegx(win: Ptr[WINDOW]): CInt = extern

  def getbegy(win: Ptr[WINDOW]): CInt = extern

  def getcurx(win: Ptr[WINDOW]): CInt = extern

  def getcury(win: Ptr[WINDOW]): CInt = extern

  def getmaxx(win: Ptr[WINDOW]): CInt = extern

  def getmaxy(win: Ptr[WINDOW]): CInt = extern

  def getparx(win: Ptr[WINDOW]): CInt = extern

  def getpary(win: Ptr[WINDOW]): CInt = extern

  def getstr(str: CString): CInt = extern

  def getwin(filep: Ptr[FILE]): Ptr[WINDOW] = extern

  def has_colors(): CBool = extern

  def has_ic(): CBool = extern

  def has_il(): CBool = extern

  def idlok(win: Ptr[WINDOW], bf: CBool): CInt = extern

  def immedok(win: Ptr[WINDOW], bf: CBool): CInt = extern

  def init_color(idx: CShort, r: CShort, g: CShort, b: CShort): CInt = extern

  def init_pair(idx: CShort, fg: CShort, bg: CShort): CInt = extern

  def initscr(): Ptr[WINDOW] = extern

  def is_cleared(win: Ptr[WINDOW]): CBool = extern

  def is_idlok(win: Ptr[WINDOW]): CBool = extern

  def is_immedok(win: Ptr[WINDOW]): CBool = extern

  def is_keypad(win: Ptr[WINDOW]): CBool = extern

  def is_leaveok(win: Ptr[WINDOW]): CBool = extern

  def is_nodelay(win: Ptr[WINDOW]): CBool = extern

  def is_notimeout(win: Ptr[WINDOW]): CBool = extern

  def is_pad(win: Ptr[WINDOW]): CBool = extern

  def is_scrollok(win: Ptr[WINDOW]): CBool = extern

  def is_subwin(win: Ptr[WINDOW]): CBool = extern

  def is_linetouched(win: Ptr[WINDOW], line: CInt): CBool = extern

  def is_wintouched(win: Ptr[WINDOW]): CBool = extern

  def keyname(c: CInt): CString = extern

  def keypad(win: Ptr[WINDOW], enable: CBool): CInt = extern

  def leaveok(win: Ptr[WINDOW], enabled: CBool): CInt = extern

  def mvwaddch(win: Ptr[WINDOW], y: CInt, x: CInt, ch: UWord): CInt = extern

  def mvwdelch(win: Ptr[WINDOW], y: CInt, x: CInt): CInt = extern

  def mvwgetch(win: Ptr[WINDOW], y: CInt, x: CInt): CInt = extern

  def mvwgetnstr(win: Ptr[WINDOW], y: CInt, x: CInt, str: CString, n: CInt): CInt = extern

  def mvwhline(win: Ptr[WINDOW], y: CInt, x: CInt, ch: UWord, n: CInt): CInt = extern

  def mvwinnstr(win: Ptr[WINDOW], y: CInt, x: CInt, str: CString, n: CInt): CInt = extern

  def mvwvline(win: Ptr[WINDOW], y: CInt, x: CInt, ch: UWord, n: CInt): CInt = extern

  def newwin(nlines: CInt, ncols: CInt, y0: CInt, x0: CInt): Ptr[WINDOW] = extern

  def nl(): CInt = extern

  def napms(ms: CInt): CInt = extern

  def newpad(nlines: CInt, ncols: CInt): Ptr[WINDOW] = extern

  def newterm(tpe: CString, outfd: Ptr[FILE], infd: Ptr[FILE]): Ptr[SCREEN] = extern

  def nocbreak(): CInt = extern

  def nodelay(win: Ptr[WINDOW], enable: CBool): CInt = extern

  def noecho(): CInt = extern

  def nonl(): CInt = extern

  def noraw(): CInt = extern

  def notimeout(win: Ptr[WINDOW], bf: CBool): CInt = extern

  def overlay(scrwin: Ptr[WINDOW], dstwin: Ptr[WINDOW]): CInt = extern

  def putwin(win: Ptr[WINDOW], filep: Ptr[FILE]): CInt = extern

  def raw(): CInt = extern

  def redrawwin(win: Ptr[WINDOW]): CInt = extern

  def reset_prog_mode(): CInt = extern

  def reset_shell_mode(): CInt = extern

  def resetty(): CInt = extern

  def savetty(): CInt = extern

  def scr_dump(filename: CString): CInt = extern

  def scr_restore(filename: CString): CInt = extern

  def scrollok(win: Ptr[WINDOW], bf: CBool): CInt = extern

  def set_term(scr: Ptr[SCREEN]): Ptr[SCREEN] = extern

  def setterm(term: CString): CInt = extern

  def start_color(): CInt = extern

  def termname(): CString = extern

  def touchline(win: Ptr[WINDOW], start: CInt, count: CInt): CInt = extern

  def touchwin(win: Ptr[WINDOW]): CInt = extern

  def untouchline(win: Ptr[WINDOW], start: CInt, count: CInt): CInt = extern

  def untouchwin(win: Ptr[WINDOW]): CInt = extern

  def use_env(b: CBool): Unit = extern

  def use_screen(scr: Ptr[SCREEN], cb: NCURSES_SCREEN_CB, data: Ptr[Byte]): CInt = extern

  def use_tioctl(b: CBool): Unit = extern

  def use_window(win: Ptr[WINDOW], cb: NCURSES_WINDOW_CB, data: Ptr[Byte]): CInt = extern

  def wattroff(win: Ptr[WINDOW], attr: CInt): CInt = extern

  def wattron(win: Ptr[WINDOW], attr: CInt): CInt = extern

  def wbkgd(win: Ptr[WINDOW], color: UWord): CInt = extern

  def wbkgdset(win: Ptr[WINDOW], color: UWord): CInt = extern

  def wchgat(win: Ptr[WINDOW], n: CInt, attr: UWord, color: CShort, opts: Ptr[Byte]): CInt = extern

  def wclear(win: Ptr[WINDOW]): CInt = extern

  def wcrltobot(win: Ptr[WINDOW]): CInt = extern

  def wcrltoeol(win: Ptr[WINDOW]): CInt = extern

  def wdelch(win: Ptr[WINDOW]): CInt = extern

  def wdeleteln(win: Ptr[WINDOW]): CInt = extern

  def werase(win: Ptr[WINDOW]): CInt = extern

  def wgetch(win: Ptr[WINDOW]): CInt = extern

  def winsdelln(win: Ptr[WINDOW], n: CInt): CInt = extern

  def winsertln(win: Ptr[WINDOW]): CInt = extern

  def wmove(win: Ptr[WINDOW], y: CInt, x: CInt): CInt = extern

  def wrefresh(win: Ptr[WINDOW]): CInt = extern

  def wresize(win: Ptr[WINDOW], lines: CInt, columns: CInt): CInt = extern

  def wscrl(win: Ptr[WINDOW], n: CInt): CInt = extern

  def wtimeout(win: Ptr[WINDOW], delay: CInt): Unit = extern
}
