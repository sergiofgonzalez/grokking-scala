# Part 2 &mdash; Scala In Depth: Built-in Control Structures
> Introducing Scala's built-in control structures.

---
  + Introducing Scala's `if` expression
  + The *while loop*: the `Unit` type and the *unit value* `()`
  + *For expressions*: generators. Filtering and mapping with *for expressions*. Nested iteration.
  + Exception handling in Scala: throwing and catching exceptions. The `finally` clause.
  + Introducing Scala's *match* expression mechanism
  + Alternatives to *break* and *continue*
  + Variable scope
  + Tips for refactoring imperative code
---

## Intro
Scala has a minimalist set of built-in control structures: *if*, *while*, *for*, *try*, *match* and *function calls*. The reason behind it is that Scala allows you to build your own and place them in libraries.

Additionally, being a functional language, you will notice that most of the control structures result in some value. Programmers can use these result values to simplify their code, just as they use return values from functions.

## *if* Expressions
Scala's `if` tests a condition and then executes one of two code branches depending on whether the condition holds true:

```scala
var filename = "default.txt"
if (!args.isEmpty)
  filename = args(0)
```
The previous piece of code declares a variable, initializes it to a default value, and then checks whether any arguments were received in the command line. If so, it changes the variable to hold the value specified in the argument list.


The previous imperative approach can be written in a more functional approach, as Scala's if *returns a value*:
```scala
val filename =
  if (!args.isEmpty) args(0)
  else "default.txt"
```

Note that it is not only more succinct, it also gets rid of the mutable variable.

In short, note that the `if` expression is more akin to the *ternary operator* present in other languages, than the traditional *imperative* if:

```scala
println(if (numItems == 1) s"$numItems result found" else s"$numItems results found")
```

## *while* loops
The while loop has a condition and a body, and the body is executed over and over as long as the condition holds true:

```scala
def gcdLoop(x: Long, y: Long): Long = {
  var a = x
  var b = y
  while (a != 0) {
    val temp = a
    a = b % a
    b = temp
  }
  b
}

gdcLoop(36, 63)
res0: Long = 9
```

There's also a *do-while* loop that tests the condition after the loop body instead of before:

```scala
import scala.io.StdIn

var line = ""
do {
  line = StdIn.readLine()
  println(s"Read: $line")
} while (line != "")
```

Note that *while* and *do-while* are called *loops* because they don't result in an interesting value (the type of the result is `Unit`).

### More on `Unit`
A value, and only one value, exists whose type is `Unit`. It is called the *unit value* and it is written `()`. The existence of `()` is how Scala's `Unit` differs from Java's `void`. This can be better grasped with the followin example:

```scala
def greet() = { println("hi!") }
greet: greet[]() => Unit

() == greet()
hi!
res0: Boolean = true
```

The procedure `greet` has a result type of `Unit`. Therefore, greet returns the *unit value*: `()`. This is confirmed by comparing `() == greet()` which yields true.

Another construct which returns in the *unit value* is the reassignment to var. For example, the following construct will not be accepted in Scala:
```scala
var line = ""
while ((line = readLine()) != "") // don't work: comparing `Unit` and `String` always yield true.
  println(s"Read: $line")
```

Because the *while* loop results in no value, it is often left out of pure functional languages. However, Scala included it for pragmatic reasons, as sometimes a loop produces more readable code.

However, you should favor functional approaches, for example, to compute the greatest common divisor you could do:

```scala
def gcd(x: Long, y: Long): Long = {
  if (y == 0) x else gcd(y, x % y)
}
```

As a consequence, you should try and challenge *while* loops found in your code, in the same way you challenge *var* definitions.

## *for* Expressions
Scala's *for expression* lets you combine a few simple ingredients in different ways to express a wide variety of iterations: simple iterations through a sequence of integers, iterate over multiple collections of different kinds, filter out elements based on arbitrary conditions, produce new collections...

