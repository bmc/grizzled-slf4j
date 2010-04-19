---
title: Grizzled-SLF4J, a Scala-friendly SLF4J Wrapper
layout: withTOC
---

## Introduction

The [Grizzled SLF4J][] package provides a very thin Scala-friendly layer
on top of the [SLF4J][] (Simple Logging Façade for Java) API. It is released
under a BSD license.

## Installation

The easiest way to install the Grizzled Scala library is to download a
pre-compiled jar from the [*clapper.org* Maven repository][]. However, you
can also get certain build tools to download it for you.

### Installing for Maven

If you're using [Maven][], you can get Grizzled SLF4J from the
[*clapper.org* Maven Repository][]. The relevant pieces of information are:

* Group ID: `clapper.org`
* Artifact ID: `grizzled-slf4j`
* Version: `0.1`
* Type: `jar`
* Repository: `http://maven.clapper.org/`

Creating the appropriate Maven configuration items is left as an exercise
for the reader.

### Using with SBT

If you're using [SBT][] (the Simple Build Tool) to compile your code, you
can place the following lines in your project file (i.e., the Scala file in
your `project/build/` directory):

    val orgClapperRepo = "clapper.org Maven Repository" at
        "http://maven.clapper.org"
    val grizzled = "org.clapper" % "grizzled-slf4j" % "0.1"

## Source Code Repository

The source code for Grizzled SLF4J is maintained on [GitHub][]. To clone
the repository, run this command:

    git clone git://github.com/bmc/grizzled-slf4j.git

## Building from Source

Building the library requires [SBT][]. Install SBT, as described at the SBT
web site. Then, assuming you have an `sbt` shell script (or .BAT file, for
Windows), run:

    sbt update

That command will pull down the external jars on which the Grizzled Scala
Library depends. After that step, build the library with:

    sbt compile package

The resulting jar file will be in the top-level `target` directory.

## API Documentation

The Scaladoc-generated the [API documentation][] is available locally.
In addition, you can generate your own version with:

    sbt doc

## Author

Brian M. Clapper, [bmc@clapper.org][]

## Copyright and License

The Grizzled Scala Library is copyright &copy; 2010 Brian M. Clapper
and is released under a [BSD License][].

[BSD License]: license.html
[Scala]: http://www.scala-lang.org/
[API Documentation]: apidocs/
[GitHub repository]: http://github.com/bmc/grizzled-slf4j
[GitHub]: http://github.com/bmc/
[downloads area]: http://github.com/bmc/grizzled-scala/downloads
[*clapper.org* Maven repository]: http://maven.clapper.org/org/clapper/
[Maven]: http://maven.apache.org/
[SBT]: http://code.google.com/p/simple-build-tool
[bmc@clapper.org]: mailto:bmc@clapper.org
[Grizzled SLF4J]: http://bmc.github.com/grizzled-scala/
[SLF4J]: http://slf4j.org/

