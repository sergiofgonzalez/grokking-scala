# 001 &mdash; Abstract Classes (snippet)
> the syntax for defining abstract classes in Scala

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

In the example, we perform our first task, which is the definition of the `Element` type as an abstract class.

```scala
abstract class Element {
  def contents: Array[String]
}
```

Note that `contents` is defined as a method of the `Element` class, with no implementation. Note also that the method does not need any abstract modifier.

## Running the Snippet
The snippet is intended as documentation and cannot be run.