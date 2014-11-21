package cxml

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import scala.xml._

class CompareXmlSpec extends FlatSpec with ShouldMatchers {

  "Hello" should "have tests" in {
    //<test/> should be === <test/>
    //val x = <test/>
    //x.child
    //XmlComparator.compare(<actual/>, <expected/>)

    var xml =
      <root>
        <level-1 id="id" name="Hello, World">
          <abc/>
          <def/>
        </level-1>
      </root>

    println(xml)
    println(sort(xml))

    <test/> should be === <test/>
  }

  private def sort(node: Node): Node = node match {
    case elem: Elem => elem.copy(child = elem.child.map(sort).sorted)
    case _ => node
  }

  implicit val nodes = new Ordering[Node] {
    override def compare(x: Node, y: Node): Int = (x, y) match {
      case (x1: Elem, y1: Elem) => elem.compare(x1, y1)
      case (x1: Text, y1: Text) => x1.text.compare(y1.text)
      case (x1, y1) => (x1.getClass.getName + ":" + x1).compare(y1.getClass.getName + ":" + y1)
    }
  }

  implicit val elem = new Ordering[Elem] {
    override def compare(x: Elem, y: Elem): Int = {
      //      Ordering.`
      ???
      //        this(prefix, label, attributes, scope, child.isEmpty, child: _*)
    }
  }
}
