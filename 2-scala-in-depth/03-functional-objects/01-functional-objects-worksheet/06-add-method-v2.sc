
class Rational(n: Int, d: Int) {
  require(d != 0)
  val numer: Int = n
  val denom: Int = d
  override def toString: String = s"$n/$d"
  def add(other: Rational): Rational = new Rational(n * other.denom + other.numer * d, d * other.denom)
}

val oneHalf = new Rational(1, 2)
val twoThirds = new Rational(2, 3)

oneHalf.add(twoThirds)

// using operator syntax
oneHalf add twoThirds

// as a consequence, we can get the numer and denom
val n = oneHalf.numer
val m = twoThirds.denom