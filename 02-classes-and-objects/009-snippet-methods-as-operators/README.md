# 009 &mdash; Methods as operators in a class (snippet) (snippet)
> renaming methods as operators

## Description
As `+` and `*` are valid method names in Scala, you can give more meaningful names to methods that perform mathematical operations. Also, you can take advantage of the Scala syntax that allows any method to use the *operator notation*, so that you don't have to use the *dot* when invoking the method, so that instead of `x.+(y)` can be effectively written as `x + y`.

```scala
class Rational(n: Int, d: Int) {
  require(d != 0)
  ...
  
  def +(that: Rational): Rational = new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
  def *(that: Rational): Rational = new Rational(numer * that.numer, denom * that.denom)
  def <(that: Rational): Boolean = numer * that.denom < that.numer * denom
  def >(that: Rational): Boolean = numer * that.denom > that.numer * denom

  def max(that: Rational): Rational = if (<(that)) that else this
}
```


## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load rational.scala
```