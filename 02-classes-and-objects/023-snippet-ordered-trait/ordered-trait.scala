class Rational(n: Int, d: Int) extends Ordered[Rational] {
  require(d != 0)

  private val g = greatestCommonDivisor(n.abs, d.abs)
  val numer: Int = n / g
  val denom: Int = d / g

  def this(n: Int) = this(n, 1)

  override def toString = if (denom != 1) s"$numer/$denom" else numer.toString

  /*
    Implementing the Ordered trait method
  */
  def compare(that: Rational) = (this.numer * that.denom) - (that.numer * this.denom)

  def +(that: Rational): Rational = new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
  def +(i: Int): Rational = new Rational(numer + i * denom, denom)

  def -(that: Rational): Rational = new Rational(numer * that.denom - that.numer * denom, denom * that.denom)
  def -(i: Int): Rational = this - new Rational(i)

  def *(that: Rational): Rational = new Rational(numer * that.numer, denom * that.denom)
  def *(i: Int): Rational = this * new Rational(i)

  def /(that: Rational) : Rational = new Rational(numer * that.denom, denom * that.numer)
  def /(i: Int): Rational = this / new Rational(i)

  def max(that: Rational): Rational = if (<(that)) that else this
  def max(i: Int): Rational = this max new Rational(i)

  private def greatestCommonDivisor(a: Int, b: Int): Int = if (b == 0) a else greatestCommonDivisor(b, a % b)
}

val oneHalf = new Rational(1, 2)
val oneThird = new Rational(1, 3)
val twoFourths = new Rational(2, 4)

oneHalf < oneThird
oneHalf <= oneThird
oneHalf > oneThird
oneHalf >= oneThird
println("==\n")

oneHalf < twoFourths
oneHalf <= twoFourths
oneHalf > twoFourths
oneHalf >= twoFourths
println("==\n")