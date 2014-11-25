package cxml

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import scala.collection.immutable.TreeMap
import scala.xml._

class CompareXmlSpec extends FlatSpec with ShouldMatchers {

  "CompareXml" should "sort taking into account child nodes" in {
    val xml1 =
      <root>
        <x/>
        <x>
          <abc/>
        </x>
        <x/>
      </root>

    val xml2 =
      <root>
        <x>
          <abc/>
        </x>
        <x/>
        <x/>
      </root>

    xml1 shouldNot be === xml2
    XmlComparator.compare(xml1, xml2) should be === true
  }

  "CompareXml" should "sort attributes" in {
    val xml1 =
      <root>
        <x id="1"/>
        <x id="2"/>
        <x id="3"/>
      </root>

    val xml2 =
      <root>
        <x id="2"/>
        <x id="1"/>
        <x id="3"/>
      </root>

    xml1 shouldNot be === xml2
    XmlComparator.compare(xml1, xml2) should be === true
  }

  "CompareXml" should "work on mid-size sample" in {

    var xml1 =
      <root>
        <level-1 id="id" name="Hello, World">
          <def/>
          <abc/>
          <level-2 id="id2" description="Goodbye, cruel World.">
            <xyz/>
            <allo/>
          </level-2>
          <level-2/>
          <level-2 id="id2" description="Goodbye, cruel World."/>
        </level-1>
      </root>

    var xml2 =
      <root>
        <level-1 id="id" name="Hello, World">
          <def/>
          <abc/>
          <level-2/>
          <level-2 id="id2" description="Goodbye, cruel World."/>
          <level-2 id="id2" description="Goodbye, cruel World.">
            <xyz/>
            <allo/>
          </level-2>
        </level-1>
      </root>

    xml1 shouldNot be === xml2
    XmlComparator.compare(xml1, xml2) should be === true
  }

}
