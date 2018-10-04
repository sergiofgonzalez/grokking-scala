

/* twice control structure repeats the operation twice for the given argument and returns the result */
def twice(op: Double => Double, x: Double) = op(op(x))

twice((x: Double) => x + 1, 5)
twice(_ + 1, 5) // same as twice((x: Double) => x + 1, 5) -> 7


/* open a resource, operate with it, then close it */
import java.io.{File, PrintWriter}
def withPrintWriter(file: File, op: PrintWriter => Unit) = {
  val writer = new PrintWriter(file)
  try {
    op(writer)
  } finally {
    writer.close()
  }
}

withPrintWriter(
  new File("/tmp/date.txt"),
  writer => writer.println(new java.util.Date())
)

/* curly braces not allowed as the function takes two arguments */
//withPrintWriter {
//  new File("/tmp/date.txt")
//  ,
//  writer => writer.println(new java.util.Date())
//}

def withPrintWriterNativeFeel(file: File)(op: PrintWriter => Unit) = {
  val writer = new PrintWriter(file)
  try {
    op(writer)
  } finally {
    writer.close()
  }
}

withPrintWriterNativeFeel(new File("/tmp/date.txt")) {
  writer => writer.println(new java.util.Date())
}

// let's decomposed the curried function to grok it
def withPrintWriterNativeFeelFirst(file: File) = (op: PrintWriter => Unit) => {
  val writer = new PrintWriter(file)
  try {
    op(writer)
  } finally {
    writer.close()
  }
}


val withPrintWriterNativeFeelSecond = withPrintWriterNativeFeelFirst(new File("/tmp/date.txt"))
withPrintWriterNativeFeelSecond(_.println(new java.util.Date()))