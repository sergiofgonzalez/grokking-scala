# 016 &mdash; Parametric Fields (snippet)
> introducing parametric fields: defining fields in the class definition

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

In the example, we illustrate the syntax to define parametric field definitions to avoid code smells such as:
```scala
abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length
}

class ArrayElement(conts: Array[String]) extends Element {
  val contents: Array[String] = conts // This is a code smell
}
```
in which we define a field `conts` that is an unnecessary redundancy.

In order to do that, use the syntax:
```scala
abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length
}

class ArrayElement(
  val contents: Array[String] // single parametric field
) extends Element
```

The parametric field can also be a `var` in which case the parameter is reassignable:
```scala
lass Person(var name: String)

val me = new Person("Sergio")
me.name
me.name = "Bizarro Sergio" // Ok: it's a var
```

You can also use modifiers such as `private` or `protected`:
```scala
class Person(private val name: String)

val me = new Person("Jason Isaacs")
me.name // Err: a private field cannot be accessed
```
 
And you can also override parametric fields as you would do with regular fields:
```scala
class Cat {
  val dangerous = false
}

class Tiger(
  override val dangerous: Boolean,
  private var age: Int
) extends Cat
```



## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load parametric-fields.scala
```