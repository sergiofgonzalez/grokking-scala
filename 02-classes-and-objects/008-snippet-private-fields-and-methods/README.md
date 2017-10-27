# 008 &mdash; Hello, Private Fields and Methods (snippet)
> adding private fields and methods to a class

## Description
You can add to a class private fields and methods using the `private` keyword:

```scala
class Rational(n: Int, d: Int) {
  require(d != 0)

  private val g = greatestCommonDivisor(n.abs, d.abs)
  val numer: Int = n / g
  val denom: Int = d / g

  ...
  private def greatestCommonDivisor(a: Int, b: Int): Int = if (b == 0) a else greatestCommonDivisor(b, a % b)
}
```

It must be noted that:
+ as expected, the field `g` and the function `greatestCommonDivisor` won't be available from outside the class
+ the Scala compiler, will place the code for the initialization of `g`, `numer` and `denom` into the primary constructor in the order in which they appear in the class definition. Therefore, `g` will be calculated before `numer` and `denom` are defined and therefore the class fields will be correctly initialized.


## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load rational.scala
```