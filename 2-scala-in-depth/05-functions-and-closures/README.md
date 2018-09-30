# Part 2 &mdash; Scala In Depth: Functions and Closures
> A tour through all the flavors of functions in Scala.

---
+ Scala's methods: private and public methods
+ Local functions (functions nested within other functions/methods)
+ The concept of *first-class functions*
+ Scala's placeholder syntax for function literals
+ Partially applied functions
+ Closures
+ Special Function Call Forms: repeated parameters, named arguments and default arguments
+ Tail-Recursion in Scala: limitations
---

## Intro
Scala offers several ways to define functions that are not present in Java. Besides methods (functions that are members of some objects), there is also support for nested functions, function literals and function values.

## Methods
A function that is a member of some object is called a method. Consider the following piece of code that defines an object the features two methods that together read a file and print out all the lines exceeding a given width:

```scala
import scala.io.Source


object LongLines {
  def processFile(filename: String, width: Int) :Unit = {
    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(filename, width, line)
  }

  private def processLine(filename: String, width: Int, line: String) = {
    if (line.length > width)
      println(s"${filename}: ${line.trim}")
  }
}
```

The `processFile` method creates a `Source` object instance from the *filename*. Then it calls the `getLines` method which returns a generator through which we iterate, and for each item the helper method `processLine` which tests the length of the line and prints it if it is greater than the threshold.

## Local Functions
Using the functional programming style of having small functions with well-defined responsibilities gives us the advantage of being able to flexibly compose building blocks to do more difficult things. Each building block should be simple enough to be understood on its own.

Once problem with this approach is that too many functions can pollute the program's namespace, so it's desirable to package them in reusable classes and objects and hide the helper functions from the clients of a class.

The most straight-forward method to do that is to declare the method as private, but Scala also offers the possibility of defining functions within functions. Just like local variables, such *local functions* are visible only in their enclosing block.

The following block of code implements the same functionality seen in the previous section, but using local functions instead of a private method:
```scala
import scala.io.Source


object LongLines {
  def processFile(filename: String, width: Int) :Unit = {
    def processLine(filename: String, width: Int, line: String) = {
      if (line.length > width)
        println(s"${filename}: ${line.trim}")
    }
    
    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(filename, width, line)
  }
}
```

There's a bonus improvement we can do here, as now `width` is in scope of both the local function and the body of the `processFile` method:

```scala
object LongLines {
  def processFile(filename: String, width: Int) :Unit = {
    def processLine(line: String) = {
      if (line.length > width)
        println(s"${filename}: ${line.trim}")
    }

    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(line)
  }
}
```

## First-class Functions
Scala has *first-class functions* &mdash; you can write functions and unnamed literals and pass them as values to other functions.

A function literal is compiled into a class that wen instantiated at runtime is a function value. 

The following piece of code defined a function literal that adds one to a number:
```scala
(x: Int) => x + 1
```

That should be understood as follows:
+ `=>` designates that the thing on the left (any integer *x*) will be converted into the thing on the right.

Function values are objects, so you can store them in variables if you like. Then, you can use the usual syntax (parentheses) to invoke them:

```scala
var increase = (x: Int) => x + 1
increase(10) // -> 11
```

Also, as it is declared as a `var` it can be reassigned:
```scala
increase = (x: Int) => x + 99
increase(1) // -> 100
```

Function literals are not limited to one-liners:

```scala
increase = (x: Int) => {
  println("Hello")
  println("to")
  println("Jason Isaacs!")
  x + 1
}
```

Many scala libraries give you opportunities to use function literals to customize the behavior of a function in a very flexible way. For instance, there is a `foreach` method for all collections that takes a function as an argument and invokes (*applies*) that function to each of the elements:

```scala
val numbers = List(-11, -10, -5, 0, 5, 10)
numbers.foreach((x: Int) => println(x))
```

Collection types also feature a `filter` method that accepts a *test function* the selects the elements of the collection that pass the test the user provides:

```scala
val numbers = List(-11, -10, -5, 0, 5, 10)
numbers.filter((x: Int) => x > 0) // -> List(5, 10)
```

### Short Forms of Function Literals
Scala provides a number of ways to make the function literals in an even more succinct way, leaving off redundant information that can be inferred.

This it is common to write `(x: Int) => x > 0` as
```scala
numbers.filter(x => x > 0)
```

## Placeholder Syntax
To make a function literal even more concise Scala allows you to use *underscores* for one or more parameters, so long as each parameter appears only one time within the function literal.
This is better understood with an example:

```scala
numbers.filter(_ > 0)
```

The *underscore* can be thought of as a *"blank"* in the expression that needs to be *"filled in*". This blank will be filled in with an argument to the function each time the function is invoked.

This syntax might make the Scala compiler complain, if it does not have enough information to infer the missing parameters types:

