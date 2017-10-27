/*
  Adding auxiliary constructor for integer values
*/
class Rational(n: Int, d: Int) {
  require(d != 0)

  val numer: Int = n
  val denom: Int = d

  def this(n: Int) = this(n, 1)

  override def toString = if (denom != 1) s"$numer/$denom" else numer.toString

  def add(that: Rational): Rational = new Rational(numer * that.denom + that.numer * denom, denom * that.denom)

  def lessThan(that: Rational): Boolean = numer * that.denom < that.numer * denom

  def max(that: Rational): Rational = if (lessThan(that)) that else this
}

val three = new Rational(3)