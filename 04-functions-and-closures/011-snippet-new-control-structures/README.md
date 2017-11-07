# 003 &mdash; Writing New Control Structures (snippet)
> Using currying and curly braces to implement control structures that feel native

## Description
In languages with first-class functions, you can make new control structures (that feel like custom additions to the language).

It all starts with passing function values to functions:
```scala
def twice(op: Double => Double, x: Double) = op(op(x))
```

You can use the previous definition, to increment a given double by 2.0 just by invoking:
```scala
twice(_ + 1.0, 5) // <- 7
```

This can be further improved with a more comprehensive example, that controls a *File resource*:
```scala
def withPrintWriter(file: java.io.File, op: java.io.PrintWriter => Unit) = {
  val writer = new java.io.PrintWriter(file)
  try {
    op(writer)
  } finally {
    writer.close()
  }
}
```

Then, you can write a piece of code that ensures that the *file resource* is properly opened and closed:
```scala
withPrintWriter(
  new java.io.File("date.txt"), writer => writer.println(new java.util.Date)
)
```

But you can even push this further with a couple of Scala features:
+ Scala lets you use `{ }` instead of parentheses when invoking a function with a single argument
+ Scala supports *currying*

Therefore, you can have:
```scala
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
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load new-control-structures.scala
```