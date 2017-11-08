# 014 &mdash; Extending classes (snippet)
> extending classes

## Description
As a running example, we intend to build a library for building and rendering 2D layout elements, in which each element represent a rectangle filled with text.

The library will provide factory methods:
+ `elem` &mdash; constructs a new element withe the given arguments
+ `above` &mdash; places the given first argument on top of the second argument
+ `beside` &mdash; places the given first argument on the left of the second argument

As as example, the library must be able to support:
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

In the example, we create a subclass that extends `Element` and implements the abstract `contents` method:
```scala
abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length
}

class ArrayElement(conts: Array[String]) extends Element {
  def contents: Array[String] = conts
}
```

By subclassing `Element` we get the two following consequences:
+ `ArrayElement` inherits all non-private members from `Element`, except for public methods defined on the superclass that are also defined in the subclass (in this case, the member of the subclass is said to *override* the superclass method).
+ `ArrayElement` becomes a subtype of `Element`

**Note**
When you define a class in Scala that does not extend from any particular class, it is assumed that it extends from `scala.AnyRef`. *AnyRef* plays the same role as the *Object* class in Java.

Once we have implemented the `Element` abstract class, we can instantiate objects:
```scala
val ae = new ArrayElement(Array("hello", "to", "Jason Isaacs"))
ae.width  // <- 5 number of chars of the first element of the array
ae.height // <- 3 number of elements in the array
ae.contents
```

And as in Java, when implementing a superclass, you can code to the superclass:
```scala
val element: Element = new ArrayElement(Array("hello", "to", "Riz Ahmed"))
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load extending-classes.scala
```