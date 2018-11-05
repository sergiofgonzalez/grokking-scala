import org.scalatest._
import org.scalatest.Matchers

import Element.elem

class TvSetSpec extends FeatureSpec with GivenWhenThen with Matchers  {
  feature("TV power button") {
    scenario("User presses power button when TV is off") {
      Given("a TV set that is switched off")
      When("the power button is pressed")
      Then("the TV should switch on")
      pending
    }
  }
}
