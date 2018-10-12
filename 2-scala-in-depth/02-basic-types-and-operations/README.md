# Part 2 &mdash; Scala In Depth: Basic Types and Operations
> Scala's basic types and how to use them 

---
  + Introducing Scala's basic types: `Byte`, `Short`, `Int`...
  + Literals syntax for integral types, floats, characters, strings, booleans and symbols
  + Scala's string interpolators: the *s*, *raw string* and *f* string interpolators
  + Operators as regular methods: rules for infix, prefix and postfix operators
  + Arithmetic, relational, bitwise and logical operations in Scala
  + Object equality in Scala
  + Operator precedence and associativity
  + Additional methods on basic types: rich wrapper API
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
Scala provides a rich set of operators for its basic types, which happen to be nothing more than syntactic sugar for ordinary method calls.

For example, `1 + 2` is the same thing as `(1).+(2)`. This means that Scala defines the `+()` method that takes an `Int` and returns an `Int`, and provides the necessary internal rules to make use of that `+()` method in a more natural way.

```scala
scala> val sum = 1 + 2
sum: Int = 3

scala> val uglySum = (1).+(2)
uglySum: Int = 3
```

Scala supports *method overloading*, so you can define different `+()` method that share the same name but accept different parameters:

```scala
scala> val sumLong = 1 + 2L
sumLong: Long = 3
```

| operators |
|-----------|
| The `+` symbol is an *infix* operator &mdash; the operator sits between the object and the parameter.<br>Scala also has two other operator notations: *prefix* and *postfix*. In *prefix* notation the method name is placed before the object on which you are invoking the method (e.g. the minus in `-7`). In *postfix* the method is placed after the object (e.g. *toLong* in `5.toLong`) |

The interesting thing, is that the syntactic sugar for the `+` operator can be applied to any method:

```scala
val s = "Hello to Jason Isaacs!"
s.indexOf('J') // -> 9

s indexOf 'J'  // -> 9
```

These rules can also be applied to methods taking several parameters. For example, there's an overloaded `indexOf` that takes two parameters, the character for which to search and an index at which to start:
```scala
val s = "Hello to Jason Isaacs!"
s.indexOf('o', 5) // -> 7
s indexOf('o', 5) // -> 7
```

As a consequence, any method can be an operator: it only depends on how you use it (which ultimately depends on how natural it feels to use a method as an operator).

Note that:
+ *infix* operators are binary; they take two operands
+ *prefix* and *postfix* operators are unary; they take just one operand

As with *infix* operators, *prefix* and *postfix* operators (e.g. `-2.0`, `!found`, `~0xFF`) are shorthands for method invocation. In this case, the name of the method must have `unary_` prepended to the operator name.

```scala
val minus2 = -2.0
val uglyMinus2 = (2.0).unary_-
```

There are only a bunch of characters that can be used as prefix operators are *+*, *-*, *!* and *~*. Thus if you define a method named `unary_!` you could invoke that method on a value or variable of the appropriate type using the *prefix operator* notation. Note that, as `*` is not one of the supported prefix operators, you wouldn't be able to use something like `*p` even when defining a method named `unary_*`.

*Postfix* operators take no arguments. As Scala lets you leave off parentheses in method calls not requiring arguments, you can do:

```scala
val msg = "Hello to Jason"
msg.toLower // regular method call, yielding hello to jason
msg toLower // postfix use of toLower of msg operand
```

| Leaving off parentheses in method calls |
|-----------------------------------------|
| The convention is that you should only include parentheses in method calls without arguments when the method has side-effects. In any other case, it's a best practice to leave them off (`toLower`, `toLong`, ...) |

