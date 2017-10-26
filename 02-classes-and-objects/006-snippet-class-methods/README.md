# 006 &mdash; Adding methods (snippet)
> adding methods to a class definition in Scala

## Description
Illustrates how to create a simple class `Rational` that models the mathematical concept of a rational number and supports addition.

The class features:
+ a primary constructor &mdash; will be synthesized by Scala using the *class parameters*
+ a precondition &mdash; the denominator cannot be zero
+ a useful `toString` &mdash; the default `toString` must be overridden with a more useful string that prints out the numerator and denominator
+ a couple of methods that expose the values for the numerator and denominator
+ an `add` method that computes the result of adding two `Rational` objects

```scala
class Rational(n: Int, d: Int) {
  require(d != 0)
  override def toString = s"$n/$d"

  val numer: Int = n
  val denom: Int = d

  def add(that: Rational): Rational = new Rational(n * that.denom + that.numer * d, d * that.denom)
}
```

Note that a couple of fields named `numer` and `denom` are added to the class so that we can access the state of a given `Rational` object &mdash; those are *public fields*.

Note also that for the *own class* we can use the *class parameters* `n` and `d`, although it is recommended to use the class fields once defined. Thus, this is a better implementation:

```scala
class Rational(n: Int, d: Int) {
  require(d != 0)

  val numer: Int = n
  val denom: Int = d

  override def toString = s"$numer/$denom"

  def add(that: Rational): Rational = new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
}
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load rational.scala
```