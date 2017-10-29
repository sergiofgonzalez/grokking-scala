# 003 &mdash; Iterating through collections with for (snippet)
> using `for` to iterate through collections

## Description
The simplest thing you can do with for is iterate through a collection:

```scala
for (file <- files)
  println(file)
}
```

The syntax `file <- files` is called a generator, and in each iteration a new `val file` is initialized and handed over to the loops body.

This form of for works for any type of collection and even ranges:
```scala
for (num <- 1 to 5)
  println(num)

for (num <- 0 until 5)  // excludes upper bound
  println(num)
```

Iterating with an index (as in `for (int i = 0; i < list.length(); i++)`), can be emulated in Scala doing:
``

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load for-collection-iteration.scala
```