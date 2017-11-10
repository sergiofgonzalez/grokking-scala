# 002 &mdash; Hello, Bottom Types (snippet)
> introducing `Null` and `Nothing`

## Description
At the bottom of the type hierarchy Scala defines the two classes `scala.Null` and `scala.Nothing`. Those classes are defined to handle the *corner cases* of Scala's object-oriented type system in a uniform way.

### `Null`
`Null` is the type of the *null reference*, and it is a subclass of every class that inherits from `AnyRef`. `Null` is not compatible with value types (those that inherit from `AnyVal`).

### `Nothing`
`Nothing` is at the very bottom of Scala's class hierarchy, it is a subtype of every other type. However, there are not values for this type, so it is defined for purposes such as signaling abnormal termination (throwing an exception is an expression of type `Nothing`).

```scala
def raiseError(message: String): Nothing = throw new Exception(message)
```

There is an `error` method defined in the `Predef` object of Scala's standard library that is defined as:
```scala
def error(message: String): Nothing = throw new RuntimeException(message)
```

As `Nothing` subtypes every other type, you can use this `error` method in very flexible ways:
```scala
def divide(x: Int, y: Int): Int = 
  if (y != 0) x / y
  else error("cannot divide by zero") // No compilation error as Nothing is a subtype of Int
```


## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load bottom-types.scala
```
