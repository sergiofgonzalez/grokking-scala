
import Element.elem // to avoid having to type Element.elem within Element class

abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length
  def above(that: Element): Element = {
    val this1 = this widen that.width
    val that1 = that widen this.width
    elem(this1.contents ++ that1.contents)
  }
  def beside(that: Element): Element = {
    val this1 = this heighten that.height
    val that1 = that heighten this.height
    elem(
      for (
        (line1, line2) <- this1.contents zip that1.contents
      ) yield line1 + line2
    )
  }

  def widen(w: Int): Element =
    if (w <= width) 
      this
    else {
      val left = elem(' ', (w - width) / 2, height)
      val right = elem(' ', w - width - left.width, height)
      left beside this beside right
    }

  def heighten(h: Int): Element =
    if (h <= height)
      this
    else {
      val top = elem(' ', width, (h - height) / 2)
      val bot = elem(' ', width, h - height - top.height)
      top above this above bot
    }

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


/*
  An application using the lib
*/
import Element.elem

object Spiral {
  val space = elem(" ")
  val corner = elem("+")

  def spiral(nEdges: Int, direction: Int): Element = {
    if (nEdges == 1)
      elem("+")
    else {
      val sp = spiral(nEdges - 1, (direction + 3) % 4)
      def verticalBar = elem('|', 1, sp.height)
      def horizontalBar = elem('-', sp.width, 1)

      if (direction == 0)
        (corner beside horizontalBar) above (sp beside space)
      else if (direction == 1)
        (sp above space) beside (corner above verticalBar)
      else if (direction == 2)
        (space beside sp) above (horizontalBar beside corner)
      else
        (verticalBar above corner) beside (space above sp)
    }
  }
}

import Spiral.spiral

spiral(11, 0)
