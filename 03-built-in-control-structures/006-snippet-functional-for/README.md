# 006 &mdash; Functional for (snippet)
> yielding values from for

## Description
A *functional for* is a for that instead of perfoming actions returns a collection.

The syntax is as follows:
```scala
for clauses yield body
```

For example:
```scala
val files = (new java.io.File(".")).listFiles
for {
  file <- files
  if file.getName.endsWith(".scala")
} yield file
```

Note that the `yield` must be included before the body:
```scala
for (file <- files) {
  yield file.getName  // Syntax error: yield not used before the body
} 

for (file <- files) yield {
  file.getName // OK: yield found before the body
}
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load functional-for.scala
```