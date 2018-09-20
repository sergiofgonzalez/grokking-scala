import java.io.FileReader
import java.net.{MalformedURLException, URL}

// throw new IllegalArgumentException

val n = 4
val half =
  if (n % 2 == 0)
    n / 2
  else
    throw new RuntimeException("n must be an even number")

/*
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException

try {
    val f = new FileReader("input.txt")
} catch {
    case ex: FileNotFoundException => println("Handle missing file")
    case ex: IOException => println("Handle I/O related error")
}
*/

// using finally
/*
val file = new FileReader("input.txt")
try {
    // work with the file here
} finally {
    file.close()
}
*/


// yielding a value
def urlFor(path: String) =
    try {
        new URL(path)
    } catch {
        case e: MalformedURLException => new URL("http://www.scala-lang.org")
    }


