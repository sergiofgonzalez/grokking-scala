# 020 &mdash; Composition and Inheritance (snippet)
> favoring composition over inheritance

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

In the example, we illustrate how to implement `LineElement` with composition instead of inheritance. Even if the *is a* relationship holds true for a certain class in a class hierarchy, you should also think about how the client code would be using the classes. 
In our previous example, even if `LineElement` *is an* `ArrayElement`, the consumers of `LineElement` would generally be more comfortable using a `LineElement` that inherits directly from `Element`. Then we can reimplement `LineElement` with composition:

By doing so, `LineElement` will no longer be tightly coupled with `ArrayElement` while supporting the same capabilities.

```scala
class LineElement(s: String) extends Element {
  val contents = Array(s)
  override def width = s.length
  override def height = 1
}
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:

```
scala> :load polymorphism-dynamic-binding.scala
```