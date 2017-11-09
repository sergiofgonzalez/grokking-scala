# 019 &mdash; Final Members and Classes (snippet)
> preventing overriding methods and subclassing with `final` modifier

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

In the example, it is illustrated how to ensure that a member cannot be overridden by subclasses using the *final modifier* on a member.

```scala
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
```

You can also prevent an entire class to be subclassed using the `final` modifier:
```scala
final class ArrayElement(
  val contents: Array[String]
) extends Element

class LineElement(s: String) extends ArrayElement(Array(s)) { // Err: illegal inheritance
  override def width = s.length
  override def height = 1
}
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:

```
scala> :load final.scala
```