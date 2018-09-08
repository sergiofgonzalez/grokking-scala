# Part 2 &mdash; Scala In Depth: Basic Types and Operations
> Foundational concepts on classes and objects 

--- TBD
  + Introducing classes, fields and methods
  + The semicolon inference
  + Singleton objects
  + How to write and run a Scala application
---

## Scala's Basic Types
The following table lists the basic types available in Scala:

| Basic Type | Range                                                         |
|------------|---------------------------------------------------------------|
| `Byte`     | 8-bit signed, two's complement integer (-2^7 to 2^7 - 1       |
| `Short`    | 16-bit signed, two's complement integer (-2^15 to 2^15 - 1)   |
| `Int`      | 32-bit signed, two's complement integer (-2^31 to 2^31 - 1)   |
| `Long`     | 64-bit signed, two's complement integer (-2^64 to 2^64 - 1)   |
| `Char`     | 16-bit unsigned Unicode character (-0 to 2^16 -1)             |
| `String`   | Sequence of Chars                                             |
| `Float`    | 32-bit IEEE 754 single-precision float                        |
| `Double`   | 64-bit IEEE 754 double-precision float                        |
| `Boolean`  | true or false                                                 |


Except for `String` that resides in the `java.lang` package, the rest of the types are defined in the `scala` package. Both, `java.lang` and `scala` members are implicitly imported into every Scala source file, so you can use the simple names.

## Literals
All the basic types can be written as literals (that is, can expressed as constant values in the code).

### Integer Literals
Integer literals can be expressed in the same way as in Java, and both decimal and hexadecimal

```scala
scala> val hex = 0x5
hex: Int = 5
```

If the number begins with a non-zero digit, it is understood as a decimal (base 10)
```scala
scala> val dec = 5
dec: Int = 5
```

If an integer ends in an `L` or `l` it is `Long` otherwise, it is an `Int`.
```scala
scala> val longVal = 5L
longVal: Long = 5
```

You can force a literal to be a `Short` or `Byte` by explicitly declaring the type of the variable that holds the literal value:

```scala
scala> val byteVal: Byte = 5
byteVal: Byte = 5

scala> val shortVal: Short = 5
shortVal: Short = 5
```

### Floating Point Literals
Floating point literals are made up of decimal digits, optionally containing a decimal point, and optionally followed by an `E` or `e` and an exponent (power of 10).

```scala
scala> val d = 1.2345
d: Double = 5

scala> val d1 = 1.2345e1
d1: Double = 12.345
```

If a floating-point literal ends in an `F` or `f` it is a `Float`; otherwise it is a `Double`. Optionally, a `Double` can also end in a `D` or `d`:

```scala
scala> val f = 1.2345f
f: Float = 5

scala> val d = 1.2345e1D
d: Double = 12.345
```

### Character Literals
Character literals are composed of any Unicode character between single quotes.

```scala
scala> val a = 'A'
a: Char = A
```

You can also identify a character using its Unicode code point using the following syntax:
```scala
scala> val a = '\u0041'
a: Char = A
```

Note that Scala also allows these type of Unicode character specification to be found anywhere in a Scala program:

```scala
scala> val B\u0041\u0044 = 1
BAD: Int = 1
```

Special escape sequences:

| Literal | Meaning                  |
|---------|--------------------------|
| \n      | line feed (\u000A)       |
| \b      | backspace (\u0008)       |
| \t      | tab (\u0009)             |
| \f      | form feed (\u000C)       |
| \r      | carriage return (\u000D) |
| \"      | double quote (\u0022)    |
| \'      | single quote (\u0027)    |
| \\      | backslash (\u005C)       |


### String Literals
A string literal is composed of characters surrounded by double quotes:
```scala
scala> val hello = "hello"
hello: String = hello
```

You can use the escape sequences within quotes as with character literals:
```scala
scala> val escapes = "\\\"\'"
escapes: String = \"'
```

Scala allows you define raw strings, which is useful for strings that span multiple lines and/or contain a lot of escape sequence. You start and end a raw string with three double quotes and the interior of the raw string can contain any characters whatsoever:
```scala
println("""Hello to Jason Isaacs!
           Type "Help" for help.""")
```

Note that the output might not be quite what you'd expect, because the leading spaces of the second line are included in the string:
```
Hello to Jason Isaacs!
           Type "Help" for help.
```

This can be easily fixed by including a pipe character at the beginning of each line and then calling `stripMargin` before sending the string to `println`. Also some IDEs will help you with cleverer and clearer ways to format raw strings.

