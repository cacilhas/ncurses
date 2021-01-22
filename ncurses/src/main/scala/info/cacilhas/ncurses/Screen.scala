package info.cacilhas.ncurses

import info.cacilhas.ncurses.lowlevel.SCREEN

import scala.collection.mutable.{Map => MutableMap}
import scala.scalanative.libc.stdio.{FILE, stdin, stdout}
import scala.scalanative.unsafe.{Ptr, Zone, toCString}
import scala.util.chaining.scalaUtilChainingOps

final class Screen private(scr: Ptr[SCREEN]) extends AutoCloseable {

  type callback = (Screen, Any) => Int

  assert(scr != null, "could not initialise screen")
  Screen.instances += scr -> this

  def close(): Unit = {
    Screen.instances -= scr
    lowlevel delscreen scr
  }

  def setTerm: Screen = lowlevel set_term scr pipe Screen.getInstance

  def use(callback: callback, data: Any*): Int = ???
}

object Screen {

  private val instances: Ptr[SCREEN] MutableMap Screen = MutableMap()

  private def getInstance(scr: Ptr[SCREEN]): Screen = instances getOrElse (scr, new Screen(scr))

  def apply(): Screen = apply(stdout, stdin)

  def apply(tpe: String): Screen = apply(tpe, stdout, stdin)

  def apply(outfd: Ptr[FILE], infd: Ptr[FILE]): Screen =
    lowlevel newterm (null, outfd, infd) pipe getInstance

  def apply(tpe: String, outfd: Ptr[FILE], infd: Ptr[FILE]): Screen = Zone { implicit zone =>
      lowlevel newterm (toCString(tpe), outfd, infd) pipe getInstance
    }
}
