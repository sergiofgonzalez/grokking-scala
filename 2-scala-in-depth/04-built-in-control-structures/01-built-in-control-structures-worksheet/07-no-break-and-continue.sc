/*
   Translating to Scala this piece of Java code *

    int i = 0
    boolean foundIt = false;
    while (i < args.length) {
      if (args[i].startsWith("-")) {
        i = i + 1;
        continue;
      }
      if (args[i].endsWith(".scala")) {
        foundIt = true;
        break;
      }
      i = i + 1;
    }
*/


//               0        1          2        3            4
val args = List("-arg1", "someArg", "-arg2", "app.scala", "-arg3")

var i = 0
var foundIt = false

while (i < args.length && !foundIt) {
  if (!args(i).startsWith("-")) {
    if (args(i).endsWith(".scala"))
      foundIt = true
  }
  i = i + 1
}
println(s"final state: i=$i; foundIt=$foundIt")

// in a more functional way: no loops, no vars
def searchFrom(i: Int): Int =
  if (i >= args.length) -1
  else if (args(i).startsWith("-")) searchFrom(i + 1)
  else if (args(i).endsWith(".scala")) i
  else searchFrom(i + 1)

val finalIndex = searchFrom(0)

println(s"final state: i=$finalIndex")

// using `Breaks` class from `scala.util.control`
import scala.util.control.Breaks._
import java.io._

val input = "this is one line\n" +
            "this is another line\n" +
            "this is the third line\n" +
            "\n" +
            "This won't be read at all\n"

val in = new BufferedReader(new StringReader(input))

breakable {
  while (true) {
    println("? ")
    if (in.readLine() == "") break
  }
}
println("done!")