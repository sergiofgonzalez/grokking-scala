# 002 &mdash; The Concept of Currying (snippet)
> introducing the concept of currying: functions that are applied multiple argument lists

## Description
*Currying* is a functional programming technique needed for understanding how to create control abstractions in Scala.

A *curried function* is applied multiple argument lists, instead of just one:
```scala
def curriedSum(x: Int)(y: Int) = x + y

curriedSum(2)(3) // <- 5
```

What's happening under the hood when using `curriedSum` is that there are two invocations back-to-back &mdash;the first one `curriedSum(2)` returns a function value which is used with the second argument `3`.

This can be understood more explicitly when writing:
```scala
def first(x: Int) = (y: Int) => x + y // returns a function that takes an Int and sums it to the parameter passed to it
val second = first(2) // apply the result of prev function to `2` and store the result
second(3) // apply the result of the prev func to 3
```

There is also a way to get an actual reference to the function value returned by a curried function when applied to the first param using the placeholder syntax:
```scala
def curriedSum(x: Int)(y: Int) = x + y
val twoPlus = curriedSum(2)_
twoPlus(3)
```


## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load currying.scala
```