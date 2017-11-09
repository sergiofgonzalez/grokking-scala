
abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length
}

class ArrayElement(
  val contents: Array[String]
) extends Element


/*
  Layout element consisting of a single line given by a string
*/
class LineElement(s: String) extends ArrayElement(Array(s)) {
  override def width = s.length
  override def height = 1
}


val elem = new LineElement("Hello to Jason Issacs!")
elem.width
elem.height
elem.contents