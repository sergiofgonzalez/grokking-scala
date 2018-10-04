# Part 2 &mdash; Scala In Depth: Control Abstractions
> Using *function values* to create new control abstractions.

---
+ Reducing Code Duplication using higher-order functions
+ Simplifying client API using higher-order functions
+ Currying
+ Writing new Control Structures with Currying
+ Using *by-name* parameters to create Control Structures with a native feel: caveats
---

## Reducing Code Duplication
Higher-order functions (functions that take functions as parameters) give you the ultimate tool in terms of behavioral flexibility when writing functions. In those function, the non-common part of the logic that the function implement is delegated to the function received as argument, giving you an opportunity to condense and simplify code.

One benefit of such functions is they enable you to create control abstractions that allow you to reduce code duplication.

Consider the following use case in which you want to provide an API that allows a use to search for files matching some criterion.

We would start by adding a facility to search for files whose names end in a particular string (e.g. files ending in `.scala`):

```scala
object FileMatcher {
  private def filesHere = (new java.io.File(".")).listFiles

  def filesEnding(query: String) =
    for {
      file <- filesHere
      if (file.getName.endsWith(query))
    }
      yield file
}
```

Now imagine that we want to add the capability of querying any part of the file name, and not only the ending:

```scala
object FileMatcher {
  private def filesHere = (new java.io.File(".")).listFiles

  def filesEnding(query: String) =
    for {
      file <- filesHere
      if (file.getName.endsWith(query))
    }
      yield file

  def filesContaining(query: String) =
    for {
      file <- filesHere
      if (file.getName.contains(query))
    }
      yield file
}
```

Eventually, you decide to add suport for regular expressions:

```scala
object FileMatcher {
  private def filesHere = (new java.io.File(".")).listFiles

  def filesEnding(query: String) =
    for {
      file <- filesHere
      if (file.getName.endsWith(query))
    }
      yield file

  def filesContaining(query: String) =
    for {
      file <- filesHere
      if (file.getName.contains(query))
    }
      yield file

  def filesRegex(query: String) =
    for {
      file <- filesHere
      if file.getName.matches(query)
    }
      yield file
}
```

Now, when the implementation starts becoming repeatitive (all the functions do the iteration, and apply a predicate to each of the elements retrieved), function values come to the rescue:

```scala
object FileMatcher {
  private def filesHere = (new File(".")).listFiles

  def filesMatching(query: String, matcher: (String, String) => Boolean) =
    for {
      file <- filesHere
      if matcher(file.getName, query)
    } yield file

  /* backward compatibility */
  def filesEnding(query: String) =
    filesMatching(query, _.endsWith(_)) // same as (file, query) => file.endsWith(query)

  def filesContaining(query: String) =
    filesMatching(query, _.contains(_)) // same as (file, query) => file.contains(query)

  def filesRegex(query: String) =
    filesMatching(query, _.matches(_)) // same as (file, query) => file.matches(query)
}
```

The function literals shown in the previous code use the placeholder syntax. That is possible because the function received in `filesMatching` requires two arguments and those are used only once in the body of the function literal. The first *underscore* identifies the first argument (the file) and the second *underscore* the second argument (the query).

The code can be even simplified further because the `query` argument is just passed around and used in the `matcher` invocation. This can be done in the function literal itself:

```scala
object FileMatcher {
  private def filesHere = (new File(".")).listFiles

  def filesMatching(matcher: String => Boolean) =
    for {
      file <- filesHere
      if matcher(file.getName)
    } yield file

  /* backward compatibility */
  def filesEnding(query: String) =
    filesMatching(_.endsWith(query)) // same as (file) => file.endsWith(query)

  def filesContaining(query: String) =
    filesMatching(_.contains(query)) // same as (file) => file.contains(query)

  def filesRegex(query: String) =
    filesMatching(_.matches(query)) // same as (file) => file.matches(query)
}
```

In the previous variations we saw how using higher-order functions lets you refactor common behavior in a very elegant way. Then, in the last step, the use of closures simplified the signature of fileMatching, which no longer required the query to be passed around.

## Simplifying Client Code
Another important use of higher-order functions is to put them in an API itself to make client code more concise, similarly to what Scala does for collection methods `foreach`, `map`, etc.

