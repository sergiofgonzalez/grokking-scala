val sum = 1 + 2

val uglySum = (1).+(2)

// The syntactic rules for operators can be applied to any method
val s = "Hello to Jason Isaacs!"
s.indexOf('J')

s indexOf 'J'

// And can be applied to methods taking several params
s.indexOf('o', 5)
s indexOf('o', 5)

// infix and postfix are also supported, but the method name
// must feature `unary_` in the name
val minus2 = -2.0
val uglyMinus2 = (2.0).unary_-

// Logical operators and short-circuiting
def salt(): Boolean = { println("salt"); true }
def pepper(): Boolean = { println("pepper"); false }
salt() && pepper()  // both required to determine the result
salt() || pepper()  // only left is needed
salt() | pepper()   // only left is needed but disabling short-circuiting

// Equality with objects
val aList = List(1, 2, 3)
val anotherList = List(1, 2, 3)
aList == anotherList

1 == 1.0
"hello" == List(1, 2, 3)

List(1, 2, 3) == null
null == List(1, 2, 3)