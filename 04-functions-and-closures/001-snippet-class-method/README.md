# 001 &mdash; Class Methods (snippet)
> defining methods in a class to organize logic

## Description
Functions can be defined as members of classes and object. In the example, we define an object `LongLines` which exposes a function `processLine` that prints out the lines of a file that are longer than an established width. The object also defines a private function that is not exposed outside of the class scope.

In order to use the class function you just have to use:
```scala
LongLines.processFile(<file-to-process>, <width>)
```

For example:
```scala
LongLines.processFile("./class-method.scala", 50)
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load class-method.scala
```