```scala
println("""|Hello Jason Isaacs!
           |Type "Help for help.""".stripMargin)

println(
  """Hello Jason Isaacs!
    |Type "Help for help.
  """.stripMargin)
```

See [01 &mdash; Basic Types and Operations: Raw Strings](./01-basic-types-and-operations-worksheet/01-raw-strings.sc).

### Symbol literals
A symbol literal is written `'ident`, where *ident* can be any alphanumeric identifier. Such literals area mapped to instances of the predefined class `scala.Symbol`. Specifically, the literal `cymbal` will be expanded by the compiler to a factory method invocation `Symbol("cymbal")`.

Symbol literals are typically used in situations where you would just use an identifier in a dynamically typed language.

Let's try to clarify this with an example. Let's suppose that you write a method that updates a record in a database:
```scala
scala> def updateRecordByName(r: Symbol, value: Any) = {
  // update is happening here
}
```

The method takes a symbol indicating the name of the record field and a value with which the field should be updated. A dynamically typed language, will not enfornce the field identifier to be declared beforehand, but a statically typed one, like Scala, will:
```scala
scala> updateRecordByName(favoriteAlbum, "OK Computer") // Err: not found: value favoriteAlbum
```

To work around this, Scala lets you pass a Symbol literal instead:
```scala
scala> updateRecordByName('favoriteAlbum, "OK Computer") // Err: not found: value favoriteAlbum
```

There's not much you can do with a *symbol*, except find its name:
```scala
scala> val s = 'aSymbol
s: Symbol = 'aSymbol

scala> val nm = s.name
nm: String = aSymbol
```

Note that symbols are *interned*: references the same symbol literal will point to the same *Symbol object*.

See [01 &mdash; Basic Types and Operations: Symbols](./01-basic-types-and-operations-worksheet/02-symbols.sc).

### Boolean literals
The `Boolean` type has two literals:
```scala
scala> val bool = true
bool: Boolean = true

scala> val wool = false
wool: Boolean = false
```

## String Interpolation
Scala includes a flexible mechanism for string interpolation:

```scala
val name = "Jason Isaacs"
println(s"Hello to $name!) // yields: Hello to Jason Isaacs!
```

Because the letter *s* precedes the open quote, Scala will use the *s string interpolation* to process the literal. This will evaluate each embedded expression, invoke toString on each result and replace the embedded expressions in the literal with those results.

Any expression can be placed after the dollar sign (`$`) in a processed string literal. If the expression uses something other than a variable identifier, you must place it in curly braces:

```scala
scala> s"The answer is ${6 * 7}."
res0: String = The answer is 42.
```

Scala provides two additional string interpolators: the *raw string interpolator* and the *f string interpolator*.

The *raw string interpolator* behaves like the *s string interpolator* except that it does not recognize character literal escape sequences:

```scala
println(raw"No\\\\escape\n") // -> No\\\\escape\n
```

The *f string interpolator* allows you to attach printf-style formatting instructions to embedded expressions. Those instructions should be placed after the expression, starting with a percent sign `%` using the syntax specificed by the `java.util.Formatter`:

```scala
scala> f"${math.Pi}%.5f"
res1: String = 3.14159
```

If the *f string interpolator* is used without formatting instructions, its implementation will default to `%s`, so the `toString` method will be used.

Libraries and users can define other string interpolators for other purposes.

## Operators Are Methods

---
## You know you've mastered this chapter when...
+ You know how to define classes, public and private fields and methods for your classes
+ You are aware of the semicolon inference rules that allow you to leave off the semicolon at the end of the statements in most of the cases, and how to prevent *automatic spanning* using parentheses
+ You know about *Singleton objects* and their role as *a class's companion object* when they share the same name and source code file of a class.
+ You know how to define a *standalone singleton object* that serves as an entry point for an Scala application, and how to simplify the approach using the `App` trait
+ You are comfortable with IntelliJ IDE running worksheets for quick prototyping, and running and debugging applications. 
+ You are comfortable starting the Scala console within SBT, and running
---


## Projects

### [01 &mdash; Basic Types and Operations](./01-basic-types-and-operations-worksheet)
IntelliJ worksheet project with several worksheet illustrating the concepts of the section.

### [02 &mdash; Hello Scala Application](./02-hello-scala-application-sbt)
Simple SBT project that illustrates how to define an application entrypoint in Scala.

### [03 &mdash; Hello App Trait SBT] (./03-hello-app-trait-sbt)
Simple SBT project that illustrates how to define an applicaiton entrypoint in Scala by extending the `App` trait.