
/* first we define a breakException variable */
val breakException = new RuntimeException("break exception")

/* Then we define the break function that will be used as a keyword */
def break = throw breakException

/*
  Now we define a function `breakable`:
    + it accepts a function that returns nothing (`Unit`)
    + it will execute the function in a try-catch block
    + if an exception is found ?
*/

def breakable(op: => Unit) {
  try {
    op
  } catch { case _ : Throwable => }
}


/*
  Now let's put everything to work with an example 
*/
def installLogic = {
  val environmentVar = "JAVA_HOME"
  val env = System.getenv(environmentVar)
  if (env == null) {
    println(s"$environmentVar not found: exiting")
    break
  }
  println(s"$environmentVar was found")
}

breakable(installLogic)

/*
  but extensibility is better seen using inline definitions and curly braces
*/

breakable {
  val environmentVar = "SCALA_HOME"
  val env = System.getenv(environmentVar)
  if (env == null) {
    println(s"$environmentVar not found: exiting")
    break
  }
  println(s"$environmentVar was found")
}