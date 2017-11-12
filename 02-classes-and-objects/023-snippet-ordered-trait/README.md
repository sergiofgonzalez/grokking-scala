# 023 &mdash; The Ordered Trait (snippet)
> Scala's `Ordered[C]` trait to solve object comparison in a standard way

## Description
The problem of comparing objects by their contents, to be able to use not only `==`,`<=`, `>`, etc. is a common problem, and Scala provides a trait to let you address it.

The trait `Ordered` defines `<`, `>`, `<=` and `>=` in terms of a `compare` method. The method requires a *type parameter* to indicate which are the type of the objects that will be compared and the method should:
+ return 0 if the objects are the same
+ negative integer if the receiver is less than the argument
+ positive integer if the receiver is greater than the argument

```scala
class Rational(n: Int, d: Int) extends Ordered[Rational] {
  def compare(that: Rational) = (this.numer * that.denom) - (that.numer * this.denom)
  /* ...other methods... */
}
```

By implementing the `compare` method, your class will feature all the comparison operators automatically, without having to define them yourself.
```scala
val oneHalf = new Rational(1, 2)
val oneThird = new Rational(1, 3)
val twoFourths = new Rational(2, 4)

oneHalf < oneThird      // <- false
oneHalf <= oneThird     // <- false
oneHalf > oneThird      // <- true
oneHalf >= oneThird     // <- true


oneHalf < twoFourths    // <- false
oneHalf <= twoFourths   // <- true
oneHalf > twoFourths    // <- false
oneHalf >= twoFourths   // <- true
```

That happens because trait is actually defined as:
```scala
trait Ordered[T] {
  def compare(that: T): Int

  def <(that: T): Boolean = (this compare that) < 0
  def >(that: T): Boolean = (this compare that) > 0
  def <=(that: T): Boolean = (this compare that) <= 0
  def >=(that: T): Boolean = (this compare that) >= 0  
}
```


## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:

```
scala> :load hello-traits.scala
```