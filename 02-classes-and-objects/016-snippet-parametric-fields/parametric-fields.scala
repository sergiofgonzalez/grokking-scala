
abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length
}

class ArrayElement(conts: Array[String]) extends Element {
  val contents: Array[String] = conts // This is a code smell
}

class ArrayElement(
  val contents: Array[String]
) extends Element

val element: Element = new ArrayElement(Array("hello", "to", "Jason Isaacs"))
element.width
element.height
element.contents
println("==")

/*
  Parametric fields can be var
*/

class Person(val name: String)

val me = new Person("Sergio")
me.name
me.name = "some other" // Err: reassignment to val
println("==")

class Person(var name: String)

val me = new Person("Sergio")
me.name
me.name = "Bizarro Sergio" // Ok: it's a var
println("==")

/*
  Including modifiers such as private
*/
class Person(private val name: String)

val me = new Person("Jason Isaacs")
me.name // Err: a private field cannot be accessed
println("==")

/*
  Overriding with parametric fields
*/
class Cat {
  val dangerous = false
}

class Tiger(
  override val dangerous: Boolean,
  private var age: Int
) extends Cat