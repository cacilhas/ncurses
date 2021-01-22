scalaVersion := "2.13.4"
nativeLinkStubs := true

scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked",
  "-language:_",
  "-Xfatal-warnings",
)

enablePlugins(ScalaNativePlugin)

lazy val root = project in file(".") aggregate ncurses
lazy val ncurses = project in file("ncurses")
