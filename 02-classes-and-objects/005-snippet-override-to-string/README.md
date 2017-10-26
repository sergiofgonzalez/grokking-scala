# 005 &mdash; Overriding Class toString (snippet)
> overriding `toString` in a class definition

## Description
Illustrates how to override `toString` in a class definition.

The syntax is simple: you just have to qualify the method with the `override` keyword to indicate that the default implementation should be overridden by the added method.

```scala
class Rational(n: Int, d: Int) {
  override def toString = s"$n/$d"
}

val oneHalf = new Rational(1, 2) // -> oneHalf: Rational = 1/2
```

The Scala compiler will place any code you include in the class body into the primary constructor:
```scala
class Rational(n: Int, d: Int) {
  println(s"Rational created: $n/$d")
}
val oneHalf = new Rational(1, 2)  // -> prints: Rational created: 1/2
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load overriding-to-string.scala
```