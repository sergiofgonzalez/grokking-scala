/*
  Rational class: models a Rational number with a numerator and denominator
*/
class Rational(n: Int, d: Int) {
  require(d != 0)
  override def toString = s"$n/$d"

  val numer: Int = n
  val denom: Int = d

  def add(that: Rational): Rational = new Rational(n * that.denom + that.numer * d, d * that.denom)
}

val oneHalf = new Rational(1, 2)
val twoThirds = new Rational(2, 3)
val sum = oneHalf add twoThirds

sum.numer   // -> now we can introspect on the object
sum.denom

sum.numer = 8 // Still immutable -> Error: reassignment to val

/*
  Better implementation:
     using class fields rather than class parameters
*/
class Rational(n: Int, d: Int) {
  require(d != 0)

  val numer: Int = n
  val denom: Int = d

  override def toString = s"$numer/$denom"

  def add(that: Rational): Rational = new Rational(numer * that.denom + that.numer * denom, denom * that.denom)

  def lessThan(that: Rational): Boolean = numer * that.denom < that.numer * denom

  def max(that: Rational): Rational = if (lessThan(that)) that else this
}

println("=====================")
val oneHalf = new Rational(1, 2)
val twoThirds = new Rational(2, 3)
val sum = oneHalf add twoThirds

sum.numer   
sum.denom

sum.numer = 8 

println("=====================")
val fourFifths = new Rational(4, 5)
fourFifths lessThan twoThirds

fourFifths max twoThirds
twoThirds max fourFifths