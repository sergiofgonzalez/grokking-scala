import org.scalatest.FunSuite

class MyFunSuite extends FunSuite {

  test("string length must be greater than 10") {
    val str = s"Hello to Jason Isaacs"
    assert(str.length > 10)
  }

  test("string length must be smaller than 20") {
    val str = s"Hello to Jason Isaacs"
    assert(str.length < 20)
  }
}
