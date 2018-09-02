# Part 1 &mdash; Scala Fundamentals &mdash; First Steps in Scala  
> TBD a quick walkthrough of Scala features that have a feel for what Scala is

## Introduction
This section will introduce Scala quickly will introduce Scala concepts to enable you to start reading and writing programs in Scala. Following sections will tackle all these concepts in a more detailed way.

## Variable Definition
Scala has two kind of variables:
+ `val` &mdash; a variable that once initialized it can never be reassigned throughout its lifetime
+ `var` &mdash; a variable that can be reassigned

The syntax is straightforward:
```scala
scala> val msg = "Hello to Jason Isaacs!"
msg: String = Hello to Jason Isaacs!
```

Note that Scala's type inference mechanism allows you to define the variable without specifying its type, which is correctly identified by the interpreter as `String`.

You can however, specify the variable type using the syntax:
```scala
scala> val msg: String = "Hello to Jeremy Irons"
msg: String = Hello to Jeremy Irons
```

Once defined, you can use the variable name in the expected way:
```scala
scala> println(msg)
Hello to Jason Isaacs
```

| Statements spanning multiple lines |
|------------------------------------|
| You can type statements into the interpreter that spans across multiple lines. The interpreter will respond with a `|` to indicate that you can continue typing:

```
scala> val multiline =
     | "This is the next line"
multiline: String = This is the next line
```

You can escapa from the multiline statement by pressing enter twice:

```scala
scala> val oops =
     |
     |
You typed two blank lines.  Starting a new command.
```
|

## Function definition
Function definitions in Scala use this syntax:

```scala
def fn(param1: Type1, param2: type2, ...): FunctionResultType = {
  // function body here
}
```

Thus:
+ a function definition start with `def`.
+ the function name is followed by a comma-separated list of parameters in parentheses. If the function receives no arguments, you can use `()` to indicate it.
+ function parameter types are not inferred, and therefore should be explicitly given using the syntax `variableName: Type`.
+ after the parameter list, you can give the function result type. This is often optional as Scala will be able to infer it.
+ then, an `=` sign and a pair of curly braces indicate the body function follows.


For example:

```scala
def max(x: Int, y: Int): Int = {
  if (x > y) x
  else y
}
```

Note that no `return` is needed in this case.


The previous syntax can be greatly simplified in most of the cases:
+ the result type of the function will be inferred by Scala in most of the cases
+ if a function consists of a single statement, you can leave off the curly braces

```scala
def max(x: Int, y: Int) = if (x > y) x else y
```

Once defined, a function can be called by its name:

```scala
scala> max(2, 5)
res1: Int = 5
```

A function that returns nothing is said to return a `Unit`. A function returning a `Unit` is invoked to trigger side effects, such as printing a message in the *stdout*:

```scala
scala> def printGreeting() = println("Hello to Jason Isaacs")
printGreeting: ()Unit
```

## Projects

### [01 &mdash; Scala Playground](./01-scala-playground)
An empty project used to start the Scala interpreter from *SBT*.

