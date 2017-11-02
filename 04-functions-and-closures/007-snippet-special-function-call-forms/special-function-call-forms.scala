/*
  Repeated parameters: variable length args
*/
def echo(args: String*) =
  for (arg <- args)
    println(arg)

echo("Hello")
println("==")

echo("Hello", "to", "Jason Isaacs!")
println("==")

val myArgs = new Array[String](3)
myArgs(0) = "Hello"
myArgs(1) = "to"
myArgs(2) = "Idris Elba!"

echo(myArgs) // Err: required String but found Array[String]
println("==")

// the following syntax allows for using the prev function
echo(myArgs: _*) // WTF! _* tells the compiler to pass each element of the array as its own argument
println("==")

/*
  Named Parameters
*/

def speed(distance: Float, time: Float): Float =
  distance / time

speed(100, 10)
speed(time = 10, distance = 100)
println("==")

/* 
  Default Parameter Values 
*/
def printTime(out: java.io.PrintStream = Console.out) =
  out.println(s"time = ${System.currentTimeMillis()}")

printTime()
printTime(Console.err)
println("==")

def printTime(out: java.io.PrintStream = Console.out, divisor: Int = 1) =
  out.println(s"time = ${System.currentTimeMillis() / divisor}")

printTime()
printTime(Console.err)
printTime(divisor = 1000)