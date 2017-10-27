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

  def add(that: Rational): Rational = new Rational(numer * that.denom + that.numer * denom, denom * that.denom)

  def lessThan(that: Rational): Boolean = numer * that.denom < that.numer * denom

  def max(that: Rational): Rational = if (lessThan(that)) that else this

  private def greatestCommonDivisor(a: Int, b: Int): Int = if (b == 0) a else greatestCommonDivisor(b, a % b)
}

val fourSixths = new Rational(4, 6)
fourSixths.numer
fourSixths.denom
fourSixths.g  // Err: private field
fourSixths.greatestCommonDivisor(6, 4) // Err: private function
