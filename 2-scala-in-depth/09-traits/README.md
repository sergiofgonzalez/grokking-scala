# Part 2 &mdash; Scala In Depth: Traits
> Scala's trait concept.

---
+ Basic Notions on Traits
+ Thin vs. Rich Interfaces in Scala
---

## Intro
Traits are a fundamental unit of code reuse in Scala. A trait encapsulates method and field definitions, which can then be reused by mixing them into classes. Unlike class inheritance, in which a class must inherit from just one superclass, a class can mix any number of traits.

A trait is typically useful to widening thing interfaces to rich ones, and defining stackable modifications.

## How Traits work
A trait definition looks just like a class definitions, except that it uses the keyword `trait`:

```scala
trait Philosophical {
  def philosophize() = println("I consume memory, therefore I am")
}
```

This *trait* does not declare a superclass, and therefore it has the default superclass `AnyRef`. It defines a single concrete method.

Once a *trait* is defined, it can be mixed in into a class with the words `extends` or `with`:

```scala
class Frog extends Philosophical {
  override def toString = "green"
}
```

When you extend from a *trait* you implicitly inherit the trait's superclass. Methods inherited from a trait can be used like methods inherited from a superclass:

```scala
val frog = new Frog
frog.philosophize()
```

A trait also defines a type:

```scala
val phil: Philoshophical = frog
phil.philosophize()
```

The type of `phil` is `Philosophical`, a trait. Thus, variable `phil` could have been initialized with any object whose class mixes in `Philosophical`.

If you wish to mix a trait into a class that explicitly extends a superclass, you use extends to indicate the superclass and with to mix in the trait. If you want to mix in multiple traits, you add more with clauses.

For example:

```scala
class Animal
trait HasLegs

class Turtle extends Animal with Philoshophical with HasLegs {
  override def toString = "green shell"
}
```

A class with a mixed-in trait can override the methods inherited from a *trait*:

```scala
class Tortoise extends Animal with Philoshophical {
  override def toString: String = "green shell"
  override def philosophize(): Unit = println(s"It ain't easy having a ${toString}!")
}

val t = new Tortoise
t.philosophize()
```

*Traits*, beside featuring concrete methods, can also declare fields and maintain state. They are more akin to classes than they are to *Java interfaces*. 
However they are different from classes in the following points:
+ a *trait* cannot have any *class parameters* as in `class Point(x: Int, y: Int)`
+ calls to `super` in *traits* are dynamically bound (in classes those are statically bound). This happens because the actual method that will be called will be decided once the trait has been *mixed in* into a concrete class. This fact about `super` in *traits* is key to allowing traits to work as *stackable modifications*, as we will see.

## Thin vs. Rich Interfaces
One major use of traits is to automatically add methods to a class. That is, *traits* can enrich a *thin interface*, making it into a *rich interface*.

A *rich interface* has many methods, which is conveniento for the caller. A *thin interface, on the other hand, has fewer methods, and thus is easier on the implementers.

Adding a concrete method to a *trait* tilts the thin-rich trade-off heavily towards rich interfaces. Adding a concrete method to a trait is a one-time effort, instead of having to implement the method each time the interface is inherited as it happens with *Java interfaces*.

To enrich an interface using traits, simply define a trait with a small number of abstract methods (the thin part of the trait's interface) and add a large number of concrete methods, all implemented in terms of the abstract methods. Then, you can mix the enrichment trait into a class, implement the thin portion of the interface, and end up with a class that has all of the rich interface available.

---
## You know you've mastered this chapter when...

+ You understand the basics of *traits*: a construct that encapsulates methods and fields and that can be mixed into concrete classes with the keyword `extends` and `with`. You understand that *traits* are more akin to classes than *Java interfaces* except for a couple of restrictions (class parameters, and the dynamic binding of `super` in traits). You understand that methods in a trait can be overriden just like methods in regular classes you inherit.
+ You're aware that traits tilt the thin vs. rich interface discussion to rich interfaces. The trick is to define traits with a small number of abstract methods (the thin portion of the interface), and then define a large number of concrete methods based on the abstract methods (the rich portion of the interfaces). Thus, implementers should only write the definition of the small number of abstract methods and clients will be able to use the large number of concrete methods available.
---

## Projects

### [01 &mdash;Traits](./01-traits-worksheet)
IntelliJ worksheet project with several worksheet illustrating the concepts of the section.

### [02 &mdash; Tiny Types HTML SBT app](./02-tiny-types-html-app-sbt)
SBT project that illustrates how conducive Scala is to the creation of tiny types.