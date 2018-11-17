# Part 2 &mdash; Scala In Depth: Case Classes and Pattern Matching
> pattern matching in Scala

---
+ Introducing Case Classes and Pattern Matching
+ Exploring the kinds of Patterns
+ Pattern guards
+ Order of cases in Pattern Matching
+ Sealed Classes
+ The `Optional` type
+ Patterns common use-cases: patterns in variable definitions, case sequences in partial functions and patterns in for expressions
+ A large example with case classes and pattern matching: an arithmetic expression formatter
---

## Intro
Case classes and pattern matching are constructs that support writing regular, non-encapsulated data structures. In particular, those two constructs are particularly useful for tree-like recursive data.

## A Simple Example
Let's assume you need to write a library that manipulates arithmetic expressions. A first step to tackling this problem is the definition of the input data. We'll focus on arithmetic expressions consisting of variables, numbers and unary and binary operations:

```scala
abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr
```

We've created a class hierarchy that expresses all the things we need. Note that for now the bodies of the classes are empty.

### Case Classes
Note that each class definition includes the modifier keyword `case`. Using that modifier makes the Scala compiler add some syntactic conveniences to your class.

First, it adds a *factory method* with the name of the class, so that you can do:
```scala
val v = Var("x")
```

Those factory methods are particularly useful when you nest them:

```scala
val op = BinOp("+", Number(1), Var("x"))
```

Secondly, all the arguments in the parameter list implicitly get a `val` prefix, so they are maintained as *class fields*:

```scala
val v = Var("x")
v.name // -> x

val op = BinOp("+", Number(1), Var("x"))
op.left // -> Expr = Number(1.0)
```

Thirdly, the compiler adds *natural* implementations of `toString`, `hashCode` and `equals` to your class. The implementations will *print*, *hash* and *compare a whole tree consisting of the class and (recursively) all its arguments*. Since `==` in Scala delegates to `equals`, this means you can do:

```scala
scala> println(op)
BinOp(+, Number(1.0), Var(x))

scala> op.right == Var("x")
res3: Boolean = true
```

Finally, the compiler adds a copy method to your class for making modified copies. This method will be useful when you need a new instance of the class that is the same as another except for one or two attributes that are different:

```scala
scala> op.copy(operator="-")
res4: BinOp = BinOp(-, Number(1.0), Var(x))
```

### Pattern Matching
Now let's assume that we want to add some simplification rules to the arithmetic expressions we're modeling:
+ Double Negation &mdash; UnOp("-", UnOp("-", whatevs)) => whatevs
+ Adding Zero &mdash; BinOp("+", whatevs, Number(0)) => whatevs
+ Multiplying by One &mdash; BinOp("*", whatevs, Number(1)) => whatevs

Pattern matching can be used to implement such a simplification function in Scala:

```scala
def simplifyTop(expr: Expr): Expr = expr match {
  case UnOp("-", UnOp("-", e))  => e     // Double negation
  case BinOp("+", e, Number(0)) => e     // Adding zero
  case BinOp("*", e, Number(1)) => e     // Multiplying by one
  case _ => expr
}


scala> simplifyTop(UnOp("-", UnOp("-", Var("x"))))
res4: Expr = Var(x)
```

Let's dissect the syntax for the pattern matching and its behavior.
The right-hand side of the function consists of a *match expression*, which looks similar to a *switch* in Java, but it's written after the selector expression:

```scala
selector match { alternatives }
```

while in Java, the switch expression is `switch (selector) { alternatives }`.

A pattern match includes a sequence of alternatives, each starting with the keyword `case`. Each alternative includes a pattern and one or more expressions which will be evaluated if the pattern matches. An arrow symbol `=>` will separate the pattern from the expression.

A *constant pattern* like `"+"` or `1` matches values that are equal to the constant with respect to `==`.
A *variable pattern* like `e` matches every value. The variable then refers to that value in the right hand side of the case clause.
A *constructor pattern* looks like `Unop("-", e)`. This pattern matches all values of type `UnOp` whose first argument matches `"-"` and whose second argument matches `e`.
The *wildcard pattern* (`_`) also matches every value, but it does not introduce a variable name to refer to that value.

