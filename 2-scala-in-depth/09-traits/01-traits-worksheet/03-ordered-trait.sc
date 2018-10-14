
class Rational(n: Int, d: Int) extends Ordered[Rational] {
  // precondition
  require(d != 0)

  // fields
  private val g = gcd(n.abs, d.abs)
  val numer: Int = n / g
  val denom: Int = d / g

  // auxiliary constructors
  def this(n: Int) = this(n, 1)

  // toString override
  override def toString: String = s"$numer/$denom"

  // Ordered Trait
  override def compare(that: Rational): Int = (this.numer * that.denom) - (that.numer * this.denom)

  // methods
  def +(other: Rational): Rational = new Rational(numer * other.denom + other.numer * denom, denom * other.denom)
  def +(other: Int): Rational = new Rational(numer + other * denom, denom)

  def -(other: Rational): Rational = new Rational(numer * other.denom - other.numer * denom, denom * other.denom)
  def -(other: Int): Rational = new Rational(numer - other * denom, denom)

  def *(other: Rational): Rational = new Rational(numer * other.numer, denom * other.denom)
  def *(other: Int): Rational = new Rational(numer * other, denom)

  def /(other: Rational): Rational = new Rational(numer * other.denom, denom * other.numer)
  def /(other: Int): Rational = new Rational(numer, denom * other)

  def max(other: Rational): Rational = if (this < other) other else this

  // private methods
  private def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else gcd(b, a % b)
  }
}

implicit def intToRational(x: Int): Rational = new Rational(x)


val half = new Rational(1, 2)
val third = new Rational(1, 3)

half < third
half > third


