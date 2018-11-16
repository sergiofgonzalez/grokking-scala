/* Patterns in variable definition: destructuring through patterns */

val myTuple = (123, "abc")

val (num, str) = myTuple
println(num)
println(str)

// a more contrived example
sealed abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

val exp = new BinOp("+", Number(5), Var("y"))
val BinOp(op, left, right) = exp
println(s"op=$op")
println(s"left=$left")
println(s"right=$right")

/* Case sequences in partial functions */

// this is read as:
// withDefaultZero is function literal for a function
// receiving an Option[Int] and returning an Int
val withDefaultZero: Option[Int] => Int = {
  case Some(x) => x
  case None => 0
}

withDefaultZero(Some(5))
withDefaultZero(None)

val withDefault: (Option[Int], Int) => Int = {
  case (Some(x), num) => x
  case (None, num) => num
}

withDefault(Some(5), -1)
withDefault(None, -1)

// the same with a proper function instead of a function literal
def getWithDefault(v: Option[Int], defVal: Int) = {
  v match {
    case Some(x) => x
    case None => defVal
  }
}

getWithDefault(Some(5), -5)
getWithDefault(None, -5)

// partially applied functions
// This is read as second is a function literal for
// a function receiving a List of Ints and returning an Int
val second: List[Int] => Int = {
  case x :: y :: _ => y
}

second(List(1, 2, 3))
second(List(1, 2))
// second(List())  // Err: scala.MatchError

// to make it safer you can use PartialFunction

val secondFL: PartialFunction[List[Int], Int] = {
  case x :: y :: _ => y
}


if (secondFL.isDefinedAt(List(1, 2, 3)))
  secondFL(List(1, 2, 3))
else
  println("Function cannot be applied to List(1, 2, 3")

if (secondFL.isDefinedAt(List()))
  secondFL(List())
else
  println("Function cannot be applied to List()")

/* Patterns in for expressions */
val capitals = Map("France" -> "Paris", "Germany" -> "Berlin", "Spain" -> "Madrid")
for ((country, capital) <- capitals)
  println(s"The capital of $country is $capital")

val results = List(Some("orange"), None, Some("apple"))
for (Some(fruit) <- results)
  println(fruit)