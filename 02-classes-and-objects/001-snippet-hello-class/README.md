# 001 &mdash; Hello, class (snippet)
> classes, fields, methods; companion and standalone objects

## Description
A class is a blueprint for objects:
```scala
class ChecksumAccumulator {
  // ... class definition here ...
}

new ChecksumAccumulator
```

Inside the class definition you place fields and methods (collectively known as *members*).

To define an instance member you just do:
```scala
class ChecksumAccumulator {
  var sum = 0 // mutable instance var
}
```

When you do that, the value is accessible from outsiders. To prevent it, you can use the `private` modifier:
```scala
class ChecksumAccumulator {
  private var sum = 0 // mutable instance var
}
```

Methods are defined using `def`:

```scala
class ChecksumAccumulator {
  private var sum = 0 // mutable instance var

  def add(b: Byte): Unit = {
    sum += b
  }

  def checksum(): Int = {
    return ~(sum & 0xFF) + 1
  }
}
```

By default, class members are public, and method parameters are always `val` and therefore, can't be reassigned.

The previous code, although valid, does not follow the expected style guide for Scala:
+ the `return` method can be dropped &mdash; in the absences of return, the last value computed by the method is the return value.
+ curly braces are not needed if the method consists of a single result expression

Note also that Scala can infer the return type of the method by looking at the method body, but it's considered a good practice to explicitly keep them for public methods for readability purposes:

Thus, the class can be specified as:
```scala
class ChecksumAccumulator {
  private var sum = 0 // mutable instance var
  def add(b: Byte): Unit = sum += b  // executed for its side effects
  def checksum(): Int = ~(sum & 0xFF) + 1
}
```

A method that is invoked only for its side-effects is known as a *procedure*.

### Semicolon
Semicolons are only required to separate multiple statements on a single line:
```scala
val s = "hello"; println(s)
```

### Singletons
Scala cannot have static members, but have a keyword to create singleton objects.

A singleton object looks like a class definition except that instead of `class` keyword `object` is used.

When a singleton object shares the same name with a class, it is called that *class's companion object*. In that case, both the class and the companion object must be defined in the same source code file. The class is also called the *companion class* of the singleton object.
A class and its companion object can access each other's private members.
```scala
// Companion object for class ChecksumAccumulator
import scala.collection.mutable

object ChecksumAccumulator {
  private val cache = mutable.Map.empty[String, Int]

  def calculate(s: String): Int =
    if (cache.contains(s))
      cache(s)
    else {
      val acc = new ChecksumAccumulator
      for (ch <- s)
        acc.add(ch.toByte)
      val cs = acc.checksum() // checksum for the string
      cache += (s -> cs) // Add the element s -> cs to the cache map
      cs
    }
}

ChecksumAccumulator.calculate("Hello to Jason Isaacs!")  // looks like an static method invocation
```

From a Java standpoint, you can think of a singleton object as the home for any static method your class may need.

A singleton object that does not share the name of any companion class is called a *standalone object*.
```scala
object StandaloneSingleton {
  def sayHello(name: String): Unit = println(s"Hello, $name")
}
```

StandaloneSingleton.sayHello("Idris")

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :paste hello-class.scala
```

**Note**
`:paste` is used instead of `:load` because the *class* and its *companion object* must be defined at once, and the latter interprets the source code line by line.