// ---------------------------------------------------------------------------
// SBT 0.10.x build file for Grizzled SLF4J
// (http://software.clapper.org/grizzled-slf4j)
//
// Copyright (c) 2010-2016 Brian M. Clapper
//
// See accompanying license file for Grizzled SLF4J license.
// ---------------------------------------------------------------------------

// ---------------------------------------------------------------------------
// Basic settings

name := "grizzled-slf4j"

organization := "org.clapper"

version := "1.3.2"

licenses := Seq("BSD" -> url("http://software.clapper.org/grizzled-slf4j/license.html"))

homepage := Some(url("http://software.clapper.org/grizzled-slf4j/"))

description := "A Scala-friendly wrapper for the SLF4J logging framework"

scalaVersion := "2.11.12"

crossScalaVersions := Seq("2.10.7", "2.11.12", "2.12.8")

// ---------------------------------------------------------------------------
// Helpers

// Take a dependency and map its cross-compiled version, creating a new
// dependency. Temporary, until Scala 2.12 is for real.
/*
def mappedDep(dep: sbt.ModuleID): sbt.ModuleID = {
  dep cross CrossVersion.binaryMapped {
    case v if v startsWith "2.12" => "2.11"
    case v => v.split("""\.""").take(2).mkString(".")
  }
}
*/

// ---------------------------------------------------------------------------
// Additional compiler options and plugins

scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature")

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.4" % Test,
    "org.slf4j"      % "slf4j-api" % "1.7.9"
)

// ---------------------------------------------------------------------------
// Publishing criteria

// Don't set publishTo. The Bintray plugin does that automatically.

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <scm>
    <url>git@github.com:bmc/grizzled-slf4j.git/</url>
    <connection>scm:git:git@github.com:bmc/grizzled-slf4j.git</connection>
  </scm>
  <developers>
    <developer>
      <id>bmc</id>
      <name>Brian Clapper</name>
      <url>https://github.com/bmc</url>
    </developer>
  </developers>
)
