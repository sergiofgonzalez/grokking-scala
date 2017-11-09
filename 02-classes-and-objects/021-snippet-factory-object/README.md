# 021 &mdash; Factory Objects (snippet)
> defining factory methods in a companion object to centralize object instantiation

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

In the example, although the class hierarchy is complete and could be used *as-is*, we hide the complete class hierarchy behind a *Factory object*. This will allow the clients to use the *Factory method* instead of `new`, so that method creation is centralized and the object details can be hidden.

In Scala, *Factory Methods* are usually defined as companion objects for the class they instantiate &mdash; `Element` in our case. By doing so, we can hide the implementation classes fromt he client.

**Note: A reminder about Companion Objects**
When a singleton `object` shares the same name with a class, it is called that *class's companion object*. In that case, both the class and the companion object must be defined in the same source code file. The class is also called the *companion class* of the singleton object.

Scala provides several possibilities to hide the implementation of `LineElement`, `ArrayElement` and `UniformElement`:
+ You can declare the classes as `private` so that the client code will not be able to instantiate or reference them.
+ You can place them inside the `Element` singleton object and declare them `private` there. The classes will still be accessible to the factory methods defined there, but will be hidden elsewhere (preferred).

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:

```
scala> :paste element.scala
```