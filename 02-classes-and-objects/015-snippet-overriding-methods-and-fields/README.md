# 015 &mdash; Overriding Methods and Fields (snippet)
> overriding methods with fields and methods

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

In the example, we demonstrate the techniques for overriding methods and fields in Scala.

For example, in Scala, fields and methods are treated uniformly, so a field can override a parameterless method, and the compiler will not complain:
```scala
abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length
}

class ArrayElement(conts: Array[String]) extends Element {
  val contents: Array[String] = conts // overriding a method with a field
}
```

For the same reason (fields and methods sharing the same *namespace*), Scala does not allow a field and a method to have the same name.

In particular, Scala features only two namespaces:
+ values (fields, methods, packages and singleton objects)
+ types (class and trait names)

The Scala compiler will not allow classes within each namespace.

Overriding a method that exists in the superclass means defining a method with the same name in the subclass, using the `override` keyword to indicate that your intentions:
```scala
class ArrayElement(conts: Array[String]) extends Element {
  val contents: Array[String] = conts
  override def width: Int = { /* new implementation */ }
}
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load overriding.scala
```