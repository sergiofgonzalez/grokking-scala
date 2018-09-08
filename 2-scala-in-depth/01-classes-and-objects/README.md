# Part 2 &mdash; Scala In Depth: Classes and Objects  
> Foundational concepts on classes and objects 

---
  + Introducing classes, fields and methods
  + The semicolon inference
  + Singleton objects
  + How to write and run a Scala application
---

## Classes, Fields and Objects
A class is a blueprint for objects. Once you define a class, *objects* from the *class blueprint* can be created with the `new` keyword.

```scala
class ChecksumAccumulator {
  // class definition here
}

val checksumAccumulator = new ChecksumAccumulator
```

Inside a class definition, you place *fields* and *methods*, which are collectively called *members*.

Fields which you define with either `val` or `var` are variables that refor to objects. Methods which you define with `def` contain executable code. The fields hold the state of the object, whereas the methods use the data to do the computational work of the object.

When you instantiate a class, the runtime sets aside some memory to hold the image of that object's state:

```scala
class ChecksumAccumulator {
  var sum = 0
}
val acc = new ChecksumAccumulator // memory for acc.sum will be allocated
val csa = new ChecksumAccumulator // memory for csa.sum will be allocated
```

You can reassign a `var` defined inside a class doing:
```scala
acc.sum = 5
```
| Instance Variables |
|--------------------|
| Fields are also known as instance variables, because every instance gets its own set of the variables. |

Once you get an object of a class and place it on a `val` you won't be able to reassign the variable to a different object (as it was declared as a `val`), although you will be able to alter the state of the object it points to:

```scala
acc = new ChecksumAccumulator // Err: acc was a `val`
acc.sum = 7 // Fine, altering the state of sum, which was declared as a `var`
```

To prevent outsiders to change the state of the object, you're encouraged to declare internal fields as *private*:

```scala
class ChecksumAccumulator {
  private var sum = 0
}
```

A private field will be accessible only by the methods defined in the same class.

| There's no public in Scala |
|----------------------------|
| There's no `public` access modifier keyword in Scala. Where you would use `public` in Java, you simply say nothin in Scala (i.e. public is the default access level in Scala).  |


Once the state is declared `private`, we will need to add some methods in the class:

```class
class ChecksumAccumulator {
  private var sum = 0;

  def add(b: Byte): Unit = {
    sum += b
  }

  def checksum(): Int = {
    return ~(sum & 0xFF) + 1
  }
}
```

Method parameters in Scala are *vals* not *vars*, and therefore, any attempt to reassign them will end up in a compilation error.

```scala
def add(b: Byte): Unit = {
  b = 3 // Err: b is a `val`
  sum += b
}
```

Although the `ChecksumAccumulator` definition is correct, it can be more succinctly written in Scala:
+ the `return` at the end of `checksum()` is superfluous and can be dropped &mdash; in absence of any explicit return, a Scala method returns the last value computed by the method
+ You can leave off the curly braces for methods that compute only a single result expression. If the result expression is short, you're encouraged to place it on the same line as the `def` itself.
+ You can also leave off for the result type of methods, and let the Scala type inference engine do the job

Thus, the class will end up being written as:

```scala
class ChecksumAccumulator {
  private var sum = 0
  def add(b: Byte) = sum += b
  def checksum() = ~(sum & 0xFF) + 1
}
```

However, it is considered a good practice to explicitly provide the result type of a class public methods, as it will help the consumers of the class.

Methods with a `Unit` result type (like the `add` method) are executed for their side effects. A method that is executed only for its side effects (mutating some state external to the method or perfoming some I/O action) is known as a *procedure*. 

## The Rules of Semicolon Inference
Scala does not typically require you to end the statements with `;`. These are the rules that allow you to do that:

A line ending is treated as a semicolon unless one of the following conditions are true:
1. the line in question ends in word that would not be legal at the end of the statement, such as a period or an infix operator
2. the next line begins with a word that cannot start a statement
3. the line ends while inside parentheses `(...)` or brackets `[...]`, because these cannot contain multiple statements anyway.

Thus, if you want to enter a statement that spans multiple lines, most of the time you can simply separate them with *enter*:
```scala
if (x < 2)
  println("less than 2")
else
  println("greater than or equal to 2")
```

This might have unexpected consequences on a handful of cases. For instance, you might want to span an `x + y` statement in two lines:

```scala
  x
+ y
```

Scala will parse the previous piece of code as two separate statements. In order to solve it, you can either do:

```scala
x +
y
```

or use parentheses (as the the rules for semicolon inference state that and end of line will not terminate the statement in that case):

```scala
(x
 + y)
```

See [Semicolon Inference Rules](./01-classes-and-objects-worksheet/02-semicolon-inference-rules.sc) for practice example.

## Singletons
Scala does not include the concept of static members in classes. Instead, Scala has *singleton objects*. A singleton object follows the same syntax rules of the class definitions, except that instead of the keyword class the keyword `object` is used.

Singleton objects play a very important role in Scala. In particular, when a singleton object shares the same name with a class, it is called the *class's companion object*. In that case the class is also called the *companion class of the singleton object*.
A class and its companion object must be defined in the same source code file.
A class and its companion object can access each other's private members