In the example, note that the first three alternatives evaluate to `e`, a variable that is bound to the associated pattern. Note also that the match ends with a default case that does nothein to the expression. Instead, it just results in `expr` &mdash; the expression matched upon.
In the example, the arguments passed to the constructor pattern are themselves patterns. This allows you to write *deep patterns* using a very concise notation (e.g. `UnOp("-", UnOp("-", e)))`).

### match compared to switch
Although *match expressions* can be seen as a generalization of Java switches there are obvious differences between them:
+ A *match* is an expression &mdash; it returns a value
+ A *match expression* never falls through the alternatives
+ If none of the patterns match, a `MatchError` exception will be thrown &mdash; you have to make sure that all cases are covered even when there's nothing to do:
```scala
expr match {
  case BinOp(op, left, right) => println(s"$expr is a binary operation")
  case _ =>  // NOOP
}
``` 

Note also that a Java-style switch with breaks in all alternatives can be expressed as a match expression on which each pattern is a constant and the last pattern may be a wildcard.

## Kinds of Patterns
The syntax of patterns is transparent and easy to understand. For example, for the previous class hierarchy code sample, the pattern `Var(x)` recreates an equivalent object, assuming `x` is already bound to a variable's name.
The important point is to understand all kinds of possible patterns.

### Wildcard Patterns
The wildcard pattern (`_`) matches any object whatsoever:

```scala
expr match {
  case BinOp(op, left, right) =>
    println(expr + " is a binary operation")
  case _ => // do nothing for the default case
}
```

Wildcards can also be used to ignore parts of an object that you don't care about. For example:

```scala
expr match {
  case BinOp(_, _, _) =>
    println(expr + " is a binary operation")
  case _ => // do nothing for the default case
}
```

### Constant Patterns
A constant pattern matches only itself. Any literal may be used as a constant. Also any *val* or *singleton object* can be used as a constant. For example `Nil` (the empty list) is a pattern that matches only the empty list.

```scala
def describe(x: Any) = x match {
  case 5 => "five"
  case true => "truth"
  case "hello" => "hi!"
  case Nil => "the empty list"
  case _ => "something else"
}
```

### Variable Patterns
A variable pattern matches any object, just lie a wildcard. But unline a wildcard, Scala binds the variable to whatever the object is, so that you can act on the object for other purposes.

```scala
expr match {
  case 0 => "zero"
  case somethingElse => s"Not zero: $somethingElse"
}
```
#### Constants vs. Variables in Patterns
Constant patterns can have symbolic names:
```scala
import math.{E, Pi}

E match {
  case Pi => s"strange math? Pi = $Pi"
  case _ => "OK"
}
```

Scala knows that Pi is a constant imported from `scala.math` and not a variable using a lexical rule for disambiguation: a simple name starting with a lowercase letter is taken to be a pattern variable; all other references are taken to be constants.
As a consequence:

```scala
// won't compile
import math.{E, Pi}

E match {
  case pi => s"strange math? Pi = $Pi"
  case _ => "OK" // Err: unreachable code
}
```

The previous code won't compile because `pi` is taken as a pattern variable instead of a constant and therefore, `case _` alternative is unreachable because `case pi` will be a *catch-all* alternative.


Scala will let you use a lowercase pattern constant if you prefix it with a qualifier (e.g. if it is field of some object) or use it with *backticks*:

```scala
E match {
  case `pi` => s"strange math? Pi = $pi"
  case _ => "OK" // Err: unreachable code
}
```

### Constructor Patterns
Constructors are where pattern matching becomes really powerful. A constructor pattern looks like `BinOp("+", e, Number(0))`.
It consists of a name (`BinOp`) and then a number of patterns within parentheses: `"+"`, `e` and `Number(0)`. Assuming the name designates a case class, such a pattern measn to first check that the object is a member of the named case class, and then to check that the constructor parameters of the object match the extra patterns supplied.

Therefore, Scala patterns support *deep matches* &mdash; not only the top-level object is checked, but also the contents of the object against further patterns.

