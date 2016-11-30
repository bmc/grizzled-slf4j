/*
  ---------------------------------------------------------------------------
  This software is released under a BSD license, adapted from
  http://opensource.org/licenses/bsd-license.php

  Copyright (c) 2010, Brian M. Clapper
  All rights reserved.

  Redistribution and use in source and binary forms, with or without
  modification, are permitted provided that the following conditions are
  met:

   * Redistributions of source code must retain the above copyright notice,
    this list of conditions and the following disclaimer.

   * Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.

   * Neither the names "clapper.org", "AVSL", nor the names of its
    contributors may be used to endorse or promote products derived from
    this software without specific prior written permission.

  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
  IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
  THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
  PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
  ---------------------------------------------------------------------------
*/

/** Scala front-end to SLF4J API.
  */
package grizzled.slf4j

import org.slf4j.{Logger => SLF4JLogger}
import org.slf4j.Marker

/** Scala front-end to a SLF4J logger.
  */
class Logger(val logger: SLF4JLogger) {
  import scala.language.implicitConversions

  /** Get the name associated with this logger.
    *
    * @return the name.
    */
  @inline final def name = logger.getName

  /** Determine whether trace logging is enabled.
    */
  @inline final def isTraceEnabled = logger.isTraceEnabled

  /** Issue a trace logging message.
    *
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    */
  @inline final def trace(msg: => Any): Unit =
    if (isTraceEnabled) logger.trace(msg.toString)

  /** Issue a trace logging message, with an exception.
    *
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    * @param t    the exception to include with the logged message.
    */
  @inline final def trace(msg: => Any, t: => Throwable): Unit =
    if (isTraceEnabled) logger.trace(msg, t)

  /** Issue a trace logging message, with a marker and an exception.
    *
    * @param mkr  the slf4j marker object.
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    * @param t    the exception to include with the logged message.
    */
  @inline final def trace(mkr: Marker, msg: => Any, t: => Throwable): Unit =
    if (isTraceEnabled) logger.trace(mkr, msg, t)

  /** Determine whether debug logging is enabled.
    */
  @inline final def isDebugEnabled = logger.isDebugEnabled

  /** Issue a debug logging message.
    *
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    */
  @inline final def debug(msg: => Any): Unit =
    if (isDebugEnabled) logger.debug(msg.toString)

  /** Issue a debug logging message, with an exception.
    *
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    * @param t    the exception to include with the logged message.
    */
  @inline final def debug(msg: => Any, t: => Throwable): Unit =
    if (isDebugEnabled) logger.debug(msg, t)

  /** Issue a debug logging message, with a marker and an exception.
    *
    * @param mkr  the slf4j marker object.
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    * @param t    the exception to include with the logged message.
    */
  @inline final def debug(mkr: Marker, msg: => Any, t: => Throwable): Unit =
    if (isDebugEnabled) logger.debug(mkr, msg, t)

  /** Determine whether error logging is enabled.
    */
  @inline final def isErrorEnabled = logger.isErrorEnabled

  /** Issue a error logging message.
    *
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    */
  @inline final def error(msg: => Any): Unit =
    if (isErrorEnabled) logger.error(msg.toString)

  /** Issue a error logging message, with an exception.
    *
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    * @param t    the exception to include with the logged message.
    */
  @inline final def error(msg: => Any, t: => Throwable): Unit =
    if (isErrorEnabled) logger.error(msg, t)

  /** Issue a error logging message, with a marker and an exception.
    *
    * @param mkr  the slf4j marker object.
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    * @param t    the exception to include with the logged message.
    */
  @inline final def error(mkr: Marker, msg: => Any, t: => Throwable): Unit =
    if (isErrorEnabled) logger.error(mkr, msg, t)

  /** Determine whether info logging is enabled.
    */
  @inline final def isInfoEnabled = logger.isInfoEnabled

  /** Issue a info logging message.
    *
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    */
  @inline final def info(msg: => Any): Unit =
    if (isInfoEnabled) logger.info(msg.toString)

  /** Issue a info logging message, with an exception.
    *
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    * @param t    the exception to include with the logged message.
    */
  @inline final def info(msg: => Any, t: => Throwable): Unit =
    if (isInfoEnabled) logger.info(msg, t)

  /** Issue a info logging message, with a marker and an exception.
    *
    * @param mkr  the slf4j marker object.
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    * @param t    the exception to include with the logged message.
    */
  @inline final def info(mkr: Marker, msg: => Any, t: => Throwable): Unit =
    if (isInfoEnabled) logger.info(mkr, msg, t)

  /** Determine whether warn logging is enabled.
    */
  @inline final def isWarnEnabled = logger.isWarnEnabled

  /** Issue a warn logging message.
    *
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    */
  @inline final def warn(msg: => Any): Unit =
    if (isWarnEnabled) logger.warn(msg.toString)

  /** Issue a warn logging message, with an exception.
    *
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    * @param t    the exception to include with the logged message.
    */
  @inline final def warn(msg: => Any, t: => Throwable): Unit =
    if (isWarnEnabled) logger.warn(msg, t)

  /** Issue a warn logging message, with a marker and an exception.
    *
    * @param mkr  the slf4j marker object.
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    * @param t    the exception to include with the logged message.
    */
  @inline final def warn(mkr: Marker, msg: => Any, t: => Throwable): Unit =
    if (isWarnEnabled) logger.warn(mkr, msg, t)

  /** Converts any type to a String. In case the object is null, a null
    * String is returned. Otherwise the method `toString()` is called.
    *
    * @param msg  the message object to be converted to String
    *
    * @return the String representation of the message.
    */
  private implicit def _any2String(msg: Any): String =
    msg match {
      case null => "<null>"
      case _    => msg.toString
    }
}

