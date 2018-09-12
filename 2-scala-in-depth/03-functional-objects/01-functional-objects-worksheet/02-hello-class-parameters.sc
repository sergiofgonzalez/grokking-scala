// class parameters can be used directly int the class's body
// without defining them
class Rational(n: Int, d: Int) {
  println(s"Created $n/$d")
}

val oneHalf = new Rational(1, 2)