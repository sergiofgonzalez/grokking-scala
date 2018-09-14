
class Rational(n: Int, d: Int) {
  require(d != 0)
  override def toString: String = s"$n/$d"
}

val oneHalf = new Rational(1, 2)
new Rational(5, 0)