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


## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load functions-as-args.scala
```