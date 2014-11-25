package cxml

import scala.xml._

object XmlComparator {

  def compare(x: Elem, y: Elem): Boolean = {
    sort(x) == sort(y)
  }

  private def sort(node: Node): Node = node match {
    case elem: Elem => elem.copy(child = elem.child.map(sort).sorted)
    case _ => node
  }

  private implicit lazy val nodesOrdering: Ordering[Node] = new Ordering[Node] {
    override def compare(x: Node, y: Node): Int = (x, y) match {
      case (x1: Elem, y1: Elem) => elemOrdering.compare(x1, y1)
      case (x1: Text, y1: Text) => x1.text.compare(y1.text)
      case (x1, y1) => (x1.getClass.getName + ":" + x1).compare(y1.getClass.getName + ":" + y1)
    }
  }

  private implicit lazy val elemOrdering: Ordering[Elem] = Ordering by { (el: Elem) => (Option(el.prefix), el.label, el.attributes, el.child) }
  private implicit lazy val metaDataOrdering: Ordering[MetaData] = Ordering by { (md: MetaData) => md.asAttrMap.toSeq }
  private implicit def seqOrdering[T](implicit ordering: Ordering[T]): Ordering[Seq[T]] = new Ordering[Seq[T]] {
    override def compare(mx: Seq[T], my: Seq[T]): Int = {
      if (mx.size != my.size) {
        mx.size.compare(my.size)
      } else {
        (mx.sorted zip my.sorted).find { case (x, y) => !ordering.equiv(x, y)} match {
          case Some((x, y)) => ordering.compare(x, y)
          case None => 0
        }
      }
    }
  }
}
