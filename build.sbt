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

version := "0.6.5"

scalaVersion := "2.8.1"

// ---------------------------------------------------------------------------
// Additional compiler options and plugins

scalacOptions ++= Seq("-deprecation", "-unchecked")

crossScalaVersions := Seq("2.9.1", "2.9.0-1", "2.9.0", "2.8.1", "2.8.0")

// ---------------------------------------------------------------------------
// Other dependendencies

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.6.2"

// ---------------------------------------------------------------------------
// Publishing criteria

// Publish to scala-tools Nexus
publishTo <<= version {(v: String) =>
    val nexus = "http://nexus.scala-tools.org/content/repositories/"
    if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "snapshots/") 
    else                             Some("releases"  at nexus + "releases/")
}

// Credentials for publishing to scala-tools.org
credentials += Credentials(Path.userHome / "src" / "mystuff" / "scala" /
                           "nexus.scala-tools.org.properties")

// Publish as Maven
publishMavenStyle := true

// Don't publish for Test
publishArtifact in Test := false

// Don't publish source jar.
publishArtifact in (Compile, packageSrc) := false

// Don't publish doc jar.
publishArtifact in (Compile, packageDoc) := false

