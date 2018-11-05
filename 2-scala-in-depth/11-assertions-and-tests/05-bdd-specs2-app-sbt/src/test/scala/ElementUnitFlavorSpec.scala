import Element.elem
import org.specs2._

class ElementUnitFlavorSpec extends mutable.Specification {
  "A UniformElement" >> {
    "should have a width equal to the passed value" in {
      val ele = elem('x', 2, 3)
      ele.width must be_==(2)
    }
    "should have a height equal to the passed value" >> { // >> is the same as in
      val ele = elem('x', 2, 3)
      ele.height must be_==(3)
    }
    "should throw an IllegalArgumentException if passed a negative value" in {
      elem('x', -2, 3) must throwA[IllegalArgumentException]
    }
  }
}
