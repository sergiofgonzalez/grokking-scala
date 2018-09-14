
class Rational(n: Int, d: Int) {
  require(d != 0)
  val numer: Int = n
  val denom: Int = d
  override def toString: String = s"$n/$d"
  def add(other: Rational): Rational = new Rational(n * other.denom + other.numer * d, d * other.denom)
  def lessThan(other: Rational): Boolean = this.numer * other.denom < other.numer * this.denom
  def max(other: Rational): Rational = if (this lessThan other) other else this
}

val oneThird = new Rational(1, 3)
val twoFifths = new Rational(2, 5)

oneThird lessThan twoFifths
twoFifths lessThan oneThird
twoFifths lessThan twoFifths

oneThird max twoFifths
twoFifths max oneThird
oneThird max oneThird