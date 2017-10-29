# 005 &mdash; Nested Iterations (snippet)
> nested iteration with `for` 

## Description
The example illustrates how to do nested iteration with `for`.

```scala
val chars = List("a", "b", "c")
val nums = List("1", "2", "3")

for (
  ch <- chars;
  num <- nums
  )
  println(s"${ch}${num}") // <- a1, a2, a3, b1, b2 ...
```

You can also add *if-expressions* when using nested iteration:
```scala
def grep(pattern: String) =
  for (
    file <- files
    if (file.getName.endsWith(".scala"));
    line <- fileLines(file)
    if (line.trim.matches(pattern))
  )
    println(s"Match Found: $file => $line")
```

Note that you need to use `;` to separate the filters for each generator.

**Note**
Scala also supports using *curly braces* instead of parentheses to surround the generators and filters:
```scala
def fileLines(file: java.io.File) = scala.io.Source.fromFile(file).getLines.toList
val files = (new java.io.File(".")).listFiles
def grep(pattern: String) {
  for {
    file <- files
    if file.getName.endsWith(".scala")
    line <- fileLines(file)
    trimmed = line.trim
    if trimmed.matches(pattern)
  } println(s"Match Found: $file => $trimmed")
}
```

As a last note, see how it is possible to define *mid-stream variable bindings*, so that you can reuse variables throughout the for body.

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load nested-iteration.scala
```