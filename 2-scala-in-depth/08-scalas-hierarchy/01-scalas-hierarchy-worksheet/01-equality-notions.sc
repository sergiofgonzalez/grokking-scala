/* In Scala it's safe to use `==` even when upcasting to `Any` */

val x = 555
val y = 555

println(x == y)

def isEqual(x: Any, y: Any): Boolean = x == y

println(isEqual(x, y))

val a = "abcd".substring(2)
val b = "abcd".substring(2)
a == b

/* reference equality */
val s1 = new String("abc")
val s2 = new String("abc")

s1 == s2 // -> true
s1 eq s2 // -> false
s1 ne s2 // -> true

val x1 = "abc"
val x2 = "abc"

x1 == x2 // -> true
x1 eq x2 // -> true
x1 ne x2 // -> true

