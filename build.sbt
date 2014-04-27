// ---------------------------------------------------------------------------
// SBT 0.10.x build file for Grizzled SLF4J
// (http://software.clapper.org/grizzled-slf4j)
//
// Copyright (c) 2010-2011 Brian M. Clapper
//
// See accompanying license file for Grizzled SLF4J license.
// ---------------------------------------------------------------------------

// ---------------------------------------------------------------------------
// Basic settings

name := "grizzled-slf4j"

organization := "org.clapper"

version := "1.0.2"

licenses := Seq("BSD" -> url("http://software.clapper.org/grizzled-slf4j/license.html"))

homepage := Some(url("http://software.clapper.org/grizzled-slf4j/"))

description := "A Scala-friendly wrapper for the SLF4J logging framework"

scalaVersion := "2.11.0"

crossScalaVersions := Seq("2.10.3", "2.11.0")

// ---------------------------------------------------------------------------
// Additional compiler options and plugins

scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature")

lsSettings

(LsKeys.tags in LsKeys.lsync) := Seq(
  "log", "logger", "logging", "slf4j", "grizzled", "wrapper"
)

(description in LsKeys.lsync) <<= description(d => d)

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "2.1.3" % "test",
    "org.slf4j" % "slf4j-api" % "1.7.7"
)

bintraySettings

bintray.Keys.packageLabels in bintray.Keys.bintray := (LsKeys.tags in LsKeys.lsync).value

externalResolvers in LsKeys.lsync := (resolvers in bintray.Keys.bintray).value

homepage := Some(url("https://github.com/bmc/%s/#readme".format(name.value)))

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
