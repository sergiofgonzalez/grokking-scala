# 009 &mdash; Hello, pattern matching (snippet)
> introducing Scala's pattern matching with a simple example

## Description
Pattern matching is a functional programming concept that Scala supports. You can think of it as a switch-case in steroids.

For example, the following function will map a number between 1 and 10 to its ordinal representation, printing an error when the number is greater than 10:

```scala
def ordinal(n: Int) = {
  println(s"Mapping $n to its ordinal representation")
  n match {
    case 1 => println("1st")
    case 2 => println("2nd")
    ...
    case 10 => println("10th")
    case _ => println(s"Error: Invalid argument $n. Cannot go beyond 10")              
  }
}
```

Note that pattern matching is activated using
```scala
 n match {...}
```

and that inside the body, you use case but no `break`. Note also that there is no default clause, but that you can use `case _` to specify the fallback option.


## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load hello-pattern-matching.scala
```