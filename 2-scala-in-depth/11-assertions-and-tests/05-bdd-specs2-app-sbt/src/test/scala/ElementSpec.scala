import org.specs2._
import Element.elem

class ElementSpec extends Specification {
  def is = s2"""
    This is a specification to check the UniformElement behavior

    The UniformElement should
      have a width equal to the passed value                                $e1
      have a height equal to the passed value                               $e2
      throw an IllegalArgumentException if passed negative width            $e3

    """

  def e1 = {
    val ele = elem('x', 2, 3)
    ele.width must be_==(2)
  }

  def e2 = {
    val ele = elem('x', 2, 3)
    ele.height must be_==(3)
  }

  def e3 = {
    elem('x', -2, 3) must throwA[IllegalArgumentException]
  }
}
