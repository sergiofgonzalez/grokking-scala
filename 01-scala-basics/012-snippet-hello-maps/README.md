# 012 &mdash; Hello, Maps (snippet)
> using Map

## Description
A `Map` is an immutable collection of keys and values.
The elements of the maps are *tuples*, which Scala allows you to define as 
```scala
val tuple2 = (key, value)
```

which is syntactic sugar for 
```scala
val tuple2 = new scala.Tuple2(key, value)
```

Then, the `Map` can be created as:
```scala
val map = Map(("key1", "value1"), ("key2", "value2"))
```

and individual values can be accessed like `map("key")`.


## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load hello-maps.scala
```