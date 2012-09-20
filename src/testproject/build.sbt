// ---------------------------------------------------------------------------
// SBT 0.10.x build file for Grizzled SLF4J test project
// ---------------------------------------------------------------------------

// ---------------------------------------------------------------------------
// Basic settings

name := "grizzled-slf4j-test"

organization := "org.clapper"

version := "0.1"

scalaVersion := "2.10.0-M7"

// ---------------------------------------------------------------------------
// Additional compiler options and plugins

scalacOptions ++= Seq("-deprecation", "-unchecked")

// ---------------------------------------------------------------------------
// Other dependendencies

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % "1.7.1",
  "org.clapper" % "avsl_2.10" % "1.0",
  "org.clapper" % "grizzled-slf4j_2.10" % "1.0"
)
