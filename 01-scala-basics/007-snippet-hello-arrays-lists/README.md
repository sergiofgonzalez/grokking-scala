# 007 &mdash; Hello, Arrays and Lists (snippet)
> introducing `Array` and `List`

## Description
Scala features *arrays*, which are mutable structures:
```scala
val array = new Array[String](3)
array(0) = "Hello"
```

and lists, which are immutable:
```scala
val list = List(1, 2, 3)
```

The example illustrates several concepts associated with arrays and lists:
+ Iterating over arrays and lists using `foreach`
+ Using `::` to put elements in front of an existing list
+ Using `:+` to append elements to a list
+ Using `filter` and `filterNot` to remove elements from a list
+ Accessing individual elements of the List by its index using `list(i)`

The example also illustrates how to convert a Scala list into a Java list:
```scala
val scalaList = List(1, 2, 3)
val bJavaList = java.util.Arrays.asList(scalaList.toArray:_*) // <- java.util.List[Int] = [1, 2, 3]
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load hello-arrays-lists.scala
```