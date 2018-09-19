# Part 2 &mdash; Scala In Depth: Built-in Control Structures
> Introducing Scala's built-in control structures.

--- TBD
  + Introducing Scala's `if` expression
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


It must be noted that the `yield` keyword must be placed before the entire body, so if the for expression body is composed of several lines, therefore needing a curly brace to group them, yield should be used before the curly braces.

---
## You know you've mastered this chapter when...

+ You're comfortable using the `if` expression, and acknowledge it is more akin to the *ternary operator* than the traditional imperative *if* found in other programming languages.
+ You're aware of the `while` and *do-while* loops and understand that you should try and challenge loops as they're not expressions &mdash; they result in `Unit`.
+ You're aware of the *unit value*  `()` which is the result value of expressions returning `Unit`. Procedures (i.e. functions that do not return a value) and reassignment to vars are expressions of this type.

---

## Projects

### [01 &mdash; Built-in Control Structures](./01-built-in-control-structures-worksheet)
IntelliJ worksheet project with several worksheet illustrating the concepts of the section.

### [02 &mdash; Hello Scala Application](./02-hello-scala-application-sbt)
Simple SBT project that illustrates how to define an application entrypoint in Scala.

### [03 &mdash; Hello App Trait SBT] (./03-hello-app-trait-sbt)
Simple SBT project that illustrates how to define an applicaiton entrypoint in Scala by extending the `App` trait.