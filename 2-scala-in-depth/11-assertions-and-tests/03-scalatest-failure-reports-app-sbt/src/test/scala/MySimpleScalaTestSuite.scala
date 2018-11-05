import org.scalatest.FunSuite

class MySimpleScalaTestSuite extends FunSuite {

  test("width must be 3") {
    val width = 2
    assert(width == 3)
  }

  test("expected width must be 3") {
    assertResult(3) {
      val width = 2
      width
    }
  }

  test("list must contain 4") {
    assert(List(1, 2, 3).contains(4))
  }

  test("should throw an exception") {
    assertThrows[IllegalArgumentException] {
      throw new IllegalArgumentException("fabricated exception")
    }
  }


  test("should get fabricated exception message") {
    val caught = intercept[IllegalArgumentException] {
      throw new IllegalArgumentException("fabricated exception")
    }

    assert(caught.getMessage == "fabricated exception")
  }
}