### Iteration through Collections
The simplest form of *for expressions* lets you iterate through all the elements of a collection.
For example, the following piece of code lists the files in the current directory:
```scala
import java.io.File

val filesHere = (new File(".")).listFiles()
for (file <- filesHere)
  println(file)
```

The `file <- filesHere` is called a *generator*. In each iteration, a new *val* named file is initialized with an element value. The compiler infers the type of file to be `File`, because `filesHere` is an `Array[File]`. Then, as a regular *for*, the body of the expression is executed.

The *for expression* syntax works for any kind of collection. One convenient special case is the `Range` type. Ranges can be succinctly created using the syntax *n to m* and then iterate over the generated range:
```scala
for (i <- 1 to 3)
  println(s"Iteration: $i")
iteration: 1
iteration: 2
iteration: 3
res0: Unit = ()  
```

You can skip the upper bound using `until`:
```scala
for (i <- 1 until 3)
  println(s"Iteration: $i")
iteration: 1
iteration: 2
res0: Unit = ()  
```

### Filtering
If you don't want to iterate through a collection in its entirety, you can add a filter &mdash; an *if clause* inside the for's parentheses.

For example, the following piece of code lists only the files with extension `".scala"` in the current directory:

```scala
for (file <- filesHere if file.getName.endsWith(".scala"))
  println(file)
```

Additional filtering clauses can be added as needed:
```scala
for (
  file <- filesHere
  if file.isFile
  if file.getName.endsWith(".bat")
  ) println(file)
```

### Nested Iteration
You can get *nested loops* by adding multiple `<-` clauses.

As an example, the following piece of code is an implementation of the `grep` command:

```scala
import scala.io.Source

def fileLines(file: File) = Source.fromFile(file).getLines().toList

def grep(pattern: String) = {
  for (
    file <- filesHere
    if file.getName.endsWith(".bat");
    line <- fileLines(file)
    if (line.matches(pattern))
  ) println(s"$file: $line")
}

grep(".*ECHO.*")
```
Note the use of the semicolon `;` as a delimiter to separate the first *generator-filter* block from the second one.

Scala syntax rules allows you to use braces instead of parentheses to surround the generators and filters. Use of curly braces has the added benefit of not having to use semicolons, because the Scala compiler will not infer semicolons while inside parentheses:

```scala
  for {
    file <- filesHere
    if file.getName.endsWith(".bat")
    line <- fileLines(file)
    if (line.matches(pattern))
  } println(s"$file: $line")
```

### Mid-stream variable bindings
An enhancement over the previous code would be to use `.trim` to eliminate extra characters found at the end of the line. If we do so, we would need to invoke it twice for the same input:

```scala
def grep(pattern: String) = {
  for (
    file <- filesHere
    if file.getName.endsWith(".bat");
    line <- fileLines(file)
    if (line.trim.matches(pattern))
  ) println(s"$file: ${line.trim}")
}
```

Scala allows you to bind intermediate results to a new variable using the following syntax:

```scala
  for {
    file <- filesHere
    if file.getName.endsWith(".bat")
    line <- fileLines(file)
    trimmedLine = line.trim
    if (trimmedLine.matches(pattern))
  } println(s"$file: $trimmedLine")
```

Note how the *bound variable* can be used both in the subsequent filter expression, and also in the body of the for expression.

### Producing a new collection
In order to return values from the body of the for expression, you have to prefix the body with the keyword `yield`.

```scala
def batFiles =
  for {
    file <- filesHere
    if file.getName.endsWith(".bat")
  } yield file

batFiles // <- Array[File] = Array("idea.bat", ...)
```

The result will include all of the yielded values contained in a single collection. The type of the resulting collection is based on the kind of collections processed in the iterations clauses. As `filesHere` is an array, and the type of the yielded expression is `File` the result is `Array[File]`.

| Using `yield` in a *for expression* |
|-------------------------------------|
| It must be noted that the `yield` keyword must be placed before the entire body, so if the for expression body is composed of several lines, therefore needing a curly brace to group them, yield should be used before the curly braces. |

