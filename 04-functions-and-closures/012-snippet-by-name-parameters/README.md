# 004 &mdash; By-Name Parameters (snippet)
> a friendlier syntax for custom control abstractions

## Description
By-name parameters allows you a friendlier syntax for custom control abstractions in which you don't have to pass additional parameters other than the function value:
```scala
def myAssert(predicate: => Boolean) =
  if (assertionsEnabled && !predicate)
    throw new AssertionError
```

This allows to call the function as:
```scala
myAssert(5 > 3) 
```

instead of:
```scala
myAssert(() => 5 > 3) 
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load by-name-parameters.scala
```