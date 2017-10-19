# 004 &mdash; val vs. var (snippet)
> understanding the difference between `val` and `var`

## Description
Illustrates the differences between `val` and `var` when declaring variables (effectively, `val` are constant references and therefore cannot be reassigned)

```scala
val num = 5
num = 6 // <- Error!

var num = 5
num = 6 // OK!
```

The following variable definition related topics are also explored in the snippet:

+ Specifying the type:
```scala
val num: String = "catorce"
```

+ Using the placeholder `_` for variables for which you don't know the value yet:
```scala
val willKnowLater = _
```

+ lazy evaluation of variables
```scala
lazy val forLater = someTimeConsumingOperation() // -> lazy
lazy val num = 5 // -> <lazy>
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load val-vs-var.scala
```