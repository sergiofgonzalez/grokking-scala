# 007 &mdash; Adding Auxiliary Constructors (snippet)
> adding auxiliary constructors to a class

## Description
When you need additional constructors (other than the primary constructor) in a class, you have to use the following syntax:

```scala
class Rational(n: Int, d: Int) {
  require(d != 0)

  val numer: Int = n
  val denom: Int = d

  def this(n: Int) = this(n, 1) // auxiliary constructor
  
  ...
}
```

In Scala, every auxiliary constructor must invoke another constructor of the same class, and will have the form `this(...)`.

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load rational.scala
```