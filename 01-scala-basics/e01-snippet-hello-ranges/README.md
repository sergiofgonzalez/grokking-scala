# e01 &mdash; Hello, ranges (snippet)
> ranges in scala `n to m`

## Description
Scala lets you use expressions such as `10 to 100` that returns a collection of integers between the two given (inclusive). This is nothing more than syntactic sugar for `10.to(100)`, but as Scala lets you use methods as *infix operators* and lets drop parentheses and curly braces here and there you end up with very expressive constructions such as `10 to 20` (in other words: in Scala, if a method takes only one parameter you can call it without a dot or parentheses).

The same happens with the `contains` method that checks if the argument is in the given range. Instead of using `10.to(20).contains(15)` you can use the more expressive `10 to 20 contains 15`.

Compare:
```scala
10 to 100 contains 55  // <- true
10 to 100 contains 555 // <- false

10.to(20).contains(15) // <- true
10.to(20).contains(55) // <- false
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load hello-ranges.scala
```