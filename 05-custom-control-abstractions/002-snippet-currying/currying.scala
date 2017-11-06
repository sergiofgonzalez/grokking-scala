/*
  Non-curried function which adds two int args
*/
def plainOldSum(x: Int, y: Int) = x + y

plainOldSum(2, 3)
println("==")

/*
  curried function
*/
def curriedSum(x: Int)(y: Int) = x + y
curriedSum(2)(3)
println("==")

/*
  Another example to understand
  what's happening behind the scenes
*/
def first(x: Int) = (y: Int) => x + y
val second = first(2)
second(3)
println("==")

/*
  You can use the placeholder syntax
  to get a ref to the function value
  returned by curriedSum when applied
  to the first param
*/
def curriedSum(x: Int)(y: Int) = x + y
val twoPlus = curriedSum(2)_
twoPlus(3)
println("==")