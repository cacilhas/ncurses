scalaVersion := "2.13.4"

name := "ncurses"
organization := "info.cacilhas"
version := "1.0.0"
scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked",
  "-language:_",
  "-Xfatal-warnings",
)

libraryDependencies += "io.scalaland" %% "enumz" % "1.0.0"
