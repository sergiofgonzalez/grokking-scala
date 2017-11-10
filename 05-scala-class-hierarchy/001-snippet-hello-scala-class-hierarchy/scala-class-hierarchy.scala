/* AnyVal */
new Int  // AnyVal instances cannot be created with new
val num: Int = 42
num.hashCode
num.##

// Implicit conversions add more functionality to value types: all defined in scala.runtime.RichInt
42 max 45
42 min 45
42 until 45
42 to 45
(-42).abs
println("==\n")

/*
  Primitives in Scala behave much more sensibly than in Java
*/
def isEqual(x: Int, y: Int) = x == y

isEqual(123, 123)

def isEqualVal(x: AnyVal, y: AnyVal) = x == y

isEqualVal(123, 123)
println("==\n")


/* For reference types, == also behaves more sensibly */
val x = "abcd".substring(2)
val y = "abcd".substring(2)
x == y
x != y
x eq y
x ne y

println("==\n")
