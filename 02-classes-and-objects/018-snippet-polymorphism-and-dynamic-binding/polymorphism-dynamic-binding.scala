
abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length

  def demo() = println("Element.demo invoked")
}

class ArrayElement(
  val contents: Array[String]
) extends Element {
  override def demo() = println("ArrayElement.demo invoked")
}

class LineElement(s: String) extends ArrayElement(Array(s)) {
  override def width = s.length
  override def height = 1

  override def demo() = println("LineElement.demo invoked")
}

/*
  UniformElement: given a width and height, fills the contents
  with a given character
*/
class UniformElement(
  ch: Char,
  override val width: Int,
  override val height: Int,
) extends Element {
  private val line = ch.toString * width
  def contents = Array.fill(height)(line)
}

val e1: Element = new ArrayElement(Array("hello", "world"))
val e2: Element = new LineElement("hello")
val e3: Element = new UniformElement('x', 2, 3)

e3.contents
println("==")

e1.demo() // ArrayElement.demo
e2.demo() // LineElement.demo
e3.demo() // UnformElement.demo does not exist, so the superclass impl is used: Element.demo
