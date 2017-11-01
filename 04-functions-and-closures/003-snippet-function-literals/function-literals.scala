(x: Int) => x + 1

/* store in a var and call */
val inc = (x: Int) => x + 1
inc(10)

/* function literals are not limited to one-liners */
var fn = (x: Int) => {
  println("Hello to Jason Isaacs!")
  x + 1
}

fn(9999)

/* 
  Function literals are really convenient as arguments
  for functions
*/
val numbers = List(1, -5, 10, 25, 4)
numbers.foreach((elem: Int) => println(elem))

numbers.filter(elem => elem > 0)