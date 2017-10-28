# 011 &mdash; Hello, Implicit Conversions (snippet)
> defining a method to perform the implicit conversion from `Int` to `Rational`

## Description
We've implemented a class that can compute things like:
```scala
val r = new Rational(2, 3)
r * 2
```

but cannot handle `2 * r` because there is no conversion available from `Int` to `Rational`. To enable such conversion to you have to define the following function:
```scala
implicit def intToRational(i: Int) = new Rational(i)
```

The function defines a conversion from `Int` to `Rational` and the `implicit` keyword tells the compiler to use it when such a conversion is required.

**Note**
For an implicit conversion to work, it needs to be in scope and therefore, cannot be placed inside the `Rational` class.


```scala
class Rational(n: Int, d: Int) {
  require(d != 0)
  ...
  
  def +(that: Rational): Rational = new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
  def +(i: Int): Rational = new Rational(numer + i * denom, denom)

  def -(that: Rational): Rational = new Rational(numer * that.denom - that.numer * denom, denom * that.denom)
  def -(i: Int): Rational = this - new Rational(i)
  ...
}
```

Note that we use two different techniques in the implementation of the overloaded method &mdash; in the second one, we reuse the pre-existing method, while in the first one we implement the method from scratch.

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load rational.scala
```