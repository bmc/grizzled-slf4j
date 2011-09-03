// ---------------------------------------------------------------------------
// SBT 0.10.x build file for Grizzled SLF4J test project
// ---------------------------------------------------------------------------

// ---------------------------------------------------------------------------
// Basic settings

name := "grizzled-slf4j-test"

organization := "org.clapper"

version := "0.1"

scalaVersion := "2.9.1"

// ---------------------------------------------------------------------------
// Additional compiler options and plugins

scalacOptions ++= Seq("-deprecation", "-unchecked")

// ---------------------------------------------------------------------------
// Other dependendencies

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % "1.6.2",
  "org.clapper" %% "avsl" % "0.3.5",
  "org.clapper" %% "grizzled-slf4j" % "0.6.6"
)