Consider the following method that determines whether a collection contains a negative integer:
```scala
def containsNeg(nums: List[Int]): Boolean = {
  var exists = false
  for {
    num <- nums
    if (num < 0)
  } exists = true
  exists
}
```

There are a lot of improvement opportunities in the function implementation. For example, you can use the method `exists` which is defined as:

```scala
override def exists(p: A => Boolean): Boolean

// Tests whether a predicate holds for at least one element of this
```

Using that function we get:
```scala
def containsNeg(nums: List[Int]): Boolean = 
  nums.exists(_ < 0) // same as nums.exists(num => num < 0)
```

The method `exists` represents the control abstraction we're looking for &mdash; it lets you pass a function that will be invoked for each of the elements of the collection. This method is part of the *Scala library* rather than the *Scala language* itself.

Defining functions that accept other functions as arguments is extremely flexible. For example, you can use `exists` to find other types of occurrences within the collection, such as whether there are odd numbers: 

```scala
def containsOdd(nums: List[Int]): Boolean = nums.exists(_ % 2 == 1)
```

## Currying
*Currying* is a functional programming technique that allows you to create new control abstractions that feel like *native language support*.

A *curried* function is applied to multiple argument lists instead of just one.
The following example shows a regular, *non-curried* function:
```scala
def plainOldSum(x: Int, y: Int):Int = x + y

plainOldSum(3, 5) // -> 8
```

By contrast, we have the similar *curried function* that instead of one list of two arguments, feature two lists of one parameter:
```scala
def curriedSum(x: Int)(y: Int):Int = x + y

curriedSum(3)(5) // -> 8
```

What happens in the curried function is that you get two traditional function invocations back to back. The first function takes a single `Int` parameter named `x` and returns a *function value* for the second function. This second function takes the `Int` parameter `y`.

Let's decompose in functions what's happening here:
```scala
def first(x: Int) = (y: Int) => x + y

val second = first(3)
second(5) // -> 8
```

Thus, `first(x: Int)` is a function that returns another function that takes `y` and returns the integer resulting from adding `x` and `y`. Then, if we define a variable to hold the *function value* returned from the first invocation, and invoke it with a value `second(5)` we would have illustrated the *currying process* that takes place in `curriedSum(3)(5)`.

