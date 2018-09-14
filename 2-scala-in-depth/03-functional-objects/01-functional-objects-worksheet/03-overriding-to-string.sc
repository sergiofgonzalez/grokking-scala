
class Rational(n: Int, d: Int) {
  override def toString: String = s"$n/$d"
}

val oneHalf = new Rational(1, 2)