scalaVersion := "2.13.4"
nativeLinkStubs := true
enablePlugins(ScalaNativePlugin)

name := "ncurses tests"
organization := "info.cacilhas"
version := "1.0.0"
scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked",
  "-language:_",
  "-Xfatal-warnings",
)

mainClass in Compile := Some("info.cacilhas.tests.Main")
