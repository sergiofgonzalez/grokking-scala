
// Mathematical operators
val sum = 1 + 2
val sum2 = 1.+(2)
val sum3 = 1.+(2L)

// The syntax can be used with regular methods as well
val s = "Hello, world!"
val commaPos = s.indexOf(",")
val commaPosAlt = s indexOf ","

// And it works with methods accepting several args
val s = "Hello to Jason Isaacs!"
val oPosAfter5 = s indexOf ("o", 5)

/* prefix */
val neg = -7
val negAlt = 7.unary_-

/* postfix */
val s = "HELLO"
val sLower = s.toLowerCase
val sLowerOp = s toLowerCase

/* Operator Precedence */
2 << 2 + 2

// The exception on operators ending in `=`
var x = 3
val y = 5
x *= y + 1 // <- 18
x

/* Operator associativity */
val aList = List(1, 2, 3)
val bList = List(4, 5, 6)
val resultList = aList ::: bList

// as the operator ":::" ends in ":" it is invoked in the right operand, passing in the left
val resultListAlt = bList.:::(aList)

val num1 = 5.0
val num2 = 7.0
val num1DividedByNum2 = num1 / num2
val num1DividedByNum2Alt = num1./(num2)

// Associativity between same precedence
val aList = List(1, 2, 3)
val bList = List(4, 5, 6)
val cList = List(7, 8, 9)
val resultList = aList ::: bList ::: cList
val resultListGrouped = aList ::: (bList ::: cList)

val num1 = 5.0
val num2 = 7.0
val num3 = 9.0
val result = num1 / num2 / num3
val resultGrouped = (num1 / num2) / num3
