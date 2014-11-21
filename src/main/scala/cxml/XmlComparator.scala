package cxml

import scala.xml._

object XmlComparator {

  sealed trait NNode[T] {
    val node: T
  }

  case class Something(
    id: Int,
    name: String,
    description: String,
    comment: String)

  def compare(actual: Elem, expected: Elem): Either[String, Boolean] = {
    ???
  }
}
