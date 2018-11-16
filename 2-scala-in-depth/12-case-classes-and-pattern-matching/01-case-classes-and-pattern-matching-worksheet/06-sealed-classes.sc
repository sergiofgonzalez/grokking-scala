/* Sealed Class hierarchy for modeling mathematical expressions
 * Making an abstract class sealed ensures that no other class (apart from the ones defined in the same source file
 * as the sealed class) can inherit from it.
 */

sealed abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

/* Warning: match may not be exhaustive */
def describeNonExhaustive(e: Expr): String = e match {
  case Number(_) => "a number"
  case Var(_) => "a variable"
}

def describeExhaustiveThrowing(e: Expr): String = e match {
  case Number(_) => "a number"
  case Var(_) => "a variable"
  case _ => throw new IllegalArgumentException
}

def describeExhaustiveAnnotation(e: Expr): String = (e: @unchecked) match {
  case Number(_) => "a number"
  case Var(_) => "a variable"
}


describeExhaustiveAnnotation(UnOp("-", Number(1))) // Err: scala.MatchError