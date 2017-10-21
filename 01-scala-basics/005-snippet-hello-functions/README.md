# 005 &mdash; Hello, Functions (snippet) (snippet)
> exploring the syntax for defining (and invoking) functions in Scala

## Description

Function definition syntax in scala is as follows:
```scala
def fn(param1: Type1, param2: Type2, ...): FunctionReturnType = {
  // function body here
}
```

However, most of the times (except in recursive functions) you can leave out the function return type, and if the function body is just one statement you can leave out the curly brace too, so that a minimalist definition may end un like:
```scala
def fn(param1: Type1, param2: Type2, ...) = function_body_one_line
```

A function the returns nothing is said to return a `Unit`. A function return a `Unit` is invoked only for its side effects, such as printing a message in the *stdout*:
```scala
> def printGreeting() = println("Hello to Jason Isaacs")
printGreeting: ()Unit
```

The examples Illustrate how to define and invoke Scala functions by example:
+ Named functions that accept arguments and return typed values
+ Omitting the return type
+ Omitting the parentheses for the arguments when no args are expected
+ Functions accepting generic types
+ Passing functions as arguments to other functions


## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load hello-functions.scala
```