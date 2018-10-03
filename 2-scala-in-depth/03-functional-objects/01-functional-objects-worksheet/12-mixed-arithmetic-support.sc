
class Rational(n: Int, d: Int) {
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

  // methods
  def +(other: Rational): Rational = new Rational(numer * other.denom + other.numer * denom, denom * other.denom)
  def +(other: Int): Rational = new Rational(numer + other * denom, denom)

  def -(other: Rational): Rational = new Rational(numer * other.denom - other.numer * denom, denom * other.denom)
  def -(other: Int): Rational = new Rational(numer - other * denom, denom)

  def *(other: Rational): Rational = new Rational(numer * other.numer, denom * other.denom)
  def *(other: Int): Rational = new Rational(numer * other, denom)

  def /(other: Rational): Rational = new Rational(numer * other.denom, denom * other.numer)
  def /(other: Int): Rational = new Rational(numer, denom * other)


  def <(other: Rational): Boolean = this.numer * other.denom < other.numer * this.denom
  def max(other: Rational): Rational = if (this < other) other else this

  // private methods
  private def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else gcd(b, a % b)
  }
}


val oneHalf = new Rational(1, 2)
val twoThirds = new Rational(2, 3)

// testing new methods

// subtraction
twoThirds - oneHalf

// division
oneHalf / twoThirds

// mixed arithmetic
oneHalf + 1
twoThirds - 2
oneHalf * 3
twoThirds / 4

// Note that this does not work
//2 * oneHalf
