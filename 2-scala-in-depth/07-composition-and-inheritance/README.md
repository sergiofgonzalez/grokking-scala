# Part 2 &mdash; Scala In Depth: Composition and Inheritance
> Scala's object-oriented features in depth.

---
+ Abstract classes and abstract methods
---

## Intro
In this section we'll deep dive into the object-oriented aspects of Scala, and in particular composition and inheritance. Composition means that one class holds a reference to another class that is used to help adress its mission. Inheritance is the superclass/subclass relationship.

## Presenting the Use-case: a 2D layout library
As a running example to demonstrate the features, we'll create a library for building and rendering two-dimensional layout elements. Each element will represent a rectangle filled with text. For convenience, the library will provide *factory methods* named `elem` that constructs new elements from passed data.
For example, you'll be able to create a layout element containing a string using a factory method with the following signature:
```scala
elem(s: String): Element
```

Thus, elements will be modeled with a type named `Element`. You'll be able to call `above` or `beside` on an element, passing in a second *element*, to get a new *element* that combines the two.

For example, the following expression would effectively result in a *2x2 table*, built from the specification of its columns:
```scala
var column1 = elem("hello") above elem("xxx")
var column2 = elem("yyy") above elem("world")
column1 beside column2
```

That will print:
```
hello yyy
xxx world
```

We'll define the classes that enable *element objects* to be constructed from arrays, lines and rectangles, that will constitute the basic parts. Those simple parts will be used to be build larger parts with the aid of *composing operators* (also known as *combinators* as they combine existing elements to create new ones).

When dealing with such a system, it's a good practice to apprach the task with answers to the the following questions:
+ Think in terms of *combinators* &mdash; what will be the fundamental ways to construct objects in the application domain?
+ What are the simple parts?
+ How more interesting objects can be built from those simpler ones?
+ How will combinators interact together?
+ What are the most general combinations?
+ Do they satisfy any interesting laws?

## Abstract Classes
The first task is to define the type `Element` which will model the layout elements. Since elements are two dimensional rectangles of characters, it makes sense to include a member `contents` that refers to the cotents of a layout element.
We will represent the contents as an array of strings, where each string in the array will represent a line (i.e. a row).

```scala
abstract class Element {
  def contents: Array[String]
}
```

Note that:
+ `contents` is declared as a method with no implementation &mdash; this is also called an abstract member.
+ the class is declared as `abstract`.

In Scala, a class with *abstract* members must itself be declared as `abstract`. This modifier means the class may have abstract members that do not have implementation. As a result, an abstract class cannot be instantiated:

```scala
new Element // -> Err! class Element is abstract; cannot be instantiated
```

What's the purpose of an abstract class then? They're created as templates that can be be used in *subclasses* of the abstract class and that will fill in the missing definitions for the *abstract* methods.

Note also that in Scala, an abstract method does not need a modifier &mdash; if a method does not have an implementation is automatically abstract.

Another important concepts are declarations and definitions: the `Element` class declares the abstract method `contents`, but defines no concrete methods.

---
## You know you've mastered this chapter when...

+ You understand how to define *abstract classes* (classes with the `abstract` modifier) and *abstract methods* (methods with no implementations) in Scala. You're aware that abstract classes cannot be directly instantiated, but instead will be subclassed and their *abstract methods* will be filled in with implementations.

---

## Projects

### [01 &mdash; Control Abstraction](./01-composition-and-inheritance-worksheet)
IntelliJ worksheet project with several worksheet illustrating the concepts of the section.
