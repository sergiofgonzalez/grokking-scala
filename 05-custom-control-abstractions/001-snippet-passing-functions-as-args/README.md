# 001 &mdash; Passing Function Values as Arguments (snippet)
> illustrating how to create functions that receives functions as arguments

## Description
Illustrates how to create functions and methods that receive function values as arguments. This can be used to reduce code duplication, write more succinct code and also to customize behavior:

In the example, we define a standalone function that receives regular parameter `query` and a `matcher` parameter which happens to be a function that takes two strings and return a boolean value:

```scala
def filesMatching(query: String, matcher: (String, String) => Boolean) =
  for (
    file <- filesHere;
    if matcher(file.getName, query)
  ) yield file
```

therefore, you can call filesMatching as:
```scala
filesMatching(".scala", _.endsWith(_))
```

which is the same as:
```scala
filesMatching(".scala", (filename: String, query: String) => filename.endsWith(query))
```

Passing functions as arguments is extensively used for Scala's API as well, for example in the `exists` function:
```scala
def exists(pred: (A) => Boolean): Boolean
```

Then, you can use it in a very flexible way changing the behavior of the function:
```scala
List(1, 2, 3, -5, 4).exists(_ < 0 ) // check negative
List(2, 3, 6).exists(_ % 2 != 0)    // check for odd numbers
```


## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load functions-as-args.scala
```