```scala
val sum = _ + _ // Err: missing parameter type for expanded function
```

In such cases, Scala allows you to type the arguments:
```scala
val sum = (_:Int) + (_:Int)
sum(2, 3) // -> 5
```

But that kind of defuses the purposes for conciseness and simplicity.

## Partially Applied Functions
Scala allows you to replace an entire parameter list with an underscore, instead of individual parameters.

For example, we can do:
```scala
numbers.foreach(println _)
```

Scala treats that short form as `numbers.foreach(x => println(x))` with the difference that the underscore in that case is not a placeholder for a single parameter, but rather for an entire parameter list.

Using the underscore in that way creates a *partially applied function*. 
In Scala, when you invoke a function passing any needed arguments you are said to *apply that function to the arguments* for example:
```scala
def sum(a: Int, b: Int, c: Int) = a + b + c 
sum(1, 2, 3) // -> 6
```

A partially applied function is an expression in which you don't supply all of the arguments needed by the function, and instead supply some or none of the needed arguments.

Let's see it with an example that supplies none of the required arguments:

```scala
val a = sum _
a(1, 2, 3) // -> 6
```

The variable named `a` refers to a function value object. This function value is an instance of a class generated automatically by the Scala compiler from `sum _`. The class generated by the compiler has an `apply` method that takes three arguments, so you could have also done:

```scala
a.apply(1, 2, 3) // -> 6
```

Another way to think about this kind of expression involving an underscore that represents an entire parameter list is to be able to convert a function or method definition into a function value:

```scala
def sum(a: Int, b: Int, c: Int) = a + b + c 
val a = sum // ERR: missing argument list for function

val a = sum _ // OK!
```

A partially applied function has that name because you are not applying the function to all of the arguments. In the previous examples we were applying it to none of them, but we can apply only to some of them:

```scala
val b = sum(2, _:Int, 3)
b(5) // -> 10
```

Note that, similarly to other programming languages, you can leave off the *underscore* if you are partially applying *none* of the parameters of the function:

```scala
numbers.foreach(println)
```

## Closures
Consider the following function literal:
```scala
(x: Int) => x + more
```

From the point of view of the function, `more` is a free variable, because the function itself does not give a meaning to it. The `x` variable, by contrast, is a bound variable because it does have a meaning in the context of the function.

Obviously, if you try to use that function by itself, it will cause a compilation error. But it will work well if the compiler has seen a definition of `more` before:

```scala
var more = 1
val addMore = (x: Int) => x + more

addMore(5) // -> 6
```

The function value (the object) created at runtime from this function literal is called a *closure*. The name arises from the act of *"closing"* the function literal by *"capturing"* the binding of its free variables. By contrast, a function literal with no free variables such as `(x: Int) => x + 1` is called a *close term* &mdash; strictly is not a closure because it's already closed when written. Thus, a function literal with free variables is called an *open term*.

What happens in a closure when the value of the free term is changed? In Scala, the closure sees the change and gets updated.
```scala
var more = 1
val addMore = (x: Int) => x + more
addMore(5) // -> 6

more = 10 
addMore(5) // -> 15
```

Therefore, in Scala the closures captures a reference to the variable and not the variable value itself. The same is true in the opposite direction: changes made by a closure to a captured variable are visble outside of the closure:

```scala
val someNumbers = List(-11, -10, -5, 0, 5, 10)

var sum = 0
someNumbers.foreach(sum += _)
println(sum) // -> -11
```

And what if a closure accesses some variable that has several different copies as the program runs? For example, what if a closure uses a local variable of some function, and the function is invoked many times. In this case, the instance used is the one that was active at the time the closure was created. That is, the reference to the free variable is closed at the time the closure was created.

```scala
def makeIncreaser(inc: Int) = (x: Int) => x + inc

val inc1 = makeIncreaser(1) // bound with 1
val inc2 = makeIncreaser(2) // bound with 2

inc1(99)  // -> 100
inc2(99)  // -> 101
```

Note that, in order to keep the reference of the free variable alive for the closure even when the variable has gone out of scope, the Scala compiler will move the variable from the stack to the heap.

## Special Function Call Forms
Typically, functions and function calls in Scala follow the syntax seen in the previous sections: functions will have a fixed number of parameters and will have an equal number of arguments.

However, Scala also features a few special forms of function definitions and function calls to address some special needs.

### Repeated Parameters
Scala allows you to indicate that the last parameter to a function may be repeated. This allows clients to pass variable length argument lists to the function.

```scala
def echo(args: String*) =
  for (arg <- args)
    println(arg)

echo("one")
echo ("one", "two", "three", "catorce")
```

