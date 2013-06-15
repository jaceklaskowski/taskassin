name := "taskassin"

organization := "pl.japila"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.11" % "test",
  "org.specs2" %% "specs2" % "1.14" % "test",
  "org.scalatest" %% "scalatest" % "1.9.1" % "test"
)

// https://github.com/nscala-time/nscala-time
libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "0.4.2"
