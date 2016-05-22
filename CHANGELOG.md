# Change Log for grizzled-slf4j

Version 1.0.5:

* Updated version of Lightbend Activator.
* Added support for Travis CI.

Version 1.0.4:

* Built against Scala 2.12.0-M4, instead of -M1.

Version 1.0.3:

* Now compiles against Scala 2.12, as well as 2.11 and 2.10.
* Updated to SBT 0.13.11.
* Added `activator`, for a self-bootstrapping build.
* Updated license to BSD 3-Clause license.
* Updated versions of PGP and Bintray SBT plugins.
* Removed "ls" SBT plugin.

Version 1.0.2:

* Added cross-compilation for Scala 2.11. Updated Scala 2.10 to 2.10.3.
  (Via @takezoe)
* Updated to SLF4J version 1.7.7.
* Artifacts are now published to Bintray.

Version 1.0.1:

* Cross-compiled and published for Scala 2.10.0-RC1.
* Converted to use ScalaTest 2.0, which changes `expect` to `expectResult`.

Version 1.0:

* Now supports Scala 2.10.0-M7, in version 1.0. Prior versions of Scala
  are supported in version 0.6.x (via a Git branch) going forward.
* Upgraded to SBT 0.12.0.
* Upgraded to SLF4J 1.7.1

Version 0.6.9:

* Added Scala 2.9.2 to list of cross-compiled targets.

Version 0.6.8:

* Added Scala 2.9.1-1 to list of cross-compiled targets.

Version 0.6.7:

* Converted to build with SBT 0.11.2.
* Added support for `ls.implicit.ly` metadata.
* Now publishes to `oss.sonatype.org` (and, thence, to the Maven central repo).
* Bumped [SLF4J][] version dependency to SLF4J 1.6.4.

Version 0.6.6:

* Pulled change from [Lucas Torri][] that changes logging method signatures
  to take `Any`, rather than `AnyRef`, allowing more parameter flexibility.
  Also handles `null` better.
  
  [Lucas Torri]: https://github.com/lucastorri

Version 0.6.5:

* Now builds for [Scala][] 2.9.1, as well as 2.9.0-1, 2.9.0, 2.8.1, and 2.8.0.

[Scala]: http://www.scala-lang.org/

Version 0.6.4:

* Upgraded to version 1.6.2 of [SLF4J][].
* Converted code to confirm with standard Scala coding style.
* Backed out change made in version 0.6.1: Published Maven POM now lists
  `slf4j-api` as "compiled" again, instead of "provided". See comments
  in [Grizzled SLF4J Issue #3][].

[SLF4J]: http://slf4j.org/

Version 0.6.3:

Addressed [Issue #5][] via a patch provided by [Lucas Torri][]. Specifically,
the `Logger` object now supports this method:

    def apply[T](implicit m: Manifest[T]): Logger = apply(m.erasure)

Allowing this syntax:

    val logger = Logger[MyClass]

**Note**: As a result of this change, Grizzled SLF4J no longer supports
Scala 2.7.

[Lucas Torri]: https://github.com/lucastorri
[Issue #5]: https://github.com/bmc/grizzled-slf4j/issues/5

Version 0.6.2:

* Per a suggestion from Josh Suereth, the `Logging` class's methods are
  now marked `@inline` and `final`, allowing the Scala compiler to pull them
  inline, if it can, for greater efficiency.

Version 0.6.1:

* Updated SBT build file so that published Maven POM lists the `slf4j-api`
  dependency as "provided". This change addresses
  [Grizzled SLF4J Issue #3][].

[Grizzled SLF4J Issue #3]: https://github.com/bmc/grizzled-slf4j/issues/3

Version 0.6:

* Now builds against Scala 2.9.0-1, as well as Scala 2.9.0, 2.8.1, 2.8.0 and 2.7.7.
* Converted to build with [SBT][] 0.10.1

Version 0.5:

* Updated to build against [Scala][] 2.9.0, as well as the 2.7 and 2.8 series.

[Scala]: http://www.scala-lang.org/
[SLF4J]: http://www.slf4j.org/

Version 0.4:

* Updated to version 1.6.1 of the [SLF4J][] library.
* Added a `Logging` trait that can be mixed into a class, providing logging
  functions and a `Logger`, without changing the public API (similar to the
  [Logging][scalate-logging] trait in the [Scalate][] source). Addresses
  [Issue #1][issue-1].

[Scala]: http://www.scala-lang.org/
[SLF4J]: http://www.slf4j.org/
[scalate-logging]: https://github.com/scalate/scalate/blob/master/scalate-util/src/main/scala/org/fusesource/scalate/util/Logging.scala
[scalate]: http://scalate.fusesource.org/
[issue-1]: https://github.com/bmc/grizzled-slf4j/issues#issue/1

Version 0.3.2:

* Now builds against [Scala][] 2.8.1, as well as 2.8.0 and 2.7.7.

[Scala]: http://www.scala-lang.org/

Version 0.3.1:

* Now compiles against [Scala][] 2.8.1 RC1, as well as 2.8.0 and 2.7.7.

[Scala]: http://www.scala-lang.org/

Version 0.3:

* Now published to the [Scala Tools Maven repository][], which [SBT][]
  includes by default. Thus, if you're using SBT, it's longer necessary to
  specify a custom repository to find this artifact.

[Scala Tools Maven Repository]: http://www.scala-tools.org/repo-releases/
[SBT]: http://code.google.com/p/simple-build-tool/
[Scala]: http://www.scala-lang.org/

Version 0.2.4:

* Now compiles with Scala 2.8.0.final, as well as Scala 2.7.7. 
  Dropped support for all Scala-2.8.0 release candidate (RC) builds.

[SBT]: http://code.google.com/p/simple-build-tool/

Version 0.2.3:

* Now compiles with Scala 2.8.0.RC5, as well as Scala-2.8.0.RC3 and
  Scala 2.7.7. Dropped support for Scala-2.8.0.RC2.

[SBT]: http://code.google.com/p/simple-build-tool/

Version 0.2.2:

* Updated to [SBT][] version 0.7.4.
* Now compiles with Scala 2.8.0.RC3, as well as Scala-2.8.0.RC2 and
  Scala 2.7.7. Dropped support for Scala-2.8.0.RC1.

[SBT]: http://code.google.com/p/simple-build-tool/

Version 0.2.1:

* Updated to [SLF4J][] version 1.6.0.
* Now compiles against Scala 2.8.0.RC2, as well as 2.8.0.RC1 and 2.7.7.

[SLF4J]: http://slf4j.org/

Version 0.2:

* Now cross-built against both Scala 2.8.0.RC1 and Scala 2.7.7, per a
  request from Ermanno Franco (ermanno.franco */at/* gmail.com).

Version 0.1:

* Initial API release.
