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

crossScalaVersions := Seq("2.9.0-1", "2.9.0", "2.8.1", "2.8.0")

// ---------------------------------------------------------------------------
// Posterous-SBT

// Only supported for 2.8.1 right now.
libraryDependencies <<= (sbtVersion, scalaVersion, libraryDependencies) { (sbtv, scalav, deps) =>
    if (scalav == "2.8.1")
        deps :+ "net.databinder" %% "posterous-sbt" % ("0.3.0_sbt" + sbtv)
    else
        deps
}

(name in Posterous) := "Grizzled SLF4J"

// ---------------------------------------------------------------------------
// Other dependendencies

// Issue #3
//
// Set the "scope" on dependencies to "provided", indicating that the user
// is responsible for providing those dependencies. This prevents someone
// else's Maven from sucking those jars into any WAR files it creates.
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.6.2" % "provided"

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

