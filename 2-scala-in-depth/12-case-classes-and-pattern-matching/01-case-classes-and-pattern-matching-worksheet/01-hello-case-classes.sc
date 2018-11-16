
/* Class hierarchy for modeling mathematical expressions */

abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

/* What do we get for declaring the case classes */

// First: you get a factory method for the case classes
val v = Var("x")
val addOp = BinOp("+", Number(1.0), v)

// Second: all arguments get a val prefix (defined as fields)
v.name
addOp.left
addOp.right
(addOp.right).asInstanceOf[Var].name // Scala casting

// Third: you get `toString`, `hash` and `equals` implementation
println(addOp)
println(v.hashCode())
addOp == BinOp("+", Number(1.0), Var("x"))

// Fourth: you get the copy operation
val subOp = addOp.copy(operator = "-")