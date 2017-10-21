# 013 &mdash; Hello, more Maps and Sets (snippet)
> using `Set` and `Map`

## Description
Scala differentiate between mutable and immutable collections. Scala arrays are always mutable while lists are always immutable; Scala also provides mutable and immutable sets and maps, using the same names, and modeling the mutability in the class hierarchy.

Scala does that using *traits*, which are like Java interfaces, but instead of implementing those, in Scala you *mix in traits*.

Effectively, this means that you can have mutable or immutable hash sets.

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

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load sets-maps.scala
```