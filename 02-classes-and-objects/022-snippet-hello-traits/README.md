# 022 &mdash; Hello, Traits (snippet)
> the concept of trait and how to mix in traits into classes

## Description
*Traits* are a fundamental unit of code reuse in Scala. A trait encapsulates method and field definitions, which can then be reused by mixing them into classes.

Unlike class inheritance, a class can *mix in* any number of traits.

A trait definition looks like a class definition:
```scala
trait philosophical {
  def philosophize() = {
    println("I consume memory, therefore i am")
  }
}
```

A *trait* like this one, that does not declare a superclass, extends `AnyRef` by default. 
Once a trait has been been defined, it can be *mixed in* into with either the `extends` or `with`.

```scala
class Frog extends Philosophical {
  override def toString = "green"
}
```

Methods inherited from a *trait* can be used just like methods inherited from a superclass.

```scala
val frog = new Frog
frog.philosophize()
```

And *traits* also define a type:
```scala
val kermit: Philosophical = new Frog
```

Typically, a class that inherits from a superclass and also mixes in a trait is declared as:
```scala
class Frog extends Animal with Philosophical {
  override def toString = "green animal"
}
```

And if it mixes in several traits the syntax would be:
```scala
class Frog extends Animal with Philosophical with HasLegs {
  override def toString = "green animal with legs"
}
```

A class that *mixes in* a trait, can override the method implementation from a trait:
```scala
class Frog extends Animal with Philosophical {
  override def toString = "green animal"
  override def philosophize() = println("It ain't easy being green")
}

val kermit = new Frog
kermit.philosophize()
```

Note that *traits* are not like Java interfaces, but instead, they are more like classes:
+ traits can define method implementations
+ traits can declare fields and maintain state

However, they have some differences with classes:
+ They cannot have *class parameters* (the parameters passes to the primary constructor of a class).
```scala
trait Point(x: Int, y: Int) // Err:
```

Another difference is that calls using the `super` keyword are dynamically bound, because it won't be known until the trait is mixed in into a concrete class.

### Thin vs. Rich Interfaces
The fact that traits allow you to implement methods make a very good case for having *rich interfaces* (that is, interfaces that provide a huge number of methods to the clients). In Java, having *thin interfaces* (that is, interfaces with a small number of methods) are preferred over *rich interfaces* because having a *rich interface* forces anyone using the interface to implement all of its methods.

However, in Scala, you can have rich interfaces that implement a huge number of methods and that only defines a small number of abstract methods.

### Some tips about when to use Traits and when to use an Abstract Class
There is no firm rule, but a few guidelines can be given:
+ If the behavior will not be reused &mdash; define an abstract class
+ If the behavior might be reused in multiple, unrelated classes &mdash; use a trait
+ If you want to inherit from it in Java &mdash; use an abstract class. As there is no analog to *traits* in Java, it tends to be awkward to inherit from a trait in Java. However, extending from a Scala class from Java is straight-forward (as an exception, a trait with only abstract members translates directly into a Java interface).
+ If you're going to distribute the behavior in compiled code &mdash; use an abstract class. It will cause less recompilation.

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:

```
scala> :load hello-traits.scala
```