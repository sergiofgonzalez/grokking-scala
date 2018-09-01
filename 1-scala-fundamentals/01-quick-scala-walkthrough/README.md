# Part 1 &mdash; Scala Fundamentals &mdash; Quick Scala Walkthrough  
> a quick walkthrough of Scala features that have a feel for what Scala is

## Introduction
The name Scala stands for *"Scalable Language"*. Scala is suited for both small scripts and large systems. Scala run on the Standard Java platform and can interact seamlessly with all the Java libraries.

Scala is a blend of object-oriented and functional programming concepts in a statically typed language.

## A malleable language
Scala is a language that allows for succinct syntax. 

Consider the following program:

```scala
var capital = Map("US" -> "Washington", "France" -> "Paris")
capital += ("Japan" -> "Tokyo")
println(capital("France"))
```

The example shows the convenience and flexibility of Scala: the notation is high-level, without semicolons, type annotations, etc.

In this other example, you can see that Scala lets you build new constructs that feel like *built-in* language constructs:

```scala
def factorial(x: BigInt): BigInt =
  if (x == 0) 1 else x * factorial(x - 1)
```

Note that even when `BigInt` is not a *built-in* type, you can use `==` and `*` to operate with that custom type.

The same applies to control structures: for example, *Akka*, the actor-based Scala framework uses the `!` operator to send an asynchronous message to an actor:

```scala
recipient ! msg
```

## Scala is object-oriented and functional
Scala is an object-oriented language in pure form: every value is an object and every operation is a method call. For example, when you type `1 + 2`, you are actually invoking a method named `+` that accepts two `Int` arguments and return an `Int`.

Also, Scala is includes very advanced *OO* concepts such as *traits* (interfaces with implementations and fields) and *mixins* (objects that can be constructed by mixing traits). 

However, Scala is also a full-blown functional language. In a functional language, functions are first-class values, meaning that a function is a value of the same status as an integer or a string, can be passed to functions, returned from functions or store them in variables.

In Scala, functions can be defined inside in functions, and you can define functions without giving them a name. 

Another main idea of functional programming is that operations of a program should map input values to output values rather than change data in place (just as functions in Math). This leads to immutable data structures, another *cornerstone* of functional programming. Therefore, methods should not have any side effects &mdash; they should just communicate with their environment only by taking arguments and returning results. Methods like this are called *referentially transparent*, because any given input, the method call could be replaced by its result without affecting the program's semantics.

Scala encourages the use of immutable data structures and referentially transparent methods, but you can still write imperative methods.

## Scala is more concise and succinct than Java
Scala avoids most of the boilerplate of Java. See for yourself in the following class definition in Java and Scala

```java
class MyClass {
  private int index;
  private String name;

  public MyClass(int index, String name) {
    this.index = index;
    this.name = name;
  }
}
```

In Scala is a one-liner:

```scala
class MyClass(index: Int, name: String)
```

Note that sometimes, this is a drawback, as you have to know that the previous line will produce a class that has two private instance variables, and a public constructor that takes initial values for those values as parameters.

The conciseness drawback also becomes evident in the following piece of code that defines a boolean variable that controls whether a name argument contains at least one uppercase character:

```scala
val nameHasUpperCase = name.exists(_.isUpper)
```

## Scala is statically typed
Scala features a very advanced static type system that classifies variables and expressions according to its type. However, type inference in Scala will let you in most of the cases forget about having to use explicit types at all.

## Projects

### [01 &mdash; Scala Playground](./01-scala-playground)
An empty project used to start the Scala interpreter from *SBT*.

