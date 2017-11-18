/*
  BDD Test Specs
*/

import org.scalatest.FlatSpec
import org.scalatest.Matchers

import Element.elem

class ElementSpec extends FlatSpec with Matchers {

  "A UniformElement" should "have width equal to the passed value" in {
    val e = elem('x', 2, 3)
    e.width should be (2)
  }

  it should "have height equal to the passed value" in {
    val e = elem('x', 2, 3)
    e.height should be (4)
  }

  // this one will fail
  it should "throw an IllegalArgumentException if passed a negative width" in {
    an [IllegalArgumentException] should be thrownBy {
      elem('x', -2, 3)
    }
  }
}