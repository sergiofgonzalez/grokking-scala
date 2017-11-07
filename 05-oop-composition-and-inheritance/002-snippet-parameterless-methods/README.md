# 002 &mdash; Parameterless Methods (snippet)
> 

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

In the example, we define two parameterless methods `width` and `height`:
+ `height` &mdash; returns the number of lines of the element
+ `width` &mdash; returns the length of the first line of the element, or zero if there are no lines in the element

```scala
abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length
}
```

Note that both methods are defined without parameters, not even with empty parentheses as in `height()`.

By convention, parameterless methods should be used whenever the method does not accept any parameters and the method accesses mutable state only by reading fields of the containing object (as it's the case here).

This is consistent with the *uniform access principle* that states that the client code should not be affected by a decision to implement an attribute as a field or a method.
For instance, we could've done:
```scala
abstract class Element {
  def contents: Array[String]
  val height = contents.length
  val width = if (height == 0) 0 else contents(0).length
}
```
and nothing would've changed. Note also that the client code should not be rewritten if we decide to switch from field access to method access.

Also by convention, you should use parentheses when the method performs some kind of side-effects (e.g. `println()`).

In summary:
+ Use parameterless methods for methods that take no parameters and have no side effects.
+ Use *empty-paren* methods for methods that take no parameters and have side effects.

## Running the Snippet
The snippet is intended as documentation and cannot be run.