```scala
// file: ChecksumCalculator.scala
import scala.collection.mutable

class ChecksumCalculator {
  private var sum = 0
  def add(b: Byte): Unit = sum += b
  def checksum(): Int = ~(sum & 0xFF) + 1
}

object ChecksumCalculator {
  private val cache = mutable.Map.empty[String, Int]

  def calculate(s: String): Int =
    if (cache.contains(s))
      cache(s)
    else {
      val acc = new ChecksumCalculator
      for (c <- s)
        acc.add(c.toByte)
      val cs = acc.checksum()
      cache += (s -> cs)
      cs
    }
}
```

The `ChecksumCalculator` singleton object has one method named `calculate` which takes a String and calculates the checksum for all the characters in the String. It features a cache were previously calculated checksums are stored, so that if a string has already been seen, it will not be recalculated.
If the string has not been previously seen, a new `ChecksumCalculator` instance is created, and we iterate over all the characters of the string adding those to the instance sum using `add()`.
Right after that, the checksum is calculated, added to the cache with key the given string and the checksum is returned.

*Singleton objects* can be easily rationalized as the home for static methods for its companion class. Even the syntax to invoke methods on singleton objects look like an static method call:
``` scala
ChecksumAccumulator.calculate("Hello to Jason Isaacs")
```

However, a singleton object is much more than a simple holder of static objects: It is a first-class object. You can think of it as a *name tag* for an object. In the previous example, this `ChecksumAccumulator` singleton object is an instance with an state (the cache) and a method that operates on that state.

Singleton objects don't define a type, as you can't make instances of singleton objects. However, singleton objects extend a superclass and can mix in traits.

Given each singleton object is an instance of its superclasses and mixed-in traits, you can invoke its methods via these types, refer to it from variables of these types, and pass it to methods expecting these types.

Singleton objects cannot take parameters whereas classes can, as you have no way to pass parameters (you can't instantiate a singleton object with the `new` keyword). A singleton object is initialized the first time some code accesses it.

Singleton objects that does not share the same name with a companion class is called a *standalone object*. They're typically used for collecting utility related methods (e.g. library of Maths related functions) or to define the entry point to a Scala application.

## Scala Application
To run a Scala application, you must supply the name of a *standalone singleton object* with a main method that takes one parameter, an `Array[String]` and has a result type `Unit`.

```scala
// file: Summer.scala
import ChecksumCalculator.calculate

object Summer {
  def main(args: Array[String]) = 
    for (arg <- args)
      println(arg + ": " + calculate(arg))
}
```

The `import` statement makes available the `calculate` method from the previous example, so that you can use the simple name, instead of the fully qualified one.

| Implicit imports |
|------------------|
| Scala implicitly imports the members of the packages `java.lang` and `scala`, as well as the members of a singleton object named `Predef` into every Scala source code file.<br>
`Predef` resides in the `scala` package and contains many useful methods such as `println` and `assert`.
|

Note that Scala does not require you to match the name of the file with the name of the class you put in it, but it is considered a good practice to do that.

See [02 &mdash; Hello Scala Application](./02-hello-scala-application-sbt) for an example you can run and debug.

## The `App` Trait
Scala provides a *trait*, `scala.App`, that simplifies the way in which entrypoints are defined.

```scala
// file: FallWinterSpringSummer.scala
import ChecksumCalculator.calculate

object FallWinterSpringSummer extends App {

  for (season <- List("fall", "winter", "spring", "summer"))
    println(season + ": " + calculate(season))
```

That is, by making your object extends from `App` you will no longer need to define the `main()` method, and instead you will be able to write the body of the application between the curly braces of the singleton object.

| How to access the command line arguments when using the `App` trait             |
|---------------------------------------------------------------------------------|
| You can access the command-line arguments via an array of strings named `args`. |

See [03 &mdash; Hello App Trait SBT] (./03-hello-app-trait-sbt) for an example.

---
## You know you've mastered this chapter when...
+ You know how to define classes, public and private fields and methods for your classes
+ You are aware of the semicolon inference rules that allow you to leave off the semicolon at the end of the statements in most of the cases, and how to prevent *automatic spanning* using parentheses
+ You know about *Singleton objects* and their role as *a class's companion object* when they share the same name and source code file of a class.
+ You know how to define a *standalone singleton object* that serves as an entry point for an Scala application, and how to simplify the approach using the `App` trait
+ You are comfortable with IntelliJ IDE running worksheets for quick prototyping, and running and debugging applications. 
+ You are comfortable starting the Scala console within SBT, and running
---


## Projects

### [01 &mdash; Classes and Objects](./01-classes-and-objects-worksheet)
IntelliJ worksheet project with several worksheet illustrating the concepts of the section.

### [02 &mdash; Hello Scala Application](./02-hello-scala-application-sbt)
Simple SBT project that illustrates how to define an application entrypoint in Scala.

### [03 &mdash; Hello App Trait SBT] (./03-hello-app-trait-sbt)
Simple SBT project that illustrates how to define an applicaiton entrypoint in Scala by extending the `App` trait.