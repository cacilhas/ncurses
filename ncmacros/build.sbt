scalaVersion := "2.13.4"

name := "ncmacros"
organization := "info.cacilhas"
version := "1.0.0"
scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked",
  "-language:_",
  "-Xfatal-warnings",
)

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-compiler" % scalaVersion.value,
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
)
