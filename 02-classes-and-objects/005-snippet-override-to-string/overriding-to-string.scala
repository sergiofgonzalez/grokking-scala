/*
  Overriding toString
*/
class Rational(n: Int, d: Int) {
  override def toString = s"$n/$d"
}

val oneHalf = new Rational(1, 2) // -> oneHalf: Rational = 1/2

/*
  With preconditions
*/
class Rational(n: Int, d: Int) {
  require(d != 0)
  override def toString = s"$n/$d"
}
new Rational(1, 0)
new Rational(1, 2)