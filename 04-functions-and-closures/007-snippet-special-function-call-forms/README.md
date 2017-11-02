# 007 &mdash; Special Function Call Forms (snippet)
> variable length args, named parameters and default arg values

## Description
A few special forms of function definitions and function calls have been added to the Scala language to address function specific related needs.

### Repeated Parameters
Scala allows you to indicate that the last parameters may be repeated, allowing the consumer of the function to pass variable length argument lists to the function.
```scala
def echo(args: String*) =
  for (arg <- args)
    println(arg)
```

You can call that function with several *String* arguments or an array if you use the special notation `_*`:
```scala
echo("Hello", "to", "Jason Isaacs!")

val myArgs = Array("Hello", "to", "Idris Elba!")
echo(myArgs: _*) 
```

`_*` tells the compiler to pass each element of the array as its own argument.

### Named Parameters
By default, arguments in a function call are matched by position, but Scala also allows named parameters with a special syntax:
```scala
def speed(distance: Float, time: Float): Float =
  distance / time

speed(100, 10)  // default
speed(time = 10, distance = 100) // named parameters
```

### Default Parameter Values
Scala lets you specify default values for function parameters with the following syntax:
```scala
def printTime(out: java.io.PrintStream = Console.out) =
  out.println(s"time = ${System.currentTimeMillis()}")

printTime() // use default value
printTime(Console.err) // override default value
```

It comes specially handy when mixed-in wth named parameters:
```scala
def printTime(out: java.io.PrintStream = Console.out, divisor: Int = 1) =
  out.println(s"time = ${System.currentTimeMillis() / divisor}")

printTime() // use default values for both
printTime(Console.err) // override out
printTime(divisor = 1000) // override divisor
printTime(Console.err, 1000) // override both
```


## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load special-function-call-forms.scala
```