```scala
expr match {
  case BinOp("+", e, Number(0)) => println("a deep match")
  case _ =>
}
```

### Sequence Patterns
You can match against sequence types, like `List` and `Array`.

```scala
expr match {
  case List(0, _, _) => println(s"found 3-element list starting with zero: $expr")
  case _ =>
}
```

The previous sample checks for three elements lists whose first element is zero.

If you want to match against a sequence without specifying how long it can be, you can specify _* as the last element of the pattern:

```scala
expr match {
  case List(0, _*) => println(s"found list starting with zero: $expr")
  case _ =>
}
```

### Tuple Patterns
A pattern like (a, b, c) matches an arbitrary 3-tuple:

```scala
expr match {
  case (a, b, c) => println(s"matched: ($a, $b, $c)")
  case _ =>
}
```

### Typed Patterns
You can use a *typed pattern* as a convenient replacement for type tests and type casts:

```scala
def generalSize(x: Any) = x match {
  case l: List[Any] => l.size  
  case s: String => s.size
  case m: Map[_, _] => m.size
  case _ => -1
}
```

Note that even though `s` and `x` refers to the same value, the type of `x` is `Any` and the type of `s` is `String`. That's why you can write `s.size` without raising errors &mdash; you're actually typecasting `Any` into a `String`.

An alternative way is Scala's typecast syntax which is:

```scala
expr.isInstanceOf[String] // => true if expr can casted into a String
expr.asInstanceOf[String] // => typecasts expr into a String
```

Therefore, typecasting in Scala without pattern matching would be:
```scala
if (x.isInstanceOf[String]) {
  val s = x.asInstanceOf[String]
  s.length
}
```

#### Type Erasure
Note that because Scala uses the erasure model of generics (as Java does), checking a map with specific types won't work:

```scala
// won't compile
def isMapOfInts(x: Any) = x match {
  case m: Map[Int, Int] => println("Map of ints")
  case _ =>
}

isMapOfInts(Map(1 -> 1, 2->2 ))  // => true
isMapOfInts(Map("a" -> "alfred", "b" -> "bruce"))  // => true (wait, what?)
```

Because of erasure, no information about type arguments is maintained at runtime and therefore, there is no way to determine at runtime whether a given `Map` object has been created with `Int` arguments or not.

The only exception to the erasure rule is arrays, because they are handle specially in Java as well as in Scala.

```scala
def isStringArray(x: Any) = x match {
  case _: Array[String] => true
  case _ => false
}

isStringArray(Array("Hello"))  // => true
isStringArray(Array("Hello", "to", "Jason")) // => true
isStringArray("Hello") // => false
```

### Variable Binding
In addition to the standalone variable patterns, you can also add a variable to any other pattern. You simply write the variable name, the `@` sign and then the pattern.
This gives you a variable-binding pattern, which means the pattern is to perform the pattern match as normal, and if the pattern succeeds, set the variable to the matched obect just as with a simple variable pattern.

The following example shows a pattern match that looks for the absolute value operation being applied twice in a row. If the entire pattern match succeeds, then the portion that matched the `Unop("+", _)` part is made available as variable `e`.
```scala
def variableBindingPatternMatching(expr: Any) = expr match {
  case UnOp("+", e @ UnOp("+", _)) => e
  case _ =>   
}

variableBindingPatternMatching(UnOp("+", Number(1)))              // -> ()
variableBindingPatternMatching(UnOp("+", UnOp("-", Var("x"))))    // -> ()
variableBindingPatternMatching(UnOp("+", UnOp("+", Number(1.0)))) // -> UnOp("+", Number(1.0))
```

## Pattern Guards
Sometimes, syntactic pattern matching is not precise enough. For example, assume that you are given the task of formulating a simplification rule that replaces sum expressions with two identical operands:
```scala
BinOp("+", Var("x"), Var("x"))
```
should be transformed into:
```scala
BinOp("*", Var("x"), Number(2))
```

