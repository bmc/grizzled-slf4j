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

version := "1.3.4"

licenses := Seq("BSD" -> url("http://software.clapper.org/grizzled-slf4j/license.html"))

homepage := Some(url("http://software.clapper.org/grizzled-slf4j/"))

description := "A Scala-friendly wrapper for the SLF4J logging framework"

ThisBuild / scalaVersion := "3.1.0"

crossScalaVersions := Seq("2.10.7", "2.11.12", "2.12.15", "2.13.6", "3.1.0")

// ---------------------------------------------------------------------------
// Helpers

// ---------------------------------------------------------------------------
// Additional compiler options and plugins

scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature")

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.2.10" % Test,
    "org.slf4j"      % "slf4j-api" % "1.7.33"
)

// ---------------------------------------------------------------------------
// Publishing criteria

// Don't set publishTo. The Bintray plugin does that automatically.

publishMavenStyle := true

Test / publishArtifact  := false

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
