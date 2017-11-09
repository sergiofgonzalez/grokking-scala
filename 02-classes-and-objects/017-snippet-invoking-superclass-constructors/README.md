# 017 &mdash; Invoking Superclass Constructors (snippet)
> syntax for invoking the superclass constructor

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

In the example, we define a subtype of `ArrayElement` that represents an element consisting of a single line given by a String.
As the `ArrayElement` primary constructor requires an array of strings as an argument, the new subtype must be defined as:
```scala
class LineElement(s: String) extends ArrayElement(Array(s)) {
  override def width = s.length
  override def height = 1
}

val elem = new LineElement("Hello to Jason Issacs!")
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:

```
scala> :load invoking-superclass-constructors.scala
```