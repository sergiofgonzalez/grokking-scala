/*
  Adding private fields and methods to normalize a Rational number
*/
class Rational(n: Int, d: Int) {
  require(d != 0)

  private val g = greatestCommonDivisor(n.abs, d.abs)
  val numer: Int = n / g
  val denom: Int = d / g

  def this(n: Int) = this(n, 1)

  override def toString = if (denom != 1) s"$numer/$denom" else numer.toString

  def +(that: Rational): Rational = new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
  def *(that: Rational): Rational = new Rational(numer * that.numer, denom * that.denom)
  def <(that: Rational): Boolean = numer * that.denom < that.numer * denom
  def >(that: Rational): Boolean = numer * that.denom > that.numer * denom

  def max(that: Rational): Rational = if (<(that)) that else this

  private def greatestCommonDivisor(a: Int, b: Int): Int = if (b == 0) a else greatestCommonDivisor(b, a % b)
}

val oneHalf = new Rational(1, 2)
val twoThirds = new Rational(2, 3)


oneHalf < twoThirds
oneHalf > twoThirds
twoThirds < oneHalf
twoThirds > oneHalf
println("==========")

oneHalf + twoThirds
oneHalf * twoThirds
println("==========")

oneHalf + oneHalf * twoThirds
oneHalf + (oneHalf * twoThirds)
