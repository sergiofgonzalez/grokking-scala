# 021 &mdash; Equality in Scala (snippet)
> object equality in Scala using `==`

## Description
Object equality in Scala is performed using `==` and `!=`.

It works with basic and complex types, and no exception is thrown even when `null` is involved in any side of the comparison:
```scala
null == List(1, 2, 3)  // false, no exception thrown!
```
**Note**
In Java, `==` is used to check for value equality in primitive types and reference equality of reference types. In Scala, the reference equality can be checked using `eq`.

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load equality.scala
```