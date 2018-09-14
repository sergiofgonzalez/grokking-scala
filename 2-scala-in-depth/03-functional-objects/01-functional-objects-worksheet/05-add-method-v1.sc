
class Rational(n: Int, d: Int) {
  require(d != 0)
  override def toString: String = s"$n/$d"
  def add(other: Rational): Rational = new Rational(n * other.d + other.n * d, d * other.d)
}

val oneHalf = new Rational(1, 2)
