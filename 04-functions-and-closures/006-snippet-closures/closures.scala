(x: Int) => x + more // Err: more was not found

/*
  Closure: function literal that uses free vars (outside of its scope)
*/
var more = 1
val addMore = (x: Int) => x + more
addMore(10)
println("==")

/*
  Closures reference vars, not their values
*/
// now we change more!
more = 2
addMore(10)
println("==")

/*
  Also changes made by the closure affect the free vars
*/
val incMore = (x: Int) => more += x
incMore(3)
more

/*
  Similar example to compute a the sum
  of the elements of a list
*/

val numbers = List(-11, -10, -5, 0, 5, 10)
var sum = 0
numbers.foreach( sum += _ )
sum
println("==")

/*
  The free vars are bound to the closure
  at the time of closure creation
*/
def makeIncreaser(more: Int) = (x: Int) => x + more
val inc1 = makeIncreaser(1)
inc1(5) // <- 6

val inc5 = makeIncreaser(5)
inc5(5) // <- 10