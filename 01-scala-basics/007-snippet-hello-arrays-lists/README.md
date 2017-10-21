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

There is also a more succinct way to initialize the arrays:
```scala
val nums = Arrays("one", "two", "three")
```

The example illustrates several concepts associated with arrays and lists:
+ Iterating over arrays and lists using `foreach`
+ Using `::` to put elements in front of an existing list (cons)
+ Using `:+` to append elements to a list (note that this operation is heavy, and therefore this method is rarely used &mdash; it's better to prepend with `::` and then call reverse)
+ Using `:::` to concatenate lists
+ Using `filter` and `filterNot` to remove elements from a list
+ Accessing individual elements of the List by its index using `list(i)`
+ a lot of other useful methods like `filter`, `map`, `foreach`, `head`, `tail`...

**Note**
The expression `1 :: nums` applies the method `::` to the right operand (*nums*). In Scala, if a method is used in *operator notation* is applied to the left-hand element (as in `10 to 20` is `10.to(20)`) except when the method ends in a colon, that is applied to the right-hand element (therefore ``nums.::(1)`)


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