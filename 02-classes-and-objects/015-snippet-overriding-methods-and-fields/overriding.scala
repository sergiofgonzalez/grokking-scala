
abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length
}

class ArrayElement(conts: Array[String]) extends Element {
  val contents: Array[String] = conts
}

/*
  Overriding a method with a field has no consequences
*/
val element: Element = new ArrayElement(Array("hello", "to", "Jason Isaacs"))
element.width
element.height
element.contents
element.contents(2) = "Idris Elba"
element.contents
println("==")

/*
  Defining a method with the same name as one in the superclass
  is said to override the superclass method.

  In the example, we change the width definition to return
  the max length amongst all of the array elements 
*/
class ArrayElement(conts: Array[String]) extends Element {
  val contents: Array[String] = conts
  override def width: Int = {
    var maxLen = 0
    for (elem <- conts)
      maxLen = Math.max(elem.length, maxLen)
    maxLen
  }
}

val element: Element = new ArrayElement(Array("hello", "to", "Jason Isaacs"))
element.width
println("==")
