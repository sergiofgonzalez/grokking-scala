
abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length
}

class ArrayElement(
  val contents: Array[String]
) extends Element

class UniformElement(
  ch: Char,
  override val width: Int,
  override val height: Int,
) extends Element {
  private val line = ch.toString * width
  def contents = Array.fill(height)(line)
}

/* Reimplementing LineElement with composition */
class LineElement(s: String) extends Element {
  val contents = Array(s)
  override def width = s.length
  override def height = 1
}

val e = new LineElement("Hello to Jason Isaacs!")
e.width
e.height
e.contents
