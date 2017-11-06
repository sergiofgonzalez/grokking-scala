/*
  Creating new control structures is easy
  in languages with first-class functions
*/
def twice(op: Double => Double, x: Double) = op(op(x))
twice(_ + 1.0, 5) // same as twice(elem => elem + 1.0, 5), therefore 7.0
println("==")

/*
  Defining a control pattern to work with file resources
*/
def withPrintWriterV1(file: java.io.File, op: java.io.PrintWriter => Unit) = {
  val writer = new java.io.PrintWriter(file)
  try {
    op(writer)
  } finally {
    writer.close()
  }
}

withPrintWriter(
  new java.io.File("date.txt"), writer => writer.println(new java.util.Date)
)
println("==")

/*
  This can be further improved in terms of syntax as 
  Scala allows you to use { } instead of ( )
*/
println("Hello to Jason Isaacs")
println { "Hello to Idris Elba" }
println("==")

// When using multiple args you cannot use `,` to separate arguments
val message = "Hello to Jason Isaacs!"
message.substring(9, 14)
message.substring { 9, 14 } // Syntax error: this syntax is only allowed for one and only one parameter
println("==")

/*
  Thus, to finally implement `withPrintWriter` as a new control structure we
  have to use currying
*/
def withPrintWriter(file: java.io.File)(op: java.io.PrintWriter => Unit) = {
  val writer = new java.io.PrintWriter(file)
  try {
    op(writer)
  } finally {
    writer.close()
  }
}

val file = new java.io.File("date.txt")
withPrintWriter(file) { writer => 
  writer.println(new java.util.Date)
}
println("==")


/*
  Let's rewrite twice too
*/
def twice(x: Double)(op: Double => Double) = op(op(x))

twice(5) {
  _ * 2 // same as x: Double => x * 2
}
println("==")