The signature `String*` is actually an `Array[String]` that will allow you to pass a variable number of arguments. However, if you try to pass an actual *array* it will fail at compile time.
```scala
val nums = Array("one", "two", "three", "catorce")
echo(nums) // Err: type mismatch
```

In order to use the array, you will have to use this special syntax:
```scala
echo(nums: _*)  // -> pass each element of arr as its own arg to the function
```

### Named Arguments
In a normal function, the arguments in the call are matched by position:
```scala
def speed(distance: Float, time: Float) = distance / time

speed(5.5F, 35.0F)
```

Named arguments allow you to pass arguments to a function is a different order:

```scala
speed(time = 35, distance = 5.5)
```

Scala also allows to mix positional and named arguments. In that case, the positional arguments come first.
```scala
def multiParam(first: Int, second: String, third: Float, fourth: Char) =
  println(
    s"""first=$first
       |second=$second
       |third=$third
       |fourth=$fourth
     """.stripMargin)

multiParam(1, "one", fourth = 'O', third = 1.0f)
```

| Note |
|------|
| Named arguments are most frequently used in combination with default parameter values |

### Default Parameter Values
Scala lets you specify default values for function parameters. The argument for such a parameter can optionally be omitted from a function call, in which cse the corresponding argument will be filled in with the default.

The following piece of code has one parameter `out` which takes the value `Console.out` as a default:

```scala
def printTime(out: java.io.PrintStream = Console.out) =
  println(System.currentTimeMillis())

printTime()
printTime(Console.err)
```

Default parameters are especially useful when used in combination with named parameters, as they give extra flexibility to the form in which the function is called:

```scala
def printTime2(out: java.io.PrintStream = Console.out, divisor: Int = 1) =
  println(System.currentTimeMillis() / divisor)
printTime2()
printTime2(Console.err)
printTime2(divisor = 1000)
printTime2(Console.err, divisor = 1000)
```

## Tail Recursion
Consider the following piece of code that uses recursion to approximate to a value by repeatedly improving a guess until it is good enough:
```scala
def approximate(guess: Double): Double =
  if (isGoodEnough(guess)) guess
  else approximate(improve(guess))
```

Such a function is much more readable than the version using a loop instead of recursion:
```scala
def approximateLoop(initialGuess: Double): Double = {
  var guess = initialGuess
  while (!isGoodEnough(guess))
    guess = improve(guess)
  guess
}
```

You might be tempted to say that the second version, although less readable is more efficient, but it turns out that both are almost exactly fast.
This happens because Scala applies an important optimization because the last thing that happens in the functional (recursive) version is the invocation of the recursive function. These functions, which call themselves as the last action are called *tail recursive*. Those functions are transparently transformed into loops by the compiler, thus making them easier to read and maintain and as efficient as their imperative (using loops) counterparts.

A *tail-recursive*  function will not build a new stack frame for each call; all calls will execute in a single frame. But this optimization can only be applied when they call themselves as the last action.

For example, the following function is not *tail-recursive* because there is an increment operation before calling itself:

```scala
def boom(x: Int): Int =
  if (x == 0) throw new Exception("boom!")
  else boom(x - 1) + 1

boom(3)
java.lang.Exception: boom!
	at #worksheet#.boom(07-hello-tail-recursion.sc:3)
	at #worksheet#.boom(07-hello-tail-recursion.sc:4)
	at #worksheet#.boom(07-hello-tail-recursion.sc:4)
	at #worksheet#.boom(07-hello-tail-recursion.sc:4)
	at #worksheet#.#worksheet#(07-hello-tail-recursion.sc:6)
```

However, the following is tail-recursive, so even when you call it with a large number of iterations, one stack frame is shows in the exception message:

```scala
def boom(x: Int): Int =
  if (x == 0) throw new Exception("boom!")
  else boom(x - 1)

boom(10)
java.lang.Exception: boom!
	at #worksheet#.boomTR(07-hello-tail-recursion.sc:10)
	at #worksheet#.#worksheet#(07-hello-tail-recursion.sc:13)
```

The use of tail-recursion in Scala is fairly limited because the JVM instruction set makes it difficult to implement more advanced forms of tail-recursion.

For example, the following piece of code cannot be optimized, because the call is to another function

```scala
def isEven(x: Int): Boolean =
  if (x == 0) true else isOdd(x - 1)
def isOdd(x: Int): Boolean =
  if (x == 0) false else isEven(x -1 )
```

The following piece cannot be optimized either:
```scala
def funValue = nestedFun _
def nestedFun(x: Int): Unit = {
  if (x != 0) {
    println(x)
    funValue(x - 1)
  }
}
```

Here the last action is a call to itself but through a function value.

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

### [01 &mdash; Functions and Closures](./01-functions-and-closures-worksheet)
IntelliJ worksheet project with several worksheet illustrating the concepts of the section.
