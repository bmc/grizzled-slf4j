import grizzled.slf4j._

import java.util.Date

private case class Foo(i: Int, s: String) {
  override def toString = "Foo{i=%d, s=%s}" format (i, s)
}

object Test {
  def main(args: Array[String]) {
    val log = Logger[this.type]

    val messageData = List(10,
                           100.0f,
                           Foo(199, "blather"),
                           "a string",
                           new Date)

    messageData.foreach(log.info(_))
    messageData.foreach(log.warn(_))
    messageData.foreach(log.debug(_))
  }
}
