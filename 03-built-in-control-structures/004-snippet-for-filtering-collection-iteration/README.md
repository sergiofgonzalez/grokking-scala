# 004 &mdash; Filtering while Iterating through collections with for (snippet)
> filtering with `for` while iterating through collections

## Description
The example illustrates how to perform filtering with `for`.

The traditional way you find in other programming languages is available here:
```scala
val files = (new java.io.File(".")).listFiles

for (file <- files)
  if (file.getName.endsWith(".scala"))
    println(file)
printSeparator()
```

However, you can also use additional techniques such as applying the filter in the *for-expression*:
```scala
val files = (new java.io.File(".")).listFiles

for (file <- files if file.getName.endsWith(".scala"))
  println(file)
```

that also supports multiple conditions:
```scala
val files = (new java.io.File(".")).listFiles

for (
  file <- files
  if file.isFile
  if file.getName.endsWith(".scala")
  )
  println(file)
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load for-filtering-collection-iteration.scala
```