/* curated inheritance hierarchy thus far... */
abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length

  def demo() = println("Element.demo()")
}

class ArrayElement(val contents: Array[String]) extends Element {
  override def demo() = println("ArrayElement.demo()")
}

class LineElement(s: String) extends ArrayElement(Array(s)) {
  override def width: Int = s.length
  override def height = 1

  override def demo() = println("LineElement.demo()")
}

class UniformElement(
                      ch: Char,
                      override val width: Int,
                      override val height: Int
                    ) extends Element {
  private val line = ch.toString * width
  def contents = Array.fill(height)(line)
}

def invokeDemo(e: Element) = e.demo()


invokeDemo(new ArrayElement(Array("something")))
invokeDemo(new LineElement("something"))
invokeDemo(new UniformElement(('s'), 5, 2))

