/*
  Placeholder syntax can be used to substitute an entire parameter list
*/
val numbers = List(1, -5, 7, -4, 10)
numbers.foreach(println(_)) // same as x => println(x)
println("==========================")

numbers.foreach(println _) // same as x => println(x)
println("==========================")

/*
  The concept of a partially applied function
*/
def sum(a: Int, b: Int, c: Int) = a + b + c
sum(1, 2, 3) // apply the function sum to arguments 1, 2, 3

val a = sum _ // partially applied function
a(1, 2, 3)    // invoke sum with 1, 2, 3

val b = sum(1, _: Int, 3)  // now it is evident why it's called a partially applied function
b(2)
println("==========================")

/*
  You can leave off the placeholder at all when you're not binding any arg
  (this only works when a function is expected as an argument)
*/
val numbers = List(1, -5, 7, -4, 10)
numbers.foreach(println) // same as x => println(x)
