
import Element.elem // to avoid having to type Element.elem within Element class

abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length
  def above(that: Element): Element = elem(this.contents ++ that.contents)
  def beside(that: Element): Element =
    elem(
      for (
        (line1, line2) <- this.contents zip that.contents
      ) yield line1 + line2
    )
  override def toString = contents mkString "\n"
}

/*
  The class companion object which will hold the factory methods
*/
object Element {
  def elem(contents: Array[String]): Element = new ArrayElement(contents)
  def elem(ch: Char, widt: Int, height: Int): Element = new UniformElement(ch, widt, height)
  def elem(line: String): Element = new LineElement(line)

  private class ArrayElement(
    val contents: Array[String]
  ) extends Element
    

  private class UniformElement(
    ch: Char,
    override val width: Int,
    override val height: Int,
  ) extends Element {
    private val line = ch.toString * width
    def contents = Array.fill(height)(line)
  }

  private class LineElement(s: String) extends Element {
    val contents = Array(s)
    override def width = s.length
    override def height = 1
  }  
}

/*
  Testing
*/

val lineElem1 = elem("Hello to Jason Isaacs!")
val lineElem2 = elem("Hello to Idris Elba!")

val lineAbove = lineElem1 above lineElem2
println(lineAbove)

val lineBeside = lineElem1 beside lineElem2
println(lineBeside)

val uniformElem = elem('*', 7, 3)
println(uniformElem)

val arrayElem = elem(Array("hello", "to", "Jason", "Isaacs"))
println(arrayElem)