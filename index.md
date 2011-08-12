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
pre-compiled jar from the [Scala Tools Maven repository][]. However, you
can also get certain build tools to download it for you automatically.

### First step: What version of Scala are you using?

Grizzled SLF4J is compiled against Scala 2.7.7 and Scala 2.8.0, via [SBT][]
(the Simple Build Tool), and published to the
[Scala Tools Maven repository][]. The Scala version is embedded in the name
of published artifact, because that's how [SBT][] handles cross-version
compilation. (See the [SBT cross-building][] page for details.)

### Installing with Maven

If you're using [Maven][], you can simply tell Maven to get Grizzled SLF4J
from the [Scala Tools Maven repository][]. The relevant pieces of
information are:

* Group ID: `org.clapper`
* Artifact ID: `grizzled-slf4j_`*scala-version*
* Version: `0.6.2`
* Type: `jar`
* Repository: `http://www.scala-tools.org/repo-releases/`

Your `<repositories>` section will need to contain the Scala Tools repo:

    <repositories>
      <repository>
        <id>scala-tools.org</id>
          <name>Scala-tools Maven2 Repository</name>
          <url>http://scala-tools.org/repo-releases</url>
      </repository>
    </repositories>

Then, you must specify the appropriate Grizzled SLF4J dependency, which
is version-sensitive. For Scala 2.7.7, use:

    <dependency>
      <groupId>org.clapper</groupId>
      <artifactId>grizzled-slf4j_2.7.7</artifactId>
      <version>0.6.2</version>
    </dependency>

For Scala 2.9.0-1, use:

    <dependency>
      <groupId>org.clapper</groupId>
      <artifactId>grizzled-slf4j_2.9.0-1</artifactId>
      <version>0.6.2</version>
    </dependency>

There are versions of this API for Scala 2.7.7, 2.8.0, 2.8.1, 2.9.0 and 2.9.0-1.

For more information on using Maven and Scala, see Josh Suereth's
[Scala Maven Guide][].

### Using with SBT

#### 0.7.x

If you're using [SBT][] 0.7.x to compile your code, you can place the
following line in your project file (i.e., the Scala file in your
`project/build/` directory):

    val grizzled_sl4fj = "org.clapper" %% "grizzled-slf4j" % "0.6.2"

#### 0.10.x

If you're using [SBT][] 0.10.x to compile your code, you can use the
following line in your `build.sbt` file (for Quick Configuration). If
you're using an SBT 0.10.x Full Configuration, you're obviously smart
enough to figure out what to do, on your own.

    libraryDependencies += "org.clapper" %% "grizzled-slf4j" % "0.6.2"

## Building from Source

You can also build Grizzled SLF4J from source. There are two ways to get
the source:

### Downloading a snapshot of the source

You can download a tarball or zip file of the source from the
[downloads page][].

### Source Code Repository

The source code for Grizzled SLF4J is maintained on [GitHub][]. To clone
the repository, run this command:

    git clone git://github.com/bmc/grizzled-slf4j.git

### Building

Building the library requires [SBT][]. Install SBT, as described at the SBT
web site. Then, assuming you have an `sbt` shell script (or .BAT file, for
Windows), run:

    sbt +update

That command will pull down the external jars on which the Grizzled SLF4J
Library depends. After that step, build the library with:

    sbt +compile +package

The resulting jar files will be under the top-level `target` directory, in
subdirectories specific to each Scala version.

## Using the Grizzled SLF4J library

Grizzled SLF4J is a thin, Scala-friendly wrapper around SLF4J. It's simpler
to use than direct SLF4J, primarily because it uses Scala's
[call-by-name][] magic so that you can use make logger calls easily,
without incurring any overhead.

For example, the various logging methods are defined as follows:

    @inline final def debug(message: => String)

Thus, `debug()` isn't a method taking a string; instead, it's a method taking
a function that *returns* a string. Under the covers, `debug()` does what
you'd expect:

    @inline final def debug(message: => String) =
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

Note, too, that the method definitions are annotated with `@inline final`,
giving the Scala compiler the opportunity to pull them inline, for greater
efficiency.

Thus, Grizzled SLF4J gives you a simple, Scala-friendly API for SLF4J (with
no need to use SLF4J format strings), while retaining the performance
benefits of delayed evaluation.

### Getting a Logger

To get a logger, use the `Logger` object and pass it a name or a class.
(By convention, the name should be a class name.)

    import grizzled.slf4j.Logger

    val logger = Logger("org.example.foo")

### Mixing in the Logging Trait

As of version 0.4, this API supports a `Logging` trait. Instead of
instantiating a `Logger` object and invoking its methods explicitly, you
can mix the `Logging` trait into any class, which automatically:

* adds logging methods (`debug`, `info`, `error`, etc.)
* adds a lazily-instantiated `Logger` object
* adds a `logger` property that you can use to retrieve the `Logger` object

*without* changing the class's public API.

This approach is similar to the `Logging` trait in the [Scalate][] source.
(See Scalate's [Logging.scala][scalate-logging].)

Example:

    class Foo extends Logging
    {
        def test: Unit =
            info("test")
    }

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

## Change log

The change log for all releases is [here][changelog].

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
[API Documentation]: api/
[GitHub repository]: http://github.com/bmc/grizzled-slf4j
[GitHub]: http://github.com/bmc/
[downloads page]: http://github.com/bmc/grizzled-slf4j/downloads
[Scala Tools Maven repository]: http://www.scala-tools.org/repo-releases/
[Scala Maven Guide]: http://www.scala-lang.org/node/345
[Maven]: http://maven.apache.org/
[SBT]: http://code.google.com/p/simple-build-tool
[bmc@clapper.org]: mailto:bmc@clapper.org
[Grizzled SLF4J]: http://software.clapper.org/grizzled-slf4j/
[SLF4J]: http://slf4j.org/
[call-by-name]: http://eed3si9n.com/scala-and-evaluation-strategy
[SBT cross-building]: http://code.google.com/p/simple-build-tool/wiki/CrossBuild
[changelog]: CHANGELOG.html
