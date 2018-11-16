
/* Class hierarchy for modeling mathematical expressions */

abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

/* general */
def simplifyTop(expr: Expr): Expr = expr match {
  case UnOp("-", UnOp("-", e))  => e     // Double negation
  case BinOp("+", e, Number(0)) => e     // Adding zero
  case BinOp("*", e, Number(1)) => e     // Multiplying by one
  case _ => expr
}

val unaryOperation = UnOp("-", UnOp("-", Number(5)))
simplifyTop(unaryOperation)

val binaryOperation = BinOp("+", Number(5), Number(0))
simplifyTop(binaryOperation)

val anotherBinaryOperation = BinOp("*", Number(5), Number(1))
simplifyTop(anotherBinaryOperation)

val nonSimplifiableOperation = BinOp("+", Number(5), Var("x"))
simplifyTop(nonSimplifiableOperation)

/* Wildcard patterns */
def wildcardPatternMatchingSample(expr: Expr) = {
  expr match {
    case BinOp(op, left, right) =>
      println(s"$expr is a binary operation")
    case _ => // NOOP for non binary operations
  }
}

wildcardPatternMatchingSample(unaryOperation)
wildcardPatternMatchingSample(binaryOperation)

// The wildcard character can be used also to ignore parts you don't care about
def wildcardPatternMatchingSample2(expr: Expr) = {
  expr match {
    case BinOp(_, _, _) =>
      println(s"$expr is a binary operation")
    case _ => // NOOP for non binary operations
  }
}

wildcardPatternMatchingSample2(unaryOperation)
wildcardPatternMatchingSample2(binaryOperation)

/* Constant Patterns */
def describe(x: Any) = x match {
  case 5 => "five"
  case true => "truth"
  case "hello" => "hi!"
  case Nil => "the empty list"
  case _ => "something else"
}

describe(5)
describe(true)
describe("hello")
describe(List())
describe("yay!")


// Pi and E are taken as constants (instead of variables by the Scala compiler)
// because it is uppercase
import math.{E, Pi}

E match {
  case Pi => s"strange math? Pi = $Pi"
  case _ => "OK"
}

Pi match {
  case Pi => s"strange math? Pi = $Pi"
  case _ => "OK"
}

/* Variable Pattern */
def variablePatternMatchingSample(arg: Any) = arg match {
  case 0 => "zero"
  case somethingElse => s"Not zero: $somethingElse"
}

variablePatternMatchingSample(0)
variablePatternMatchingSample("Hello to Jason")

// pi is taken as a variable (instead of constant by the Scala compiler)
// because it is lowercase. Therefore, it won't compile as
// `case _` is unreachable code

/* Uncomment to test: it won't compile
import math.{E, Pi}

E match {
  case pi => s"strange math? Pi = $Pi"
  case _ => "OK"
}
*/

// using backticks to force Scala compiler to identify a lowercase
// entry as a constant (this does not compile either!!)

/* this should work but doesn't
import math.{E, Pi}
E match {
  case `pi` => s"strange math? Pi = $pi"
  case _ => "OK"
}
*/

/* Constructor Patterns */
def constructorPatternMatchingSample(expr: Expr) = expr match {
  case BinOp("+", e, Number(0)) => "a deep match"
  case _ => // NOOP
}

constructorPatternMatchingSample(BinOp("+", Var("x"), Number(0)))
constructorPatternMatchingSample(BinOp("+", Var("x"), Number(1)))

/* Sequence Patterns */
def sequencePatternMatchingSample(expr: Any) = expr match {
  case List(0, _, _) => println(s"found 3-element list starting with zero: $expr")
  case _ =>  // NOOP
}

sequencePatternMatchingSample(List(1, 2, 3))
sequencePatternMatchingSample(List(0, 1, 2, 3))
sequencePatternMatchingSample(List(0, 1, 2))


def sequencePatternMatchingSample2(expr: Any) = expr match {
  case List(0, _*) => println(s"found list starting with zero: $expr")
  case _ =>
}

sequencePatternMatchingSample2(List(1, 2, 3))
sequencePatternMatchingSample2(List(0, 1, 2, 3))
sequencePatternMatchingSample2(List(0))

/* Tuple patterns */

def tuplePatternMatchingSample(expr: Any) = expr match {
  case (a, b, c) => println(s"matched: ($a, $b, $c)")
  case _ =>
}

tuplePatternMatchingSample(("a", "1", "whatevs"))
tuplePatternMatchingSample((1.0, "x", "1", "whatevs"))

/* Typed patterns */
def generalSize(x: Any) = x match {
  case l: List[Any] => l.size
  case s: String => s.size
  case m: Map[_, _] => m.size
  case _ => -1
}

generalSize("Hello")
generalSize(Map(1 -> "one", 2 -> "two"))
generalSize(List(1, 2, 3))

// type erasure: the following won't work


// compiler emits warning
def isMapOfInts(x: Any) = x match {
  case m: Map[Int, Int] => println("Map of ints")
  case _ =>
}

isMapOfInts(Map(1 -> 1, 2->2 ))
isMapOfInts(Map("a" -> "alfred", "b" -> "bruce"))  // => true

// it works with arrays
def isStringArray(x: Any) = x match {
  case _: Array[String] => true
  case _ => false
}

isStringArray(Array("Hello"))
isStringArray(Array("Hello", "to", "Jason"))
isStringArray("Hello")

/* variable binding */

def variableBindingPatternMatching(expr: Any) = expr match {
  case UnOp("+", e @ UnOp("+", _)) => e
  case _ =>
}

variableBindingPatternMatching(UnOp("+", Number(1)))
variableBindingPatternMatching(UnOp("+", UnOp("-", Var("x"))))
variableBindingPatternMatching(UnOp("+", UnOp("+", Number(1.0))))