Obviously, you can get a reference to the second function using the *partially applied function* syntax as seen on the previous section ([Partially Applied Functions](../05-functions-and-closures/README.md#partially-applied-functions)):

```scala
val onePlus = curriedSum(3) _ // you can also do curriedSum(3)_
onePlus(5) // -> 8
```

## Writing New Control Structures
In languages with *first-class functions*, you can make new controls structures even though the syntax of the language is fixed. Evertyhing is based in using methods that take functions as arguments.

Consider the following example that repeats a given operation twice and return the result:
```scala
def twice(op: Double => Double, x: Double) = op(op(x))

twice(_ + 1, 5) // same as twice((x: Double) => x + 1, 5) -> 7.0
```

The `twice` function is declared as receiving two arguments:
+ a function `op` that takes a `Double` and returns a `Double`
+ a `Double` value `x`

Then, the body of the function consists in applying the function received to the argument passed, and then applying the function once again to the result of the first application, returning the resulting value.

As a rule of thumb, any time you find a control pattern repeated in multiple parts of your code, you should think about implementing it as a new control structure. Consider the following example in which a resource is opened, something is done with it and then it is closed. The logic can be captured in the following method:

```scala
import java.io.File
import java.io.PrintWriter

def withPrintWriter(file: File, op: PrintWriter => Unit) = {
  val writer = new PrintWriter(file)
  try {
    op(writer)
  } finally {
    writer.close()
  }
}
```

Given that method, Scala will let you use it with the following syntax:
```scala
withPrintWriter(
  new File("/tmp/date.txt"),
  writer => writer.println(new java.util.Date())
)
```

This approach is extremely robust, as we are ensuring that the file is closed at the end, while giving the consumer of the method all the flexibility to do whatever is needed with any given file.

| The *loan* pattern |
|--------------------|
| The previous technique is known as the *loan* pattern, as a resource is opened and *loaned* to a function. |


The previous method will look more native if you'd be able to use curly braces instead of parentheses. Scala lets you do that, but only for functions receiving exactly one argument, not two.
For example, you can do:

```scala
println { "Hello to Jason Isaacs!" }
```

The purpose of this ability to substitute curly braces for parentheses for passing one argument is to enable client programmers to write function literals between curly braces and make the call feel more like a built-in control abstraction. In our `withPrintWriter` method we cannot use this feature as it receives two arguments, but we can use *currying* to pull the first argument into a separate argument list, and leave the function as the single parameter for the second argument list:

```scala
def withPrintWriter(file: File)(op: PrintWriter => Unit) = {
  val writer = new PrintWriter(file)
  try {
    op(writer)
  } finally {
    writer.close()
  }
}
```

Thanks to that, we will be able to use it using the following syntax that feels more native:
```scala
withPrintWriter(new File("/tmp/date.txt")) {
  writer => writer.println(new java.util.Date())
}
```

We can work on the deconstruction of the `withPrintWriter` curried function, as we did in the previous section:
```scala
def withPrintWriterFirst(file: File) = (op: PrintWriter => Unit) => {
  val writer = new PrintWriter(file)
  try {
    op(writer)
  } finally {
    writer.close()
  }
}
```
The first function receives a single parameter of type `File` and returns a function value consisting on a function that takes a `PrintWriter` and operates on it. The implementation can be given in this function value, and it is exactly the same as in the non-curried function.

```scala
val withPrintWriterNativeFeelSecond = withPrintWriterNativeFeelFirst(new File("/tmp/date.txt"))
withPrintWriterNativeFeelSecond(_.println(new java.util.Date())) // same as (writer: PrintWriter) => writer.println(new java.util.Date())
```

The second function just gives the definition of the `op` function and passes it to the first one.

## By-Name Parameters
The only difference that you will find in:
```scala
withPrintWriter(new File("/tmp/date.txt")) {
  writer => writer.println(new java.util.Date())
}
```
with respect to an `if` or a `while` is that in the latter there is no value to pass into the code between the curly braces (i.e. the `writer =>`). 

To help with that, Scala provides *by-name* parameters. 

Let's assume you want to create a custom assertion construct called `myAssert`. That will be implemented as a function that will take a *function value* as input and consult a flag to decide what to do. If the flag is set, `myAssert` will invoke the passed function and verify it returns true. If the flas is turned off, `myAssert` will quietly complete without doing anything.

The first implementation, without using *by-name parameters* could be like:

```scala
var assertionsEnabled = true

def myAssert(predicate: () => Boolean) =
  if (assertionsEnabled && !predicate())
    throw new AssertionError


myAssert(() => 5 > 3)
myAssert(() => "hello" == "hola")
```

Then again, although nicely implemented, the control structure does not feel native as you have to use the syntax `() => 5 > 3` to use it.

By-name parameters to the rescue. To make a *by-name parameter* you give the parameter a type starting with `=>` instead of `() =>`. Let's use that idea in `boolAssert`:

```scala
def boolAssert(predicate: Boolean) =
    if (assertionsEnabled && !predicate)
        throw new AssertionError

boolAssert(5 > 3)
```

Now it feels totally native!. However, there is one important difference. Because the type of `boolAssert` parameter is `Boolean`, the expression inside the parentheses is evaluated before the call to the function, while in the previous implementation, the evaluation was deferred until the function was called.

For example, if you call `boolAssert( x / 0 == 0)`, it will fail with an arithmetic exception even before the function has been called.

```scala
assertionsEnabled = false
val x = 5
myAssert(() => x / 0 == 0)  // OK: assertions disabled
boolAssert(x / 0 == 0) // Arithmetic error before calling the function
```

---
## You know you've mastered this chapter when...

+ You understand that a higher-order function is a function that receive another function (or functions) as arguments.
+ You're aware of how you can reduce code duplication using higher-order functions to customize the behavior of a function: both for client APIs and internal implementation.
+ You understand the fundamentals of currying: functions that instead of having a list of arguments have several lists of single arguments.
+ You're aware that currying is a mechanism that allows you to create new control structures thanks to the fact that Scala allows you to use curly braces instead of parentheses when a function has an argument list with a single argument.
+ You understand that you can use *by-name* parameters to create control structures with a more native feel, but understand the caveats: the function value will be evaluated before the actual function backing the control structure is called
---

## Projects

### [01 &mdash; Control Abstraction](./01-control-abstraction-worksheet)
IntelliJ worksheet project with several worksheet illustrating the concepts of the section.
