# 006 &mdash; Hello, Scala extensibility: Implementing the break keyword (snippet)
> exploring Scala extensibility capabilities: implementing the `break` keyword

## Description
Scala doesn't have a `break` keyword, but you can create a `breakable` function that accepts a function as a parameter and in which the *break* could be used with the expected results.

In order to do that you will need:
+ `break` &mdash; a variable that will hold the action of throwing an *Exception*
+ `breakable` &mdash; a function that accepts a `() => Unit` function as a parameter and in which `break` can be called. The implementation of the function includes Scala's exception hadling to simulate the `break` functionality.
+ A function that will be passed to `breakable` that uses `break` inside it.

With all the pieces in place you'll be able to do:

```scala
breakable {
  val environmentVar = "SCALA_HOME"
  val env = System.getenv(environmentVar)
  if (env == null) {
    println(s"$environmentVar not found: exiting")
    break
  }
  println(s"$environmentVar was found")
}
```


## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load break-implementation.scala
```