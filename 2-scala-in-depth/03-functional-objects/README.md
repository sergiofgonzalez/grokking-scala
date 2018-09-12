# Part 2 &mdash; Scala In Depth: Functional Objects
> Designing objects in a functional way 

--- TBD
  + Introducting Scala's basic types: `Byte`, `Short`, `Int`...
  + Literals syntax for integral types, floats, characters, strings, booleans and symbols
  + Scala's string interpolators: the *s*, *raw string* and *f* string interpolators
  + Operators as regular methods: rules for infix, prefix and postfix operators
  + Arithmetic, relational, bitwise and logical operations in Scala
  + Object equality in Scala
  + Operator precedence and associativity
  + Additional methods on basic types: rich wrapper API
---

## Intro
In this section we will design classes that define *functional* objects, and object that do not have any *mutable* state. We'll delve into more aspects of object-oriented programming in Scala such as class parameters, constructors, methods and operators, private members, overriding, checking preconditions, overloading and self references.

In order to grasp all these concepts we will be creating several variants of a class that models rational numbers as immutable objects.

## Specification for the *Rational* class
A rational number is a number that can be expresses as a ratio *n/d*, where *n* (numerator) and *d* (denominator) are integers, and *d* cannot be zero. 

The class must model the behavior of these rational numbers, including support for addition, subtraction, multiplication and division:
+ To add two rationals, you must first obtain a common denominator, then add the two numerators. Same thing for subtraction. E.g. *1/2 + 2/3 = 3/6 + 4/6 = 7/6*
+ To multiply rationals, you multiply their numerators and denominators. E.g. *1/2 &ast; 2/5 = 2/10 = 1/5*
+ To divide rationals, you swap the numerator and denominator of the right operand and then multiply. E.g. *1/2 / 3/5 = 1/2 * 5/3 = 5/6*

In Maths, rational numbers *do not have a mutable state*: you can add one rational numer to another, but the result will be a new rational number. We'll design the *Rational* class in this way.

We expect the class to support the following usage:
```scala
scala> val oneHalf = new Rational(1, 2)
oneHalf: Rational = 1/2

scala> val twoThirds = new Rational(2, 3)
twoThirds: Rational = 2/3

scala> (oneHalf / 7) + (1 - twoThirds) = new Rational(1, 2)
res0: Rational = 17/42
```

## Constructing a Rational
As the Rational object will immutable, we'll require the clients to provide all the necessary data needed by the instance when they invoke the constrcutor:

```scala
class Rational(n: Int, d: Int)
```

Note that:
+ The class does not have a body, and because of that you can leave off the curly braces.
+ The class specification includes the identifies for *n* and *d*. These are called *class parameters*. Scala compiler will gather up these two *class parameters* and synthesize a *primary constructor* behind the scenes for you.

This highlights a difference between Java and Scala. In Java, classes have constructors that take parameters, whereas in Scala, classes can take parameters directly. You will see that those parameters can be used directly in the body of the class without having to defined them as in Java. Thus, you can do:
```scala
class Rational(n: Int, d: Int) {
  println(s"Created $n/$d")
}
```

| Immutable Object Pros and Cons |
|--------------------------------|
| Immutable objects are often easier to reason about, as there is no complex state that you should be aware of when using it. As a consequence, you can pass immutable objects around quite freely expecting on side-effects. Also, mutable objects are *threadsafe*, so you don't have to worry to synchronize access to mutable objects.<br>However, immutable objects might be the reason behind performance bottlenecks when a large object graph needs to be copied (just imagine a simple cache implementation using an immutable map &mdash; each time you add a new item to the cache, you'll be force to copy all the preexisting elements!). That is why some libraries provide both mutable and immutable versions of classes (e.g. *Map*).

## Reimplementing `toString` method

---
## You know you've mastered this chapter when...

+ You are aware of the different *basic types* that Scala provides and recognize them as full-fledged objects (rather than primitive types as in other programming languages).
+ You know how to define integer, floating point, character, string, boolean and symbol literals. You must be aware of the intrincacies of each literal definitions:
  + decimal and hexadecimal literals for integer types, the *L* (or *l*) suffix to specify *Longs* and how to force the exact type of an integer literal (by declaring its type). 
  + exponent notation for floats and the *F* (or *f*) and *D* (or *d*) to give the specific type.
  + escape sequences and Unicode specification of character literals.
  + raw strings and how to use them.
  + symbol literals and what they try to achieve (where you'd use an identifier in a dynamically typed language).
+ You're aware of the different string interpolators available in Scala, the *s interpolator*, the *raw string interpolator* and the *f string interpolator*.
+ You're aware that operators in Scala are just regular methods that are invoked using a special syntax that feels more natural for certain operations. You must know that methods can be overloaded and that there are different rules for *infix*, *prefix* and *postfix* operators. You're aware that only a bunch of characters can be used as *prefix* operators and must follow a strict naming rule (`unary_<char>`).
+ You're comfortable with arithmetic, relational and logical operations and bitwise operations
+ You're aware of how to check for object equality in Scala using `==` and `!=` and that Scala provides a special operator to check for *reference equality* (`eq` and `ne`).
+ You're aware of the operator precedence and associativity rules.
+ You know about the basic types rich wrappers, which provide additional methods on basic types.
---

## Projects

### [01 &mdash; Basic Types and Operations](./01-basic-types-and-operations-worksheet)
IntelliJ worksheet project with several worksheet illustrating the concepts of the section.

### [02 &mdash; Hello Scala Application](./02-hello-scala-application-sbt)
Simple SBT project that illustrates how to define an application entrypoint in Scala.

### [03 &mdash; Hello App Trait SBT] (./03-hello-app-trait-sbt)
Simple SBT project that illustrates how to define an applicaiton entrypoint in Scala by extending the `App` trait.