The following piece of code transform a list of files into a list of integers:

```scala
val lineLengths =
  for {
    file <- filesHere
    if file.getName.endsWith(".bat")
    line <- fileLines(file)
    trimmedLine = line.trim
    if trimmedLine.matches(".*SET.*")
  } yield {
    println(s"match found on $file: $trimmedLine")
    trimmedLine.length
  }
```

In this example, an `Array[File]` is transformed into another one containing only the `*.bat ` files. For each of these, an `Iterator[String]` is generated (the result of the `fileLines` method). This initial iterator is transformed into another one of the same type containing only the trimmed lines matching the pattern. Finally, the length of these lines is yielded. 



## Exception Handling with *Try Expressions*
Instead of returning a value in the normal way, a method can terminate by throwing an exception. The method's caller can either catch and handle that exception, or ignore it, in which case the exception propagates to the caller's caller. This propagation mechanism continues (unwinding the call stack), until a method handles the exception or there are no more methods left.

### Throwing Exceptions
To throw an exception, you create an exception object and then throw it:

```scala
throw new IllegalArgumentException
```

In Scala, `throw` is an expression that has a result type:
```scala
val half =
  if (n % 2 == 0)
    n / 2
  else
    throw new RuntimeException("n must be an even number")
```

In the previous piece of code, an exception will be thrown if *n* is an odd number. Any context that tries to use the return value from a throw will never get to do so, and thus no harm will come.

Technically, an exception thrown has type `Nothing`. You can use a throw as an expression even though it will never evaluate to anything. This means that the type of `half` will be the type of the branch which does computer something.

### Catching Exceptions
You can catch exceptions using the following syntax:

```scala
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException

try {
    val f = new FileReader("input.txt")
} catch {
    case ex: FileNotFoundException => // handle missing file
    case ex: IOException => // handle I/O related error
}
```

Note that the syntax for the catch clause is consistent with Scala's pattern matching feature. The behavior, however, is the same as in other languages with exceptions. The body is executed, and if it throws an exception, each catch clause is tried in turn. In the example above, if an exception is found, the first clause will be tried, then the second. If the exception is neither `FileNotFoundException` or `IOException` the tr-catch will terminate and the exception will propagate further.

| Note on `@throws` annotation |
|------------------------------|
| Unlike Java, Scala does not require you to catch *checked* exceptions or declare them in a *throws* clause. You can however, use the `@throws` annotation but it is not required. |

### The `finally` clause
You can wrap an expression with a finally clause if you want some code to execute no matter how the expression terminates.

```scala
val file = new FileReader("input.txt")
try {
    // work with the file here
} finally {
    file.close()
}
```

The previous piece of code ensures that no matter whether an exception is thrown while working with the file, it gets properly closed.

### Yielding a value
As with most Scala control structures, *try-catch-finally* is also an expression and results in a value.

For example, the following piece of code tries to parse a URL but uses a default value if the URL is badly formed.
The result is that of the try clause if no exception is thrown, or the relevant catch clause if an exception is thrown and caught. If an exception is thrown but not caught, the expression has no result at all. The value computed in the finally clause (if there is one), is dropped.

```scala
def urlFor(path: String) =
    try {
        new URL(path)
    } catch {
        case e: MalformedURLException => new URL("http://www.scala-lang.org")
    }
```

## Match Expressions
Scala's *match expression* lets you select from a number of alternatives, just like a *switch* statement in other languages, but using an arbitrary pattern.

As an example, the following piece of code reads a food name and prints a companion to that food:

```scala
val mainDish = "chips"

mainDish match {
  case "salt" => println("pepper")
  case "chips" => println("salsa")
  case "eggs" => println("bacon")
  case _ => println("huh?")  
}
```

There are a few differences from Java's *switch statement*.
+ The default case is specified with an underscore, a *wildcard* symbol frequently used in Scala as a placeholder for a completely unknown value.
+ Any kind of constant (as well as other things) can be used in the cases, not only integer and Strings.
+ There are no breaks at the end of each alternative &mdash; the break is implicit and there is no fall-through from one alternative to the next.
+ The *match expression* results in a value


