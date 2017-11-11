# 003 &mdash; Defining Value Types (snippet)
> extending classes from `AnyVal`

## Description
Scala allows you to define your own value types that will compile to Java bytecode that will not use a wrapper class, and will get boxed and unboxed automatically.

For a class to be a value class:
+ it must have exactly one parameter
+ it must contain only *defs*
+ a value class cannot redefine `equals` or `hashCode`
+ value classes cannot be extended

The following class models an amount of money in US dollars
```scala
lass Dollars(val amount: Int) extends AnyVal {
  override def toString() = s"$${amount}"
}

val someMoney = new Dollars(100000)
someMoney.amount
```

This convenient way of defining *value types* is great for generating small classes that can help to describe your domain model.

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load defining-value-types.scala
```
