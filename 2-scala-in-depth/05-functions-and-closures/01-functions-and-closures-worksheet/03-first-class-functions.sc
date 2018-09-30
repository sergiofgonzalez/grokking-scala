var increase = (x: Int) => x + 1
increase(10)

increase = (x: Int) => x + 99
increase(1)

increase = (x: Int) => {
  println("Hello")
  println("to")
  println("Jason Isaacs!")
  x + 1
}

increase(5)

/* Using function literals as arguments */
val numbers = List(-11, -10, -5, 0, 5, 10)
numbers.foreach((x: Int) => println(x))

numbers.filter((x: Int) => x > 0)

/* using more concise syntax */
numbers.filter(x => x > 0)

/* even more concise with placeholder */
numbers.filter(_ > 0)

/* but it's not always allowed */
// val sum = _ + _ // Err: missing parameter type for expanded function

val sum = (_:Int) + (_:Int)
sum(2, 3)