If you try to define this rule as:
```scala
// won't compile
def simplifyAdd(e: Expr) = e match {
  case BinOp("+", x, x) => BinOp("*", x, Number(2))
  case _ => e
}
```
the Scala compiler will complain because Scala restricts patterns to be linear &mdash; a pattern variable may only appear once in a pattern. 
The solution consists in reformulating the match with a *pattern guard*:
```scala
def simplifyAdd(e: Expr) = e match {
  case BinOp("+", x, y) if x == y => BinOp("*", x, Number(2))
  case _ => e
}
```

A pattern guard comes after the pattern and starts with an if. The guard can be an arbitrary boolean expression, which typically refers to variables in the pattern. If a pattern guard is present, the match succeeds only if the guard evaluates to true.

Some other useful examples for *pattern guards*:
 ```scala
 case n: Int if 0 < n => ...
 case s: String if s(0) == 'a' => ...
 ```

## Pattern Overlaps
Patterns are tried in the order in which they are written. Therefore, the order is important:
```scala
def simplifyAll(expr: e): Expr = expr match {
  case UnOp("-", UnOp("-", e)) => simplifyAll(e)
  case BinOp("+", e, Number(0)) => simplifyAll(e)
  case BinOp("*", e, Number(1)) => simplifyAll(e)
  case UnOp(op, e) => UnOp(op, simplifyAll(e))
  case BinOp(op, l, r) => BinOp(op, simplifyAll(l), simplifyAll(r))
  case _ => expr
}
```

Note that appart from the obvious simplifications of the first three rules, the fourth and the fifth cases recursively apply the `simplifyAll` to the operands. In this example, it is important that the *catch-all* cases for the unary and binary operations are placed after the more specific rules. Otherwise, the compiler will complain because some of the rules would be unreachable:
```scala
def simplifyBadImpl(expr: e): Expr = expr match {
  case UnOp(op, e) => UnOp(op, simplifyAll(e))
  case UnOp("-", UnOp("-", e)) => simplifyAll(e) // Err: unreachable code
...
}
```

## Sealed Classes
Whenever you write a pattern match, you need to make sure that you have covered all of the possible cases. This becomes challenging when new classes are defined and there is no sensible *catch-all* default case for your code.

When you are facing this situation, you can make the superclass of your case classes *sealed*. A *sealed* class cannot have any new subclasses added except for the ones in the same file. This means that you only need to worry about the subclasses you already know. Also, the Scala compiler will flag missing combinations of patterns when you match against case classes that inherit from a sealed class.

The following piece of code illustrates how to do that:

```scala
sealed abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr
```

Then, if you define a pattern match where some of the possible cases are left out:
```scala
def describe(e: Expr): String = e match {
  case Number(_) => "a number"
  case Var(_) => "a variable"
}
```
the compiler will report warnings telling you that the match is not exhaustive.

When you know for a fact that it will not happen (i.e. `describe` will be only used for *Vars* and *Numbers*), use a default value that throws and exception and the warning will go away:
```scala
def describe(e: Expr): String = e match {
  case Number(_) => "a number"
  case Var(_) => "a variable"
  case _ => throw RuntimeException
}
```

or add an `@unchecked` annotation:

```scala
def describe(e: Expr): String = (e: @unchecked) match {
  case Number(_) => "a number"
  case Var(_) => "a variable"
}
```

## The Option Type
Scala has a standard type named `Option` for *optional values*. Such a value can be of two forms:
+ `Some(x)` &mdash; where `x` is the actual value
+ `None` &mdash; which represents a missing value

Optional values are produced by some of the standard operations on Scala's collections. For example. `Map.get` produces an optional value as the given key might not be found in the map.

```scala
scala> val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")

scala> capitals get "France"
res0: Option[String] = Some(Paris)

scala> capitals get "Spain"
res0: Option[String] = None
```

The most common way to take optional values apart is to use *pattern matching*:
```scala
def show(x: Option[String]) = x match {
  case Some(s) => s
  case None => "n/a"
}

show(capitals get "Japan") // -> Tokyo
show(capitals get "Germany") // -> "n/a"
```

