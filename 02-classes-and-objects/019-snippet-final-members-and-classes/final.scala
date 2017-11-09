
abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length

  def demo() = println("Element.demo invoked")
}

class ArrayElement(
  val contents: Array[String]
) extends Element {
  final override def demo() = println("ArrayElement.demo invoked")
}

class LineElement(s: String) extends ArrayElement(Array(s)) {
  override def width = s.length
  override def height = 1

  override def demo() = println("LineElement.demo invoked") // Err: cannot override final method from superclass
}

println("==")


final class ArrayElement(
  val contents: Array[String]
) extends Element

class LineElement(s: String) extends ArrayElement(Array(s)) { // Err: illegal inheritance
  override def width = s.length
  override def height = 1
}