/* curated inheritance hierarchy thus far... */
abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length

  def above(that: Element): Element = new ArrayElement(this.contents ++ that.contents)

  /* imperative impl: v1
  def beside(that: Element): Element = {
    val contents = new Array[String](this.contents.length)
    for (i <- 0 until this.contents.length)
      contents(i) = this.contents(i) + that.contents(i)
    new ArrayElement(contents)
  }
  */

  /* functional impl: v2 */
  def beside(that: Element): Element = {
    new ArrayElement(
      for ((line1, line2) <- this.contents zip that.contents)
        yield line1 + line2
    )
  }

  override def toString: String = contents mkString "\n"
}

class ArrayElement(val contents: Array[String]) extends Element

class LineElement(s: String) extends ArrayElement(Array(s)) {
  override def width: Int = s.length
  override def height = 1
}

class UniformElement(
                      ch: Char,
                      override val width: Int,
                      override val height: Int
                    ) extends Element {
  private val line = ch.toString * width
  def contents = Array.fill(height)(line)
}

/* Now we have a way to see the representation of Element's as Strings */
println((new ArrayElement(Array("singleElement"))).toString)
println((new ArrayElement(Array("elem1", "elem2"))).toString)
println((new ArrayElement(Array("elem1", "elem2", "elem3"))).toString)

println((new LineElement("hello")).toString)

println((new UniformElement('*', 10, 2)))


/* Now we test the methods */
println(new LineElement("hello") above new LineElement("world"))
println(new LineElement("hello ") beside new LineElement("to ") beside new LineElement("Jason Isaacs"))


val line = new UniformElement('*', "hello to Jason Isaacs".length, 1)
val greeting = new LineElement("hello ") beside new LineElement("to ") beside new LineElement("Jason Isaacs")

println(line above greeting)
