package info.cacilhas.macros

import scala.reflect.macros.blackbox

object NcursesAssertMacro {

  def impl(c: blackbox.Context)(code: c.Tree): c.Tree = {
    import c.universe._
    val text = code.toString
    q"""{
      val res = ($code)
      assert(res != -1, $text)
      res
    }"""
  }
}
