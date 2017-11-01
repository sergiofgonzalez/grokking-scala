# 003 &mdash; Hello, Function Literals (snippet)
> lambdas!

## Description
A function literal is an unnamed literal that can be passed as a value. Scala compiles the function literal into a class that when instantiated at runtime it is converted into a function value.

```scala
(x: Int) => x + 1  // Function literal (aka Lambda)
```

Function literals, as values, can be stored in variables, and then the variable can be used to invoke the function:
```scala
var fn = (x: Int) => {
  println("Hello to Jason Isaacs!")
  x + 1
}

fn(9999)
```

And function literals are really handy when passed as arguments for functions:
```scala
val numbers = List(1, -5, 10, 25, 4)
numbers.foreach((elem: Int) => println(elem))

numbers.filter(elem => elem > 0)
```

Note that the function literal passed to filter leaves off the parameter type &mdash; it is possible to do that because the compilar knows that `elem` must be an integer by looking at the `numbers` definition. Note also that you can leave out the parentheses when the function literal with a single parameter type.

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load function-literals.scala
```