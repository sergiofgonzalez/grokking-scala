# 004 &mdash; Placeholder Syntax in Function Literals (snippet)
> placeholders in function literals

## Description
Scala syntax allows you to use `"_"` as a placeholder for one or more parameters, so long as each parameter appears only once within the function literal:

```scala
val numbers = List(1, -7, 5, -3, 10)
numbers.filter(_ > 0) // same as elem => elem > 0
```

Sometimes, Scala compiler will ask for the types of the placeholder parameters when those cannot be inferred:
```scala
val sumFn = (_: Int) + (_: Int)  // same as (x: Int, y: Int) => x + y
sumFn(5, 5)
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load function-literals-placeholders.scala
```