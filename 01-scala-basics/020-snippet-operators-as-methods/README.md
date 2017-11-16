# 020 &mdash; Operators as Methods (snippet)
> in Scala, operators are just methods

## Description
Scala provides a rich set of operators for its basic types, but those are just ordinary method calls written with a nice *operator notation* syntax.

```scala
val sumNice = 1 + 2     // nice syntax: operator notation (infix)
val sumMethod = 1.+(2)  // ordinary method invocation
```

Any method can be used in the *operator notation*:
```scala
val commaPos = "Hello, world!" indexOf ","
```

The previous examples are known as *infix operator notation* as the method to invoke sits between the object and parameter (or parameters) you wish to pass to the method. 
Scala also supports *prefix* and *postfix operation notation*.

In the *prefix operator notation*, you put the method name before the object on which you are invoking the method:
```scala
val neg = -7
val negAlt = 7.unary_-
```

In the *postfix operator notation*, the method takes no args, and as it should not produce any side-effects, you'd have to leave off the parentheses:
```scala
val s = "HELLO"
val sLower = s.toLowerCase
val sLowerOp = s toLowerCase
```

### Operator Precedence
Given that Scala does not have operators, precedence is decided based on the first character of the method, from higher to lower precedence (except for the exception rule stated below):
+ (any other special character)
+ `* / %`
+ `+ -`
+ `:`
+ `= !`
+ `< >`
+ `&`
+ `^`
+ `|`
+ (all letters) 

Thus:
```scala
2 << 2 + 2  // <- 32
```
As `+` has a higher precedence than `<`, Scala performs:
```
2 << 2 + 2 = 2 << 4 = bin(10) << 4 = bin(100000) = 32
```

There is an exception to this rule for *the assignment operator*: If an operator ends in an equals character `=` and the operator is not one of the comparison operators (`<=`, `>=`, `==` or `!=`) then, the precedence of the operator is the same as that of a simple assignment `=`.

So for instance:
```scala
var x = 3
val y = 5

x *= y + 1 // <- 18
```

meaning it is evaluated as `x *= (y + 1)` although it starts with `*`, because the exception rule is applied.

### Associativity
The associativity determines the way in which an operation is computed when the operators that appear in an expression have the same precedence.

In Scala, associativity is determined by the last character of the operator:
+ any method ending in `:` is invoked on its right operand, passing in the left operand
+ any method ending in other character are invoked on its left operand, passing in the right operand

An example for the first case is the for list concatenation operator `:::`:
```scala
val aList = List(1, 2, 3)
val bList = List(4, 5, 6)
val resultList = aList ::: bList      // -> List(1, 2, 3, 4, 5, 6)
val resultListAlt = bList.:::(aList)  // -> List(1, 2, 3, 4, 5, 6)
```

An example for the second case is the division:
```scala
val num1 = 5.0
val num2 = 7.0
val num1DividedByNum2 = num1 / num2         // <- 0.7142...
val num1DividedByNum2Alt = num1./(num2)     // <- 0.7142...
```

It is also important to note that:
+ Operands are always evaluated from left to right.
+ Operators of the same precedence ending in `:` are grouped from right to left => a ::: b ::: c == a ::: (b ::: c)
+ Operators of the same precedence ending in other than `:` are grouped from left to right => a / b / c == (a / b) / c

And as a general guideline when writing code that depends on this obscure precedence rules for added clarity: **use parentheses** to make it clear how the operation will be computed (e.g. `2 << 2 + 2` is more obscure than `2 << (2 + 2)`)

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load operators-as-methods.scala
```