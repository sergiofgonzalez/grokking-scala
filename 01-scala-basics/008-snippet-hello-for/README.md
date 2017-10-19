# 008 &mdash; Hello, for-comprehensions (snippet)
> introducing `for`

## Description
Scala `for` is *dramastically* different from the one you find in Java.

The imperative-for is similar to a for-each:
```scala
for (name <- names) {
  if (name.length > 3) {
    println(name)
  }
}
```

but allows for a syntax that lets you use a guard
```scala
for (
  name <- names;  // <- note the semi-colon
  if (num.length > 5)
) {
    println(num)
    ... // do some other stuff
}
```

In the previous case, the body of the for will be executed only if the guard is true


Additionally, you can use several *generators* in the for, and use curly braces intead of parentheses:
```scala
for { ch <- chars; num <- nums }
  println(s"$ch$num") // <- a1, a2, a3, b1, b2, b3...
```

And on top of that, it supports a functional for, in which you decouple the computation from its use:
```scala
val aList = List(1, 2, 3)
val bList = List(10, 20, 30)

val resultList = for { a <- aList; b <- bList } yield a + b  // List[Int] = List(11, 21, 31, 12, 22, ...)
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load hello-for.scala
```