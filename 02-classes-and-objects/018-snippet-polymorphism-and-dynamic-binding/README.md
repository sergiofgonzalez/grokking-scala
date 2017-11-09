# 018 &mdash; Polymorphism and Dynamic Binding (snippet)
> polymorphism and dynamic binding

## Description
As a running example, we intend to build a library for building and rendering 2D layout elements, in which each element represent a rectangle filled with text.

The library will provide factory methods:
+ `elem` &mdash; constructs a new element withe the given arguments
+ `above` &mdash; places the given first argument on top of the second argument
+ `beside` &mdash; places the given first argument on the left of the second argument

As an example, the library must be able to support:
```scala
val col1 = elem("hello") above elem("###")
val col2 = elem("***") above elem("world")
col1 beside col2
```

and rendering the previous construct would produce:
```
hello ***
### world
```

The elements will be modeled with a type named `Element`.

In the example, the following subtypes and methods within the subtypes are defined:
```scala
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

class UniformElement(
  ch: Char,
  override val width: Int,
  override val height: Int,
) extends Element {
  private val line = ch.toString * width
  def contents = Array.fill(height)(line)
}

```

Note that, `LineElement` inherits from `ArrayElement` which inherits from `Element`, while `UniformElement` inherits directly from `Element`.

We define a *test method* demo which is overridden by `LineElement`, `ArrayElement` but not from `UniformElement`.

Thus when doing:
```scala
val e1: Element = new ArrayElement(Array("hello", "world"))
val e2: Element = new LineElement("hello")
val e3: Element = new UniformElement('x', 2, 3)

e1.demo() // ArrayElement.demo
e2.demo() // LineElement.demo
e3.demo() // UnformElement.demo does not exist, so the superclass impl is used: Element.demo
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:

```
scala> :load polymorphism-dynamic-binding.scala
```