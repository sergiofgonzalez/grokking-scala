import org.scalatest.FunSuite


class MyFunSuite extends FunSuite {

  test("string length must be greater than 10") {
    val str = s"Hello to Jason Isaacs"
    assert(str.length > 10)
  }

  test("string length must be smaller than 20 (regular report)") {
    val str = s"Hello to Jason Isaacs"
    assert(str.length < 20)
  }

  // failed test using ScalaTest's diagrammed assertions
  // ** somehow is not working **
  import org.scalatest.Assertions._
  import org.scalatest.DiagrammedAssertions._ 
  test("List must contain 4 (with diagrammed assertions)") {
    assert(List(1, 2, 3).contains(4))
  }

  // Telling scalatest what was the expected result
  test("List was expected to be 4 elements long") {
    assertResult(4) {
      List(1, 2, 3).length
    }
  }

  // Checking that an exception is raised
  test("check an exception is raised") {
    assertThrows[ArithmeticException] {
      val x = 5 / 0
    }
  }

  // Another approach for checking for exceptions
  test("check exception result by catching it") {
    val caught = intercept[ArithmeticException] {
      5 / 0
    }
    assert(caught.getMessage == "/ by zero")
  }


}
