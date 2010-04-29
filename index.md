---
title: Grizzled-SLF4J, a Scala-friendly SLF4J Wrapper
layout: withTOC
---

## Introduction

The [Grizzled SLF4J][] package provides a very thin Scala-friendly layer
on top of the [SLF4J][] (Simple Logging Façade for Java) API. It is released
under a BSD license.

## Installation

The easiest way to install the Grizzled SLF4J library is to download a
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

That command will pull down the external jars on which the Grizzled SLF4J
Library depends. After that step, build the library with:

    sbt compile package

The resulting jar file will be in the top-level `target` directory.

## Using the Grizzled SLF4J library

Grizzled SLF4J is a thin, Scala-friendly wrapper around SLF4J. It's simpler
to use than direct SLF4J, primarily because it uses Scala's
[call-by-name][] magic so that you can use make logger calls easily,
without incurring any overhead.

For example, the various logging methods are defined as follows:

    def debug(message: => String)

Thus, `debug()` isn't a method taking a string; instead, it's a method taking
a function that *returns* a string. Under the covers, `debug()` does what
you'd expect:

    def debug(message: => String) =
        if (debugIsEnabled) log(message)

However, because `message` is a function that returns a string, it isn't
evaluated until it is called--which is *after* the test that determines
whether it *should* be logged.

Because of Scala's rich syntactic sugar, you can still call `debug()` in
a straightforward way:

    log.debug("Failed to open file \"" + file.getCanonicalPath + "\"")

However, the argument to `debug()` is a function, not a string, so the
string concatenation and the call to `file.getCanonicalPath` do not happen
until and unless the `debug()` function determines that debug logging is
enabled.

Thus, Grizzled SLF4J gives you a simple, Scala-friendly API for SLF4J (with
no need to use SLF4J format strings), while retaining the performance
benefits of delayed evaluation.

### Getting a Logger

To get a logger, use the `Logger` object and pass it a name or a class.
(By convention, the name should be a class name.)

    import grizzled.slf4j.Logger

    val logger = Logger("org.example.foo")

### Logging messages

The `Logger` class supports the following methods:

    def debug(msg => AnyRef, t: => Throwable): Unit
    def debug(msg => AnyRef, t: => Throwable): Unit
    def isDebugEnabled: Boolean

    error(msg => AnyRef, t: => Throwable)
    error(msg => AnyRef, t: => Throwable)
    def isErrorEnabled: Boolean

    info(msg => AnyRef, t: => Throwable)
    info(msg => AnyRef, t: => Throwable)
    def isInfoEnabled: Boolean

    trace(msg => AnyRef, t: => Throwable)
    trace(msg => AnyRef, t: => Throwable)
    def isTraceEnabled: Boolean

    warn(msg => AnyRef, t: => Throwable)
    warn(msg => AnyRef, t: => Throwable)
    def isWarnEnabled: Boolean

See the [API documentation][] for complete details.

## API Documentation

The Scaladoc-generated the [API documentation][] is available locally.
In addition, you can generate your own version with:

    sbt doc

## Author

Brian M. Clapper, [bmc@clapper.org][]

## Copyright and License

Grizzled SLF4J is copyright &copy; 2010 Brian M. Clapper and is released
under a [BSD License][].

## Patches

I gladly accept patches from their original authors. Feel free to email
patches to me or to fork the [GitHub repository][] and send me a pull
request. Along with any patch you send:

* Please state that the patch is your original work.
* Please indicate that you license the work to the Grizzled SLF4J project
  under a [BSD License][].

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
[call-by-name]: http://eed3si9n.com/scala-and-evaluation-strategy
