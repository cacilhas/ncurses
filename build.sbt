scalaVersion := "2.13.4"
nativeLinkStubs := true
enablePlugins(ScalaNativePlugin)

scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked",
  "-language:_",
  "-Xfatal-warnings",
)

lazy val root     = project in file(".") aggregate (ncurses, tests)
lazy val ncmacros = project in file("ncmacros")
lazy val ncurses  = project in file("ncurses") dependsOn ncmacros
lazy val tests    = project in file("tests") dependsOn ncurses

addCommandAlias("test", "tests / run")
