# 001 &mdash; Hello, Scala's Class Hierarchy (snippet)
> `Any` and its subclasses `AnyVal` and `AnyRef`

## Description
In Scala, every class inherits from a common superclass named `Any`. As a consequence, all of the methods defined in `Any` are available in all the Scala objects.

At the bottom of the hierarchy, Scala defines also two important classes: `Null` and `Nothing` &mdash; just as `Any` is a superclass of every other class, `Nothing` and `Null` are subclasses of every other class.

The following methods are defined in `Any`:
```scala
final def ==(that: Any): Boolean  // comparison
final def !=(that: Any): Boolean  // comparison
def equals(that: Any): Boolean    // overridable comparison
def ##: Int
def hashCode: Int
def toString: String
```

`Any` features two subclasses: 
+ `AnyVal` &mdash; the parent class for value classes like `Byte`, `Short`, `Int`, `Long`, `Float` `Double` and `Unit`.
+ `AnyRef` &mdash; the parent class for all of the *reference* classes.

### `AnyVal`
It's the parent class for Scala's value types. 

Those cannot be instantiated by `new` operator, only using literals. Same happens with `Unit` which is used to identify the return value of methods that do not return any interesting result, and the single instance value is written as `()`.

Implicit conversions are defined between the different value types, so that an `Int` is *automagically* widened into a `Long` when required.

Classes similar to `scala.runtime.RichInt` are defined to enhance the set of available operations available to a value type. For example, `max`, `min`, `abs`... are all defined there. Whenever such a method is invoked on an `Int`, it's implicitly converted into a `RichInt` and the method is applied.

### `AnyRef`
It's the parent class for all the *reference classes* in Scala. `AnyRef` is just an alias for `java.lang.Object`. Thus, all classes written in Scala and Java inherit from `AnyRef`.

Typically, `==` in Scala is implemented to compare contents instead of references:
```scala
val x = "abcd".substring(2)
val y = "abcd".substring(2)
x == y  // <- true: same value
```

And if you want to check for reference equality, `AnyRef` defines the final methods `eq` and `ne` which behaves as Java's `==` and `ne`.
```scala
val x = "abcd".substring(2)
val y = "abcd".substring(2)
x eq y  // <- false: different refs
x ne y  // <- true
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load scala-class-hierarchy.scala
```