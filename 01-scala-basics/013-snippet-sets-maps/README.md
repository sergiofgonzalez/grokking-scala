# 013 &mdash; Hello, more Maps and Sets (snippet)
> using `Set` and `Map`

## Description
Scala differentiate between mutable and immutable collections. Scala arrays are always mutable while lists are always immutable; Scala also provides mutable and immutable sets and maps, using the same names, and modeling the mutability in the class hierarchy.

Scala does that using *traits*, which are like Java interfaces, but instead of implementing those, in Scala you *mix in traits*.

Effectively, this means that you can have mutable or immutable sets and maps.

### Sets
To create an immutable set you use:
```scala
var jetSet = Set("Boeing", "Airbus")
jetSet += "Lear"
jetSet.contains("Cessna")
```

For immutable sets you need an import:
```scala
import scala.collection.mutable

val actorSet = mutable.Set("Jason", "Idris")
actorSet += "Riz"
```

You can force a particular implementation using an import:
```scala
import scala.collection.immutable.HashSet

val ahashSet = HashSet("Tomatoes", "Chillies") // Set(Tomatoes, Chillies)
val b = ahashSet + "Parsley" // Set(Parsley, Tomatoes, Chillies)
```

### Maps

You can have implementations of Map (both mutable and immutable) and initialize them using factory methods similar to the ones available for arrays, lists and sets.

For an immutable map you'd use:
```scala
import scala.collection.mutable

val treasureMap = mutable.Map[Int, String]()
treasureMap += (1 -> "Go to island")
treasureMap += (2 -> "Find big X on the ground")
treasureMap += (3 -> "Dig until find treasure chest")

treasureMap(2)
```

The default implementation is the immutable one, for which you don't need any import:

```scala
val romanNumerals = Map(1 -> "I", 2 -> "II", 3-> "III", 4 -> "IV", 5 -> "V")
romanNumerals(4)
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load sets-maps.scala
```