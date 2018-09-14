
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
  def add(other: Rational): Rational = new Rational(numer * other.denom + other.numer * denom, denom * other.denom)
  def lessThan(other: Rational): Boolean = this.numer * other.denom < other.numer * this.denom
  def max(other: Rational): Rational = if (this lessThan other) other else this

  // private methods
  private def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else gcd(b, a % b)
  }
}


val fourSixths = new Rational(4, 6)
val r = new Rational(66, 42)