```scala
val friend = mainDish match {
  case "salt" => "pepper"
  case "chips" => "salsa"
  case "eggs" => "bacon"
  case _ => "huh?"
}

println(friend)
```

## Living without *break* and *continue*
Scala leaves out *break* and *continue* commands because they do not get along well with function literals. However it is possible to rewrite those using other constructs.

Consider, for example, the following piece of Java code which iterates over the list of arguments, considering  all arguments prefixed by *-* and breaking the loop as soon as it finds an argument that ends in `".scala"`
```java
// Java
int i = 0
boolean foundIt = false;
while (i < args.length) {
  if (args[i].startsWith("-")) {
    i = i + 1;
    continue;
  }
  if (args[i].endsWith(".scala")) {
    foundIt = true;
    break;
  }
  i = i + 1;
}
```

This can be rewritten in Scala as:
```scala
var i = 0
var foundIt = false
while (i <  args.length && !foundIt) {
  if (!args(i).startsWith("-")) {
    if (args(i).endsWith(".scala"))
      foundIt = true
  }
  i = i + 1
}
```

However, the code can be further improved to make it more functional. We want to get rid of the *while loop* and the mutable variables:

```scala
def searchFrom(i: Int): Int = {
  if ( i >= args.length) - 1
  else if (args(i).startsWith("-")) searchFrom(i + 1)
  else if (args(i).endsWith(".scala")) i
  else searchFrom(i + 1)
}
```

However, the `scala.util.control.Breaks` class provides a break method you can use to exist an enclosing block that is marked with the `breakable` keyword:

```scala
import scala.util.control.Breaks._
import java.io._

val input = "this is one line\n" +
            "this is another line\n" +
            "this is the third line\n" +
            "\n" +
            "this won't be read at all\n"

val in = new BufferedReader(new StringReader(input))

breakable {
  while (true) {
    println("? ")
    if (in.readLine() == "") break
  }
}
println("done!")
```

The previous piece of code will repeatedly read lines from the string until an empty line is found. The `Breaks` class implements break by throwing an exception that is caught by an enclosing application of the `breakable` method. The Scala syntax rules that allows you to use curly braces instead of parentheses, makes the *breakable* keyword feel like it is a built-in keyword.

## Variable Scope
Variable declarations in Scala programs have a *scope* that defines where you can use the name. The most common example of scoping is that curly braces generally introduce a new scope, so anything defined inside curly braces leaves scope after the final closing brace.

Consider the following example, which prints a multiplication table:

```scala
def printMultiTable() = {
  var i = 1
  // only i in scope here
  while (i <= 10) {
    var j = 1
    // both i and j in scope here
    while (j <= 10) {
      val prod = (i * j).toString
      // i, j, and prod in scope here
      var k = prod.length
      // i, j, prod, and k in scope here
      while (k < 4) {
        print(" ")
        k += 1
      }
      print(prod)
      j += 1
    }
    // i and j still in scope; prod and k out of scope
    println()
    i += 1
  }
  // i still in scope; j, prod, and k out of scope
}
```

All variables in the example are *local variables* meaning they can only be used in the function in which they are defined.

Once you define a variable, you can't define a new variable with the same name in the same scope. For example, the following code will not compile:

```scala
val a = 1
val a = 2 // ERR!
```

However, you can define a variable in an inner scope that has the same name as a variable in the outer scope.
```scala
val a = 1;
{
  val a = 2
  println(s"a=$a")
}
println(s"a=$a")
```

## Refactoring Imperative-Style Code
In this section, it will be shown how the imperative piece of code from the previous section is refactored in a more functional way. Guidelines will be given, explaining the rationale behind those changes:

