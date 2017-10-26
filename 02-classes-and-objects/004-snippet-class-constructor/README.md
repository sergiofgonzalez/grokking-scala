# 004 &mdash; Hello, Class Constructor (snippet)
> peculiarities about class constructors in Scala

## Description
Illustrates class constructor peculiarities in Scala.
For example, if a class does not have a body you don't need to specify curly braces. 
Also, you can include the constructor parameters in the class definition (in what Scala calls *class parameters*) and Scala will synthesize a primary constructor that takes the parameters you specify:

```scala
class Rational(n: Int, d: Int)  

val oneHalf = new Rational(1, 2)
```

The Scala compiler will place any code you include in the class body into the primary constructor:
```scala
class Rational(n: Int, d: Int) {
  println(s"Rational created: $n/$d")
}
val oneHalf = new Rational(1, 2)  // -> prints: Rational created: 1/2
```

This can be used to include *preconditions* for inmmutable objects:
```scala
class Rational(n: Int, d: Int) {
  require(d != 0)
} 
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load hello-class-constructor.scala
```