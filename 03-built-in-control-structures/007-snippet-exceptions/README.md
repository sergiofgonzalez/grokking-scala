# 007 &mdash; Exceptions in Scala (snippet)
> exceptions and exception handling peculiarities in Scala

## Description
Throwing an exception is Scala follows the same syntax as in Java:
```scala
throw new IllegalArgumentException("Invalid arguments")
```

However, in Scala a `throw` is an expression of result `Nothing` and therefore can be used in wherever an expression is expected:
```scala
val half = if (n % 2 == 0) n / 2 else throw new RuntimeException(s"n must be even but was $n")
```

Catching exceptions in Scala is based on *pattern matching*:
```scala
import java.io.FileReader
import java.io.FileNotFoundException
import java.io.IOException

try {
  val f = new FileReader("input.txt")
} catch {
  case ex: FileNotFoundException => println("The file was not found")
  case ex: IOException => println("The file could not be read")
}
```

In short, the appropriate *case-clause* will be executed depending on the exception that was raised. If the exception does not match any of the *cases* it will be propagated further.

The `finally` clause is also available in Scala:
```scala
import java.io.FileReader

val file = new FileReader("input.txt")
try {
  // ... use the file here ...
} finally {
  // ... no matter what, execute this section ...
  file.close()
}
```

As expected, *try-catch-finally* results in a value:
```scala
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
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load exceptions.scala
```