/** Mix the `Logging` trait into a class to get:
  *
  * - Logging methods
  * - A `Logger` object, accessible via the `log` property
  *
  * Does not affect the public API of the class mixing it in.
  */
trait Logging {
  // The logger. Instantiated the first time it's used.
  @transient private lazy val _logger = Logger(getClass)

  /** Get the `Logger` for the class that mixes this trait in. The `Logger`
    * is created the first time this method is call. The other methods (e.g.,
    * `error`, `info`, etc.) call this method to get the logger.
    *
    * @return the `Logger`
    */
  protected def logger: Logger = _logger

  /** Get the name associated with this logger.
    *
    * @return the name.
    */
  protected def loggerName = logger.name

  /** Determine whether trace logging is enabled.
    */
  protected def isTraceEnabled = logger.isTraceEnabled

  /** Issue a trace logging message.
    *
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    */
  protected def trace(msg: => Any): Unit = logger.trace(msg)

  /** Issue a trace logging message, with an exception.
    *
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    * @param t    the exception to include with the logged message.
    */
  protected def trace(msg: => Any, t: => Throwable): Unit =
    logger.trace(msg, t)

  /** Issue a trace logging message, with a marker and an exception.
    *
    * @param mkr  the slf4j marker object.
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    * @param t    the exception to include with the logged message.
    */
  protected def trace(mkr: Marker, msg: => Any, t: => Throwable): Unit =
    logger.trace(mkr, msg, t)

  /** Determine whether debug logging is enabled.
    */
  protected def isDebugEnabled = logger.isDebugEnabled

  /** Issue a debug logging message.
    *
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    */
  protected def debug(msg: => Any): Unit = logger.debug(msg)

  /** Issue a debug logging message, with an exception.
    *
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    * @param t    the exception to include with the logged message.
    */
  protected def debug(msg: => Any, t: => Throwable): Unit =
    logger.debug(msg, t)

  /** Issue a debug logging message, with a marker and an exception.
    *
    * @param mkr  the slf4j marker object.
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    * @param t    the exception to include with the logged message.
    */
  protected def debug(mkr: Marker, msg: => Any, t: => Throwable): Unit =
    logger.debug(mkr, msg, t)

  /** Determine whether trace logging is enabled.
    */
  protected def isErrorEnabled = logger.isErrorEnabled

  /** Issue a trace logging message.
    *
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    */
  protected def error(msg: => Any): Unit = logger.error(msg)

  /** Issue a trace logging message, with an exception.
    *
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    * @param t    the exception to include with the logged message.
    */
  protected def error(msg: => Any, t: => Throwable): Unit =
    logger.error(msg, t)

  /** Issue a error logging message, with a marker and an exception.
    *
    * @param mkr  the slf4j marker object.
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    * @param t    the exception to include with the logged message.
    */
  protected def error(mkr: Marker, msg: => Any, t: => Throwable): Unit =
    logger.error(mkr, msg, t)

  /** Determine whether trace logging is enabled.
    */
  protected def isInfoEnabled = logger.isInfoEnabled

  /** Issue a trace logging message.
    *
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    */
  protected def info(msg: => Any): Unit = logger.info(msg)

  /** Issue a trace logging message, with an exception.
    *
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    * @param t    the exception to include with the logged message.
    */
  protected def info(msg: => Any, t: => Throwable): Unit =
    logger.info(msg, t)

  /** Issue a info logging message, with a marker and an exception.
    *
    * @param mkr  the slf4j marker object.
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    * @param t    the exception to include with the logged message.
    */
  protected def info(mkr: Marker, msg: => Any, t: => Throwable): Unit =
    logger.info(mkr, msg, t)

  /** Determine whether trace logging is enabled.
    */
  protected def isWarnEnabled = logger.isWarnEnabled

  /** Issue a trace logging message.
    *
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    */
  protected def warn(msg: => Any): Unit = logger.warn(msg)

  /** Issue a trace logging message, with an exception.
    *
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    * @param t    the exception to include with the logged message.
    */
  protected def warn(msg: => Any, t: => Throwable): Unit =
    logger.warn(msg, t)

  /** Issue a warn logging message, with a marker and an exception.
    *
    * @param mkr  the slf4j marker object.
    * @param msg  the message object. `toString()` is called to convert it
    *             to a loggable string.
    * @param t    the exception to include with the logged message.
    */
  protected def warn(mkr: Marker, msg: => Any, t: => Throwable): Unit =
    logger.warn(mkr, msg, t)
}

/** A factory for retrieving an SLF4JLogger.
  */
object Logger {
  import scala.reflect.{classTag, ClassTag}

  /** The name associated with the root logger.
    */
  val RootLoggerName = SLF4JLogger.ROOT_LOGGER_NAME

  /** Get the logger with the specified name. Use `RootName` to get the
    * root logger.
    *
    * @param name  the logger name
    *
    * @return the `Logger`.
    */
  def apply(name: String): Logger =
    new Logger(org.slf4j.LoggerFactory.getLogger(name))

  /** Get the logger for the specified class, using the class's fully
    * qualified name as the logger name.
    *
    * @param cls  the class
    *
    * @return the `Logger`.
    */
  def apply(cls: Class[_]): Logger = apply(cls.getName)

  /** Get the logger for the specified class type, using the class's fully
    * qualified name as the logger name.
    *
    * @return the `Logger`.
    */
  def apply[C: ClassTag](): Logger = apply(classTag[C].runtimeClass.getName)

  /** Get the root logger.
    *
    * @return the root logger
    */
  def rootLogger = apply(RootLoggerName)
}
