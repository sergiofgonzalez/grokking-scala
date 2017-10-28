# 001 &mdash; If expressions (snippet)
> if control structure in Scala is an expression

## Description
`if` in Scala is an expression &mdash; it returns a value (as if it were Java's *ternary operator*). As such, you can do:
```
val filename = if (!args.isEmpty) args(0) else "default.txt"
```

This supports the functional style of programming:
+ you're using a `val` for the variable
+ the expression supports *equational reasoning* &mdash; the if expression could be substituted wherever the variable is used

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load rational.scala
```