Scala encourages the use of `Option` to indicate an optional value instead of Java's *null*.

## Patterns Everywhere
The following section illustrates other use cases beyond standalone match expressions, on which patterns are useful.

### Patterns in variable definition 
Anytime you define a `val` or a `var` you can use a pattern instead of a simple identifier. For example, you can destructure a tuple and assign each of its parts to its own variable:

```scala
val myTuple = (123, "abc")

val (number, str) = myTuple // number = 123; str = "abc"
```

You can even do more complicated things with case classes as seen below:

```scala
val exp = BinOp("*", Number(5), Number(11))
val BinOp(op, left, right) = exp // op = "*"; left = Number(5); right = Number(11)
```

### Case Sequences as Partial Functions
A sequence of *cases* in curly braces can be used anywhere a function literal can be used. Instead of having a single entry point and list of parameters, a case sequence has multiple entry points, each with their own list of parameters. Each case can be seen as an entry point to the function, and the parameters are specified with the pattern:

```scala
val withDefault: Option[Int] => Int = {   // function variable that receives an Option[Int] and returns an Int
  case some(x) => x
  case None => 0
}

withDefault(Some(10)) // => Int = 10
withDefault(None)     // => Int = 0
```

A more contrived example, in which a function has complicated logic for each of the entrypoints:
```scala
def receive = {
  case Data(byte) =>
    sum += byte
  
  case GetChecksum(requester) =>
    val checksum = ~(sum & 0xFF) + 1
    requester ! checksum
}
```

One other generalization is worth noting: a sequence of cases gives you a *partial* function. If you apply such a function on a value it does not support, it will generate a run-time exception.
For example:

```scala
// returns the second element of a list of integers
val second: List[Int] => {
  case x :: y :: _ => y
}
```

The function will succeed if you pass a a List with three elements, but will fail with empty lists:

```scala
second(List(5, 6, 7)) // => 6
second(List()) // runtime err!
```

If you want to check whether a partial function is defined, you must first tell the compiler that knwo you are working with partial funcions. This can be done with the following syntax:

```scala
val second: PartialFunction[List[Int], Int] = {
  case x :: y :: _ => y
}
```

This will let you use the `isDefinedAt` method:

```scala
second.isDefinedAt(List(5, 6, 7)) // => true
second.isDefinedAt(List())  // => false
```

Behind the scene, the Scala compiler does the following:
```scala
new PartialFunction[List[Int], Int] {
  def apply(xs: List[Int]) = xs match {
    case x :: y :: _ => y
  }
  def isDefinedAt(xs: List[Int]) = xs match {
    case x :: y :: _ => true
    case _ => false
  }
}
```

In general, it is recommended working with complete functions whenever possible to prevent runtime errors. When that cannot be done, using the `PartialFunction` type as it will let you check whether the function can be applied or not.

### Patterns in *for expressions*
Patterns can also be used in *for expressions*:

```scala
// this looks like destructuring to me :p
for ((country, city) <- capitals)
  println(s"The capital of $country is $city")
```

A more interesting case consists in using a pattern that might not match:

```scala
val results = List(Some("apple"), None, Some("orange"))
for (Some(fruit) <- results)
  println(fruit)
```

## A Larger Example
The proposed scenario is to write an expression formatter class that displays an arithmetic expression in a two-dimensional layout.
For example, `x/(x + 1)` should be displayed as:
```
  x
-----
x + 1
```

And `((a/(b*c)+1/m)/3)` should be printed as:
```
  a     1
----- + -
b * c   n
---------
    3
```

For the implementation, we'll use the layout library already developed, along with the `Expr` case classes.

Let's start planning the approach for the horizontal layout.
The expression:
```scala
BinOp("+",
      BinOp("*",
            BinOp("+", Var("x"), Var("y")),
            Var("z")
      ),
      Number(1)
)
```

should be displayed as: `(x + y) * z + 1`. Note that parentheses would be required around `x + y` but optional around `(x + y) * z`. We'll need to omit parentheses whenever they are redundant.

