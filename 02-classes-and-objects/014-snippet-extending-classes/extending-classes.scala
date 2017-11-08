
abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length
}

class ArrayElement(conts: Array[String]) extends Element {
  def contents: Array[String] = conts
}

/*
  Now that we have a concrete class, we can finally instantiate ArrayElements
*/
val ae = new ArrayElement(Array("hello", "to", "Jason Isaacs"))
ae.width  // number of chars of the first element of the array
ae.height
ae.contents
ae.contents(2) = "Idris Elba"
ae.contents
println("==")

/*
  As in Java, creating a subtype means that you can code to the superclass
*/
val element: Element = new ArrayElement(Array("hello", "to", "Riz Ahmed"))
element.width
element.height
element.contents
println("==")