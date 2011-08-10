// ---------------------------------------------------------------------------
// Basic settings

name := "grizzled-slf4j"

organization := "org.clapper"

version := "0.6"

scalaVersion := "2.8.1"

// ---------------------------------------------------------------------------
// Additional compiler options and plugins

scalacOptions ++= Seq("-deprecation", "-unchecked")

crossScalaVersions := Seq("2.9.0-1", "2.9.0", "2.8.1",  "2.8.0", "2.7.7")

// ---------------------------------------------------------------------------
// Posterous-SBT

libraryDependencies <<= (sbtVersion, scalaVersion, libraryDependencies) { (sbtv, scalav, deps) =>
    if (scalav == "2.8.1")
        deps :+ "net.databinder" %% "posterous-sbt" % ("0.3.0_sbt" + sbtv)
    else
        deps
}

(name in Posterous) := "Grizzled SLF4J"

// ---------------------------------------------------------------------------
// Other dependendencies

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.6.1"

// ---------------------------------------------------------------------------
// Publishing criteria

publishTo <<= version {(v: String) =>
    val nexus = "http://nexus.scala-tools.org/content/repositories/"
    if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "snapshots/") 
    else                             Some("releases"  at nexus + "releases/")
}

publishMavenStyle := true

credentials += Credentials(Path.userHome / "src" / "mystuff" / "scala" /
                           "nexus.scala-tools.org.properties")
