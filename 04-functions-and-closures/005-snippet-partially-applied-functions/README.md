# 005 &mdash; Partially applied functions (snippet)
> partially applied functions and the placeholder syntax

## Description
You can use the `"_"` to substitute not only a given argument to a function, but also an entire parameter list. Thus you can write:

```scala
numbers.foreach(println _) // same as x => println(x)
```

When you use such syntax `println _` you are writing a `partially applied function`. Let's see why:
```scala
def sum(a: Int, b: Int, c: Int) = a + b + c

val a = sum _ // partially applied function
a(1, 2, 3)    // invoke sum with 1, 2, 3

val b = sum(1, _: Int, 3)  // now it is evident why it's called a partially applied function
b(2)                      
```

A *partially applied function* is an expression in which you supply some, or none of the needed arguments. The resulting function can then be stored in a variable (like `a` and `b` in the above example) and then use it to invoke the referred function with the missing parameters that weren't applied.

Note also that you can leave off the underscore when the invoked function is expecting a function as an argument, as it's the case in `foreach`
```scala
numbers.foreach(println)
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load partially-applied-functions.scala
```