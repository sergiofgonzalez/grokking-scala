
class Rational(n: Int, d: Int) {
  // precondition
  require(d != 0)

  // fields
  val numer: Int = n
  val denom: Int = d

  // auxiliary constructors
  // def this(n: Int) = new Rational(n, 1) // not allowed: `this` expected
  def this(n: Int) = {
    required(n > 5) // not allowed, `this` expected
    this(n)
  }


  // toString override
  override def toString: String = s"$n/$d"

  // methods
  def add(other: Rational): Rational = new Rational(n * other.denom + other.numer * d, d * other.denom)
  def lessThan(other: Rational): Boolean = this.numer * other.denom < other.numer * this.denom
  def max(other: Rational): Rational = if (this lessThan other) other else this
}


val five = new Rational(5)