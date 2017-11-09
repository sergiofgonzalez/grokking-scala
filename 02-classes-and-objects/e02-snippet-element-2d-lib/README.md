# e02 &mdash; Element 2D lib
> the first implementation of the Element library

## Description
The first implementation for a library for building and rendering 2D layout elements, in which each element represent a rectangle filled with text.

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

I
## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:

```
scala> :paste element.scala
```

Note that when using `:paste` you might need to use `:reset` when executing multiple times the same piece of code.