# 010 &mdash; Hello, Method Overloading (snippet)
> using method overloading

## Description
Scala's process to define and resolve method overloading is very similar to Java's. In the following example, we use method overloading to add the capability of using `Int` types in our rational type:

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