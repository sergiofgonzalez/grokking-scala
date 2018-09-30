# Part 2 &mdash; Scala In Depth: Control Abstractions
> Using *function values* to create new control abstractions.

--- TBD
+ Scala's methods: private and public methods
+ Local functions (functions nested within other functions/methods)
+ The concept of *first-class functions*
+ Scala's placeholder syntax for function literals
+ Partially applied functions
+ Closures
+ Special Function Call Forms: repeated parameters, named arguments and default arguments
+ Tail-Recursion in Scala: limitations
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


---
## You know you've mastered this chapter when...

+ You're comfortable using methods on objects and classes, as it follows the same principles as in other object oriented languages.
+ You're comfortable nesting functions within other functions and methods (*local functions*) and understand how this mechanism can be used as a hiding mechanism for hiding *private* implementation details.
+ You understand the concept of *first-class functions* in Scala: functions can be defined as literals, assign to variables and passed around as parameters.
+ You're aware of the benefits of using functions as arguments to customize the behavior of a function in a flexible way.
+ You're comfortable with the *placeholder syntax* in Scala that allows you to write really concise function literals as long as each parameter is used only once in the function body.
+ You're aware of the mechanisms to build *partially-applied* functions, that allows you fix none or some of the arguments of a function and assign it to another function value.
+ You're comfortable writing closures in Scala, and understand that closures maintain references to the free variables at the time of creation.
+ You're comfortable reading and writing special function call forms like repeated parameters, named arguments and default parameters.
+ You're aware that Scala implements tail-recursion optimizations which optimizes the implementation of recursive calls when the last action of a recursive function is a call to itself.
---

## Projects

### [01 &mdash; Control Abstraction](./01-control-abstraction-worksheet)
IntelliJ worksheet project with several worksheet illustrating the concepts of the section.