The precedence can be modeled creating groups in an array of increased precedence, but a more convenient approach is to just define groups of operators of increasing precedence and then calculate the precedence of each operator from that.

The following listing shows the complete code for the expression formatter:
```scala
// file: ./expressions/ExprFormatter
package expressions

sealed abstract class Expr

case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

class ExprFormatter {
  // Operators arrange in groups of increasing precedence
  private val opGroups =
    Array(
      Set("|", "||"),
      Set("&", "&&"),
      Set("^"),
      Set("==", "!="),
      Set("<", "<=", ">", ">="),
      Set("+", "-"),
      Set("*", "%")
      )

  // A mapping from operators to their precedence
  private val precedence = {
    val assocs =
      for {
        i <- 0 until opGroups.length
        op <- opGroups(i)
      } yield op -> i  // op -> i is called an association (until now, only seen in maps)
    assocs.toMap
  }

  private val unaryPrecedence = opGroups.length
  private val fractionPrecedence = -1

  import elements.Element
  import Element.elem

  private def format(e: Expr, enclPrec: Int): Element =
    e match {
      case Var(name) =>
        elem(name)

      case Number(num) =>
        def stripDot(s: String) =
          if (s endsWith ".0")
            s.substring(0, s.length - 2)
          else
            s
        elem(stripDot(num.toString))

      case UnOp(op, arg) =>
        elem(op) beside format(arg, unaryPrecedence)

      case BinOp("/", left, right) =>
        val top = format(left, fractionPrecedence)
        val bot = format(right, fractionPrecedence)
        val line = elem('-', top.width max bot.width, 1)
        val frac = top above line above bot
        if (enclPrec != fractionPrecedence)
          frac
        else
          elem(" ") beside frac beside elem(" ")

      case BinOp(op, left, right) =>
        val opPrec = precedence(op)
        val l = format(left, opPrec)
        val r = format(right, opPrec + 1)
        val oper = l beside elem(" " + op + " ") beside r
        if (enclPrec <= opPrec) oper
        else elem("(") beside oper beside elem(")")
    }

    def format(e: Expr): Element = format(e, 0)
}
```

The precedence variable is a map from operators to their precedences, which are integers starting from 0. It is calculated using a *for expression* with two generators. The first generator produces every index `i` of the `opGroups` array. The second generator produces every operator `op` in `opGroups(i)`. For each such operator the *for expression* yields an *association* from the operator `op` to its index `i`.

*Associations* are written with an *infix arrow* (e.g. `op -> i`). So far, we've only seen association as part of map constructions but they're also values in their own right. In fact, `op -> i` is nothing but the pair `(op, i)`.

Now that the precedence of binary operators except `/` has been fixed (the division requires a special treatment because of the vertical layout required for them), it makes sense to generalize this concept to cover unary operators. The precedence of a unary operator is higher than the precedence of every binary operator. Thus, we can set `unaryPrecedence` to the length of the `opGroups` array, which is more than the precedence of the `*` and `%` operators. Division is handled by assigning the special precedence value `-1`.

After these preparations, you are ready to write the main format method. This method takes two arguments: an expression `e` of type `Expr` and the precedence `enclPrec` of the operator directly enclosing the expression `e`. The method yields a layout element that represents a 2D array of characters.

The `stripDot` method is a helper method. The private `format` method does most of the work to format the expressions. The public `format` is the only public method in the library. It takes the expression to format.

The private `format` performs a *pattern match* on the kind of expression. It has five cases.

The first case is:

```scala
case Var(name) =>
  elem(name)
```

If the expression is a variable, the result is an element formed from the variable's name.

The second case is:

```scala
case Number(num) =>
  def stripDot(s: String) =
    if (s endsWith ".0") s.substring(0, s.length - 2)
    else s
  elem(stripDot(num.toString))
```

If the expression is a number, the result is an element formed from the number's value. The `stripDot` function removes any `".0"` suffix from the resulting string.

The third case is:

```scala
case UnOp(op, arg) =>
  elem(op) beside format(arg, unaryPrecedence)
```

