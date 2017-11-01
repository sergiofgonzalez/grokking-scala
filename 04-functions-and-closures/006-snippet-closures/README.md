# 006 &mdash; Closures (snippet)
> closures in Scala

## Description
A closure is a function value created from a function literal that references variables outside of its own scope:

```scala
val addMore = (x: Int) => x + y  // y must be defined elsewhere
```

By contrast, a function that only references no *free* variables is called a *closed term*
```scala
val increase = (x: Int) => x + 1 // this is a close term
```

Scala captures the variable in the closure, not the value to which it refers:
```scala
var y = 1
val addMore = (x: Int) => x + y  // y must be defined elsewhere
addMore(10) // <- 11

y = 2
addMore(10) // <- 12
```

Also, if the closure changes the value of the free the change will be visible:
```scala
var more = 1
val incMore = (x: Int) => more += x
incMore(3)
more // <- 4
```

This can be used to *reduce* the elements of a List:
```scala
val numbers = List(-11, -10, -5, 0, 5, 10)
var sum = 0
numbers.foreach( sum += _ )
sum // <- -11
```

Note that the closures are bound to the free variables it uses at the time of closure creation. This is illustrated in the following example, creates a more recognizable closure definition
```scala
def makeIncreaser(more: Int) = (x: Int) => x + more
val inc1 = makeIncreaser(1)
inc1(5) // <- 6

val inc5 = makeIncreaser(5)
inc5(5) // <- 10
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load partially-applied-functions.scala
```