## Arithmetic Operations
Arithmetic methods (*+*, *-*, *&ast;*, */* and *%*) via infix operation notation can be invoked on any numeric type.

When both left and right operands are integral types the */* operator will yield the whole number portion of the quotient, excluding any remainder. In that case, the *%* will give you the remainder of an implied integer division.

The numeric types also offer the unary prefix operators *+* and *-* that indicates whether a literal number is positive or negative.

## Relational and Logical Operations
Numeric types can be compared using *>*, *<*, *>=* and *<=* and yield a `Boolean` result. In addition, you can use the unary operator *!* to invert a *boolean* value.

Logical methods (*&&* and *&* and *||* and *|*) take `Boolean` operands in infix notation and yield a `Boolean`result.

The *&&* and *||* short-circuit: expressions built from these operators are only evaluated as far as needed to determine a result, from left to right. However, the *&* and *|* operators performs the same logical operation, but don't short-circuit like *&&* and *||* does.

```scala
def salt(): Boolean = { println("salt"); true }
def pepper(): Boolean = { println("pepper"); false }
salt() && pepper()  // -> false, prints salt and pepper
salt() || pepper()  // -> true, prints only salt because it short-circuits
salt() | pepper()   // -> true, prints salt and pepper because it doesn't short-circuit
```

## Bitwise Operations
The bitwise operations are *and (&)*, *or (|)* and *xor (^)*. The unary bitwise operator *~* inverts each bit in its operand.

Alsom Scala defines three shift methods: *shift-left (<<)*, *shift-right (>>)* and *unsigned shift right (>>>)*. Shift-left and unsigned shift right fill with zeroes as they shift. Shift right fills with the highest but of the left-hand value as it shifts.

## Object Equality
Two objects can be compared for equality using either *==* or *!=*. In contrast to Java, these operations apply to all objects and not only basic types:

```scala
val aList = List(1, 2, 3)
val anotherList = List(1, 2, 3)
aList == anotherList
```

You can compare two objects of differen types:
```scala
1 == 1.0 // -> true
"hello" == List(1, 2, 3) // -> false
```

You can even compare against null, or against things that might be null and no exception will be thrown:
```scala
List(1, 2, 3) == null // -> false
null == List(1, 2, 3) // -> false
```

Using *==* will yield true on different objects provided that their contents are the same and their *equals* method is written based on contents. That's why `1 == 1.0` yields true.

| `==` in Scala                           |
|-----------------------------------------|
| `==` has been carefully crafted in Scala so that you just get the equality comparison you want int most cases. This is accomplished by first checking the left side for null. If it is not null, then the *equals* method is called. Since *equals* is a method, the precise comparison depends on the type of the left-hand argument. Since there is an automatic null check, you do not have to check yourself. |

As you see, *==* compares value equality, rather than *reference equality*. Scala provides a facility for comparing reference equality `eq` and `ne` but are only applied to objects that directly map to Java objects.

## Operator Precedence and Associativity
Operator precedence determines which parts of an expression are evaluated before the other parts. For example, `2 + 2 * 7` evaluate to `16` because the `*` operator has a higher precedence than the `+` operator.

As Scala doesn't have operators (but rather methods that can be used in operator notation), precedence is decided by inspecting the first character of the methods used in operator notation, with one exception (the assignment operator).

| Operator Precedence (from highest to lowest) |
|----------------------------------------------|
| (all other special characters)               |
| * / %                                        |
| + -                                          |
| :                                            |
| = !                                          |
| < >                                          |
| &                                            |
| ^                                            |
| |                                            |
| (all letters)                                |
| (all assignment operators)                   |


For example, 
```scala
scala> 2 << 2 + 2  // evaluate as 2 << (2 + 2)
Int: 32
```

The one exception concerns the assignment operator: if an operator ends in an equal character (`=`), and the operator is not one of the comparison operators (`<=`, `>=`, `==` or `!=`), then the precedence of the operator is the same as that of simple assignment (`=`).

Thus, `x *= y + 1` is the same as `x *= (y + 1)` because `*=` is classified as an assignment operator (it ends in an equal character), whose precedence is lower than `+`.

When multiple operators of the same precedence appear side by side in an expression, the *associativity* of the operators determines the way the operators are grouped. This associativity is determined in Scala by inspecting the last character of the method name. Any method that ends in a `:` character is invoked on its right operand, passign in the left operand. Methods that end in any other character are the other way around: they're invoked on their left operand, passing in the right operand.

Thus, `a * b` yields `a.*(b)` while `a ::: b` yields `b.:::(a)`.

Independently of the associativity rules, operands are always evaluated from left to right.

The associativity rule also plays a role when multiple operators of the same precedence appear side by side. If the methods end in `:` those are grouped right to left as in `a ::: b ::: c` -> `a ::: (b ::: c)`; otherwise they're grouped from left to right as in `a * b * c` -> `(a * b) *c`.

As always, it is considered good style to use parentheses to clarify precedence on complex operations.

## Rich Wrappers
Many more methods are available on Scala's basic types via *implicit conversions*. This technique allows you to define a *rich wrapper* on a basic type to provide such methods.

Here we have a few examples:

| Code           | Result      |
|----------------|-------------|
| 0 max 5        | 5           |
| 0 min 5        | 0           |
| -2.7 abs       | 2.7         |
| -2.7 round     | -3L         |
| 1.5 isInfinity | false       |
| (1.0 / 0) isInfinity | true  |
| 4 to 6         | Range(4, 5, 6) |
| "bob" capitalize | "Bob"     |
| "robert" drop 2 | "bert"     |


You can find more information about those methods on the API documentation for the *rich wrappers*:

| Basic Type | Rich Wrapper                     | 
|------------|--------------------------------- |
| Byte       | scala.runtime.RichByte           |
| Short      | scala.runtime.RichShort          |
| Int        | scala.runtime.RichInt            |
| Long       | scala.runtime.RichLong           |
| Char       | scala.runtime.RichChar           |
| Float      | scala.runtime.RichFloat          |
| Double     | scala.runtime.RichDouble         |
| Boolean    | scala.runtime.RichBoolean        |
| String     | scala.collection.immutable.StringOps |



---
## You know you've mastered this chapter when...

+ You are aware of the different *basic types* that Scala provides and recognize them as full-fledged objects (rather than primitive types as in other programming languages).
+ You know how to define integer, floating point, character, string, boolean and symbol literals. You must be aware of the intrincacies of each literal definitions:
  + decimal and hexadecimal literals for integer types, the *L* (or *l*) suffix to specify *Longs* and how to force the exact type of an integer literal (by declaring its type). 
  + exponent notation for floats and the *F* (or *f*) and *D* (or *d*) to give the specific type.
  + escape sequences and Unicode specification of character literals.
  + raw strings and how to use them.
  + symbol literals and what they try to achieve (where you'd use an identifier in a dynamically typed language).
+ You're aware of the different string interpolators available in Scala, the *s interpolator*, the *raw string interpolator* and the *f string interpolator*.
+ You're aware that operators in Scala are just regular methods that are invoked using a special syntax that feels more natural for certain operations. You must know that methods can be overloaded and that there are different rules for *infix*, *prefix* and *postfix* operators. You're aware that only a bunch of characters can be used as *prefix* operators and must follow a strict naming rule (`unary_<char>`).
+ You're comfortable with arithmetic, relational and logical operations and bitwise operations
+ You're aware of how to check for object equality in Scala using `==` and `!=` and that Scala provides a special operator to check for *reference equality* (`eq` and `ne`).
+ You're aware of the operator precedence and associativity rules.
+ You know about the basic types rich wrappers, which provide additional methods on basic types.
---

## Projects

### [01 &mdash; Basic Types and Operations](./01-basic-types-and-operations-worksheet)
IntelliJ worksheet project with several worksheet illustrating the concepts of the section.

### [02 &mdash; Hello Scala Application](./02-hello-scala-application-sbt)
Simple SBT project that illustrates how to define an application entrypoint in Scala.

### [03 &mdash; Hello App Trait SBT] (./03-hello-app-trait-sbt)
Simple SBT project that illustrates how to define an application entrypoint in Scala by extending the `App` trait.