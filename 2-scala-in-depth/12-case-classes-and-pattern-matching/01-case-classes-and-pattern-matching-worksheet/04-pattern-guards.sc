/* Class hierarchy for modeling mathematical expressions */

abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

/* won't compile!
def simplifyAdd(e: Expr) = e match {
  case BinOp("+", x, x) => BinOp("*", x, Number(2)) // ERR: x is already defined
  case _ => e
}
*/

/* using pattern guards */
def simplifyAdd(e: Expr) = e match {
  case BinOp("+", x, y) if (x == y) => BinOp("*", x, Number(2)) // ERR: x is already defined
  case _ => e
}

simplifyAdd(BinOp("+", Var("x"), Var("x")))
simplifyAdd(BinOp("+", Number(2), Number(2)))


/* another examples */
def onlyForNegatives(e: Any) = e match {
  case n: Int if n < 0 => println(s"$e is negative")
  case _ =>
}

onlyForNegatives(-5)
onlyForNegatives(5)

def onlyForStringStartingWithA(e: Any) = e match {
  case s: String if s(0) == 'A' => println(s"$e starts with an 'A'")
  case _ =>
}

onlyForStringStartingWithA("Ahoy")
onlyForStringStartingWithA(5)
onlyForStringStartingWithA("Yay!")