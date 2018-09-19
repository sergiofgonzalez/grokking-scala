def gcdLoop(x: Long, y: Long): Long = {
  var a = x
  var b = y
  while (a != 0) {
    val temp = a
    a = b % a
    b = temp
  }
  b
}

gcdLoop(63, 36)
gcdLoop(37, 3)

// better functional approach
def gcd(x: Long, y: Long): Long = {
  if (y == 0) x else gcd(y, x % y)
}
gcd(63, 36)


/*
// do-while
import scala.io.StdIn

var line = ""
do {
  line = StdIn.readLine()
  println(s"Read: $line")
} while (line != "")
*/

