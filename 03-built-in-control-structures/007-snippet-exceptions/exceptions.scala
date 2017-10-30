/*
  throwing an exception is an expression of type `Nothing`
*/
def throwException = throw new IllegalArgumentException("Fabricated exception to check return type")


/*
  As an expression it can be used whenever an expression is expected
*/
val n = 7
val half = if (n % 2 == 0) n / 2 else throw new RuntimeException(s"n must be even but was $n")

/*
  Catching exceptions is based on pattern matching
*/
import java.io.FileReader
import java.io.FileNotFoundException
import java.io.IOException

try {
  val f = new FileReader("input.txt")
} catch {
  case ex: FileNotFoundException => println("The file was not found")
  case ex: IOException => println("The file could not be read")
}

/* Using finally */
import java.io.FileReader

val file = new FileReader("input.txt")
try {
  // ... use the file here ...
} finally {
  // ... no matter what, execute this section ...
  file.close()
}

/* 
  try-catch-finally returns a value 

  try to parse a URL, but use a default value if the URL is badly formed.
*/
import java.net.URL
import java.net.MalformedURLException

def urlFor(path: String) =
  try {
    new URL(path)
  } catch {
    case e: MalformedURLException => new URL("http://www.default-url.org")
  }

urlFor("invalid@url@i suppose") // <- http://www.default-url.org
urlFor("http://google.com")     // <- http://google.com
