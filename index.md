---
title: Grizzled-SLF4J, a Scala-friendly SLF4J Wrapper
layout: withTOC
---

## Introduction

The [Grizzled SLF4J][] package provides a very thin Scala-friendly layer
on top of the [SLF4J][] (Simple Logging Façade for Java) API. It is released
under a BSD license.

## Installation

Grizzled SLF4J is published to the
[Bintray Maven repository](https://bintray.com/bmc/maven), which is
automatically linked to Bintray's [JCenter](https://bintray.com/bintray/jcenter)
repository. (From JCenter, it's eventually pushed to the
[Maven Central Repository][].)

* Version 1.3.4 supports Scala 2.13.0 (final), Scala 2.12.x, Scala 2.11.x and 2.10
* Version 1.3.3 supports Scala 2.13.0 (built against -M5), Scala 2.12.x, Scala 2.11.x and 2.10
* Versions 1.3.0 through 1.3.2 support Scala 2.12.x, Scala 2.11.x and 2.10
* Version 1.2.0 supports Scala 2.12-RC1, Scala 2.11 and 2.10
* Version 1.1.0 supports Scala 2.12-M5, Scala 2.11 and 2.10
* Versions 1.0.4 and 1.0.5 support Scala 2.12-M4, 2.11 and 2.10
* Versions 1.0.2 and 1.0.3 support Scala 2.12-M1, 2.11 and 2.10
* Version 1.0.1 supports Scala 2.10.0-RC1
* Version 1.0 supports Scala 2.10.0-M7
* Version 0.6.10 supports Scala 2.9.2, 2.9.1-1, 2.9.1, 2.9.0-1, 2.9.0, 2.8.2,
  2.8.1 and 2.8.0.

### Installing with Maven

If you're using [Maven][], just specify the artifact, and Maven will do the
rest for you:

* Group ID: `org.clapper`
* Artifact ID: `grizzled-slf4j_`*scala-version*
* Version: The usual
* Type: `jar`

For example:

    <dependency>
      <groupId>org.clapper</groupId>
      <artifactId>grizzled-slf4j_2.11</artifactId>
      <version>1.3.4</version>
    </dependency>

If you cannot resolve the artifact, then add the JCenter repository:

    <repositories>
      <repository>
        <snapshots>
          <enabled>false</enabled>
        </snapshots>
        <id>central</id>
        <name>bintray</name>
        <url>http://jcenter.bintray.com</url>
      </repository>
      ...
    </repositories>

For more information on using Maven and Scala, see Josh Suereth's
[Scala Maven Guide][].

### Using with SBT

Add the following to your SBT build:

    libraryDependencies += "org.clapper" %% "grizzled-slf4j" % "1.3.4"

## Building from Source

You can also build Grizzled SLF4J from source. There are two ways to get
the source:

### Downloading a snapshot of the source

You can download a tarball or zip file of the source from the
[downloads page][].

### Source Code Repository

The source code for Grizzled SLF4J is maintained on [GitHub][]. To clone
the repository, run this command:

    $ git clone git://github.com/bmc/grizzled-slf4j.git

### Building

Building the library requires [SBT][] 0.13.x, but you don't have to
install it (unless you're building on Windows). Instead, just use the
`./activator` script at the top level of the repository. The script,
part of [Lightbend Activator](https://www.lightbend.com/activator/download),
automatically downloads the appropriate versions of SBT and Scala for
you. (You _do_ need to have an installed Java JDK. I recommend 1.8.)

You can build with this one simple command:

    ./activator +compile +package

The resulting jar files will be under the top-level `target` directory, in
subdirectories specific to each Scala version.

## Using the Grizzled SLF4J library

Grizzled SLF4J is a thin, Scala-friendly wrapper around SLF4J. It's simpler
to use than direct SLF4J, primarily because it uses Scala's
[call-by-name][] magic so that you can use make logger calls easily,
without incurring any overhead.

For example, the various logging methods are defined as follows:

    @inline final def debug(message: => Any)

Thus, `debug()` isn't a method taking a string; instead, it's a method taking
a function that *returns* a string. Under the covers, `debug()` does what
you'd expect:

    @inline final def debug(message: => Any) =
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

To get a logger, use the `Logger` object and pass it a name, a class, or a
type. (By convention, a name should be a class name.) Examples:

**Using a name:**

```scala
import grizzled.slf4j.Logger

class Foo {
  val logger = Logger("org.example.foo")
}
```

**Using a class:**

```scala
import grizzled.slf4j.Logger

class Foo {
  val logger = Logger(classOf[Foo])
}
```

**Using a type:**

```scala
import grizzled.slf4j.Logger

class Foo {
  val logger = Logger[this.type] // or Logger[Foo]
}
```

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

[Scalate]: http://scalate.fusesource.org/
[scalate-logging]: https://github.com/scalate/scalate/blob/master/scalate-util/src/main/scala/org/fusesource/scalate/util/Logging.scala

Example:

    class Foo extends Logging {
      def test: Unit =
        info("test")
    }

### Logging messages

The `Logger` class supports the following methods:

    def debug(msg => Any, t: => Throwable): Unit
    def debug(msg => Any, t: => Throwable): Unit
    def isDebugEnabled: Boolean

    error(msg => Any, t: => Throwable)
    error(msg => Any, t: => Throwable)
    def isErrorEnabled: Boolean

    info(msg => Any, t: => Throwable)
    info(msg => Any, t: => Throwable)
    def isInfoEnabled: Boolean

    trace(msg => Any, t: => Throwable)
    trace(msg => Any, t: => Throwable)
    def isTraceEnabled: Boolean

    warn(msg => Any, t: => Throwable)
    warn(msg => Any, t: => Throwable)
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
[Maven central repository]: http://search.maven.org/
[Scala Maven Guide]: http://www.scala-lang.org/node/345
[Maven]: http://maven.apache.org/
[SBT]: http://code.google.com/p/simple-build-tool
[bmc@clapper.org]: mailto:bmc@clapper.org
[Grizzled SLF4J]: http://software.clapper.org/grizzled-slf4j/
[SLF4J]: http://slf4j.org/
[call-by-name]: http://eed3si9n.com/scala-and-evaluation-strategy
[SBT cross-building]: http://code.google.com/p/simple-build-tool/wiki/CrossBuild
[changelog]: https://github.com/bmc/grizzled-slf4j/blob/master/CHANGELOG.md
[ls.implicit.ly]: http://ls.implicit.ly