If the expression is a unary operation `UnOp(op, arg)`, the result is formed from the operation `op` and the result of formatting the argument `arg` with the highest possible environment precedence. This means that if arg is a binary operation (but not a fraction) it will always be displayed in parentheses.

The fourth case is:

```scala
case BinOp("/", left, right) =>
  val top = format(left, fractionPrecedence)
  val bot = format(right, fractionPrecedence)
  val line = elem('-', top.width max bot.width, 1)
  val frac = top above line above bot
  if (enclPrec != fractionPrecedence) frac
  else elem(" ") beside frac beside elem(" ")
```

If the expression is a fraction, an intermediate result frac is formed by placing the formatted operands left and right on top of each other, separated by an horizontal line element. The width of the horizontal line is the maximum of the widths of the formatted operands. This intermediate result is also the final result unless the fraction appears itself as an argument of another fraction. In the latter case, a space is added on each side of the `frac`. This is because otherwise, the expression `(a/b)/c` would be displayed as:
```
a
-
b
-
c
```
which does not clarify where the top-levl fractional bar is. However, with the correction, it will be displayed as:
```
 a
 -
 b
---
 c
```

The fifth and last case is:

```scala
case BinOp(op, left, right) =>
  val opPrec = precedence(op)
  val l = format(left, opPrec)
  val r = format(right, opPrec + 1)
  val oper = l beside elem(" " + op + " ") beside r
  if (enclPrec <= opPrec) oper
  else elem("(") beside oper beside elem(")")
```

This case will be applied for all other binary operations. As we know there's no division in place, to format it we just format the left and right operands. The precedence parameter for formatting the left operand is the precedence opPrec of the operator `op`, while for the right operand it is one more than that. This scheme ensures that parentheses also reflect the correct associativity.
For example, the operation:
```scala
BinOp("-", Var("a"), BinOp("-", Var("b"), Var("c")))
```
would be parenthesized as `"a - (b - c)"`. The intermediate result oper is then formed by placing the formatted left and right operands side-by-side, separated by the operator. If the precedence of the current operator is smaller than the precedenve of the enclosing operator, `oper` is placed between parentheses; otherwise it is returned as is.


You ca see a running example in [02 &mdash; Expression Formatter](./02-expression-formatter-app-sbt)

---
## You know you've mastered this chapter when...

+ You understand the concept of case classes &mdash; regular classes that are prefixed by the `case` modifier which adds some conveniences to the classes: factory methods; automatic field definition; natural implementation of `toString`, `hash` and `equals`; and the addition of the `copy` method.
+ You understand the syntax and behavior of the `match` expression in Scala, which is like a super-powered *switch* that allows you to implement functionality depending on whether *an expression matches a given selector*. You're aware of the differences between a *match expression* and a *switch* and that you need to provide an alternative for all the possible cases or an exception will be raised.
+ You're comfortable reading and writing pattern matches with wildcards, constants, variables, constructors, sequences, tuples, typed.
+ You're aware of how to use pattern matching to do typecasting in Scala, and how to do it using `isInstanceOf[Type]` and `asInstanceOf[Type]`.
+ You're aware of the variable binding using the `@` syntax.
+ You're aware of *pattern guards* &mdash; boolean expressions that appear after the match, and how it can be used to solve problems not covered by simple match expressions.
+ You know that the order for the cases while doing *pattern matching* is important, because the rules are tried from top to bottom.
+ You understand *sealed classes* and when those become useful when doing pattern matching (to prevent worrying about unknown subclasses).
+ You're comfortable using Scala's `Option` type to model optional values.
+ You recognize the different scenarios in which pattern matching is useful: destructuring in variable definition, partial functions and *for expressions*.
+ You understand partial function mechanisms, and are comfortable using `isDefinedAt` to check if a partial function can be safely called.
---


## Projects

### [01 &mdash; Case classes and Pattern Matching](./01-case-classes-and-pattern-matching-worksheet)
IntelliJ worksheet project with several worksheet illustrating the concepts of the section.

### [02 &mdash; Expression Formatter](./02-expression-formatter-app-sbt)
SBT app illustrating case-classes concepts in a larger example.

