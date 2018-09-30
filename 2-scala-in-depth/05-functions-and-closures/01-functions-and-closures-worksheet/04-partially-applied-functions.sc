/* using the placeholder syntax to substitute an entire parameter list */
val numbers = List(-10, -5, 0, 5, 10)

numbers.foreach(println(_))
numbers.foreach(println _)

/* passing params to a function is called applying the function to a param list */
val sum = (a: Int, b: Int, c: Int) => a + b + c
sum(1, 2, 3)

// it does not have to be a function literal
def prod(a: Int, b: Int, c: Int) = a * b * c
prod(1, 2, 3)


/* Partially applied function with no args */
val a = prod _
a(1, 2, 3)

a.apply(1, 2, 3)

val b = sum _
//b(1, 2, 3) //this does not work
b()(1, 2, 3) // but this does work (whatever this is)


/* this is not allowed */
//val c = prod

val weirdProd = prod(2, _:Int, 3)
weirdProd(5)

// when applying none of the params you can use the function itself (no underscore)
numbers.foreach(println)