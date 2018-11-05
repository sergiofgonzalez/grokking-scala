import Element.elem
import org.scalatest.{Matchers, _}

class ElementFeatureSpec extends FeatureSpec with GivenWhenThen with Matchers  {

  feature("UniformElement basic behavior") {
    scenario("UniformElement is given a valid width") {
      Given("A UniformElement")
      When("given a valid width")
      val ele = elem('x', 2, 3)
      Then("the element should have that width")
      ele.width should be (2)
    }

    scenario("UniformElement is given a valid height") {
      Given("A UniformElement")
      When("given a valid width")
      val ele = elem('x', 2, 3)
      Then("the element should have that height")
      ele.height should be (3)
    }

    scenario("UniformElement is given an invalid width") {
      Given("A UniformElement")
      When("given an invalid width")
      Then("the element should throw an IllegalArgumentException")
      an [IllegalArgumentException] should be thrownBy {
        elem('x', -2, 3)
      }
    }
  }
}
