# 002 &mdash; while loops (snippet)
> while and do-while loop peculiarities in Scala

## Description
*while* and *do-while* work exactly as in other programming languages:

```scala
  while (a != 0) {
    val temp = a
    a = b % a
    b = temp
  }
  b
}

var line = ""
do {
  line = readLine()
  println(s"Read: $line")  
} while (line != "")
```

Note that *while* and *do-while* are not expressions, they do not result in an interesting value &mdash; the type of the result is `Unit`. As such, it is often left out of pure functional languages.

**Note**
In Scala (or when trying to achieve functional-style programming in any other language), you should challenge *while* loops in the same way you should always challenge `var`.

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load rational.scala
```