```scala
// Returns a row as a sequence
def makeRowSeq(row: Int) =
  for (col <- 1 to 10) yield {
    val prod = (row * col).toString
    val padding = " " * (4 - prod.length)
    padding + prod
  }

// Returns a row as a String
def makeRow(row: Int) = makeRowSeq(row).mkString

// Returns table as a string with one row per line
def multiTable() = {
  val tableSeq =
    for (row <- 1 to 10)
      yield makeRow(row)
  tableSeq.mkString("\n")
}

val multiplicationTable = multiTable()
println(multiplicationTable)
```

First, the `printMultiTable` was refactored to return a string instead of having a side effect. The function was then renamed to `multiTable` as it no longer prints anything.
| Takeaway #1: use functions without side-effects |
|-------------------------------------------------|
| Side-effects free functions are easier to test. For example, to test `multiTable` we just have to establish assertions on the result of a function (as string). However, to test `printMultiTable` we will need to mock the `println` and `print` methods. |

The `multiTable` function uses does not feature a *while loop* or *vars* and rathe uses *val* (immutables), *for expressions* (which return a value, rather than modifying state), and helper functions.
| Takeaway #2: favor immutability and expressions |
|-------------------------------------------------|
| Immutable variables are less prone to errors because of inadvertent changes. Expressions are also more functional as they return values instead of producing side-effects. |

The functional code also includes refactoring of the imperative code to improve readability while also assigning clear responsibilities to the functions. The `makeRowSeq` uses a *for expression* whose generator iterates through column numbers 1 through 10. The body calculates the product of row and column, determines the padding needed for the product and yields the result of concatenating the padding and product strings.

The result of the *for expression* will be a *sequence*, which is a Scala collection that contains as elements those strings. 

The other helper function just calls `mkString`, a Scala method that lets you join the results returned by the `makeRowSeq` function.

| Takeaway #3: create small functions that decompose the problem in pieces |
|--------------------------------------------------------------------------|
| Creating small functions with clear responsibilities will improve code's readability and maintainability. |


Finally, the `multiTable` method will collect in a sequence of strings the result of calling `makeRow` with a *for expression* whose generator iterates through row numbers 1 to 10. Once we get the sequence, we flatten it by calling `mkString("\n")` which will *join* the sequence elements by the given string delimiter.

---
## You know you've mastered this chapter when...

+ You're comfortable using the `if` expression, and acknowledge it is more akin to the *ternary operator* than the traditional imperative *if* found in other programming languages.
+ You're comfortable writing *while* and *do-while* loops and understand that you should try and challenge them in the functional world as they're not expressions &mdash; they result in `Unit`.
+ You're aware of the *unit value* `()` which is the result value of expressions returning `Unit`. Procedures (i.e. functions that do not return a value) and reassignment to vars are expressions of this type.
+ You understand the *for expressions* in Scala, how you iterate over elements with a *generator* (e.g. `file <- files` and `i <- 1 to 10`), and the syntax rules to return a value from the *for expression's body*.
+ You are aware of the syntax for filtering within *for expressions* using an *if clause*.
+ You are comfortable doing nested iteration with *for expressions*, probably intertwined with *if clauses* for filtering. You understand the rules for variable scope in these situations.
+ You know how to use mid-stream variable bindings &mdash; defining intermediate variables while specifying the *for expression*. 
+ You understand the basics of Scala's exception handling &mdash; *try-catch expressions*, the *finally* keyword and how *try-catch* actually produces a value in Scala.
+ You are familiar with the syntax for catching exceptions in Scala.
+ You're comfortable with the syntax of the *match expression* in Scala, and how it relates (for now) to the *switch-case* in other programming languages.
+ You're aware that Scala does not feature *break* and *continue*, but you know how to survive without it.
+ You're familiar with the rules for variable scope in Scala.
+ You're aware of the tips for refactoring imperative code into a more functional way.
---

## Projects

### [01 &mdash; Built-in Control Structures](./01-built-in-control-structures-worksheet)
IntelliJ worksheet project with several worksheet illustrating the concepts of the section.
