
/*
   No body, so no curly braces
   Constructor synthesized by Scala compiler from class parameters
*/
class Rational(n: Int, d: Int)  

val oneHalf = new Rational(1, 2)
oneHalf.n // -> Err: n is not a member of Rational


/*
  Scala includes code found in the class body into the primary constructor
*/
class Rational(n: Int, d: Int) {
  println(s"Rational created: $n/$d")
}
val oneHalf = new Rational(1, 2)

// using previous feature for preconditions */
class Rational(n: Int, d: Int) {
  require(d != 0)
}
new Rational(1, 0)