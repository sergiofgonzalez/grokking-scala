# e04 &mdash; Hello Zip (snippet)
> introducing the zip operation in Scala that allows you to iterate over the contents of two arrays element by element

## Description
Scala's zip operation is useful iterate over the elements of two arrays, element by element. 
```
val nums = Array("1", "2", "3", "4", "5")
val chars = Array("a", "b", "c", "d", "e")
nums zip chars Array((1,a), (2,b), (3,c), (4,d), (5,e))
```

Note that applying the `zip` operation on the two arrays, we obtain an aray of tuples containing the nth elements of each array. 

In the example, the merge operation is manually implemented, and then it is reimplemented using `zip`
```scala
for (
  (num, char) <- nums zip chars
) yield num + char  // <- Array(1a, 2b, 3c, 4d, 5e)
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load hello-zip.scala
```