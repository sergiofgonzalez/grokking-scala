/* regular, non-curried, function */
def plainOldSum(x: Int, y: Int):Int = x + y

plainOldSum(3, 5)

/* curried version */
def curriedSum(x: Int)(y: Int):Int = x + y

curriedSum(3)(5)

// this is the decomposition of what happens
// first is a function that receives a single Int parameter y
// and returns a function that expects a single Int parameter y
// that will return the sum of both parameters
def first(x: Int) = (y: Int) => x + y

val second = first(3) // -> function that takes Int and returns Int
second(5) // -> 8

/* we can get a ref to the second function using partially applied functions syntax */
val onePlus = curriedSum(3) _
onePlus(5)
