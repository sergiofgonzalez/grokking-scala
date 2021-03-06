# Part 2 &mdash; Scala In Depth: Functional Objects
> Designing objects in a functional way 

--- 
  + Specifying the primary constructor for a class: class parameters
  + Overriding methods from a superclass
  + Adding preconditions to a class
  + Defining Fields in a class
  + Self References: the `this` keyword
  + Specifying Auxiliary Constructors
  + Defining Private Fields and Methods
  + Creating Operators
  + The four types of identifiers in Scala
  + Using Method Overloading
  + Implicit Conversions mechanism
---

## Intro
In this section we will design classes that define *functional* objects, and object that do not have any *mutable* state. We'll delve into more aspects of object-oriented programming in Scala such as class parameters, constructors, methods and operators, private members, overriding, checking preconditions, overloading and self references.

In order to grasp all these concepts we will be creating several variants of a class that models rational numbers as immutable objects.

## Specification for the *Rational* class
A rational number is a number that can be expresses as a ratio *n/d*, where *n* (numerator) and *d* (denominator) are integers, and *d* cannot be zero. 

The class must model the behavior of these rational numbers, including support for addition, subtraction, multiplication and division:
+ To add two rationals, you must first obtain a common denominator, then add the two numerators. Same thing for subtraction. E.g. *1/2 + 2/3 = 3/6 + 4/6 = 7/6*
+ To multiply rationals, you multiply their numerators and denominators. E.g. *1/2 &ast; 2/5 = 2/10 = 1/5*
+ To divide rationals, you swap the numerator and denominator of the right operand and then multiply. E.g. *1/2 / 3/5 = 1/2 * 5/3 = 5/6*

In Maths, rational numbers *do not have a mutable state*: you can add one rational numer to another, but the result will be a new rational number. We'll design the *Rational* class in this way.

We expect the class to support the following usage:
```scala
scala> val oneHalf = new Rational(1, 2)
oneHalf: Rational = 1/2

scala> val twoThirds = new Rational(2, 3)
twoThirds: Rational = 2/3

scala> (oneHalf / 7) + (1 - twoThirds) = new Rational(1, 2)
res0: Rational = 17/42
```

## Constructing a Rational
As the Rational object will immutable, we'll require the clients to provide all the necessary data needed by the instance when they invoke the constrcutor:

```scala
class Rational(n: Int, d: Int)
```

Note that:
+ The class does not have a body, and because of that you can leave off the curly braces.
+ The class specification includes the identifies for *n* and *d*. These are called *class parameters*. Scala compiler will gather up these two *class parameters* and synthesize a *primary constructor* behind the scenes for you.

This highlights a difference between Java and Scala. In Java, classes have constructors that take parameters, whereas in Scala, classes can take parameters directly. You will see that those parameters can be used directly in the body of the class without having to defined them as in Java. Thus, you can do:
```scala
class Rational(n: Int, d: Int) {
  println(s"Created $n/$d")
}
```

| Immutable Object Pros and Cons |
|--------------------------------|
| Immutable objects are often easier to reason about, as there is no complex state that you should be aware of when using it. As a consequence, you can pass immutable objects around quite freely expecting on side-effects. Also, mutable objects are *threadsafe*, so you don't have to worry to synchronize access to mutable objects.<br>However, immutable objects might be the reason behind performance bottlenecks when a large object graph needs to be copied (just imagine a simple cache implementation using an immutable map &mdash; each time you add a new item to the cache, you'll be force to copy all the preexisting elements!). That is why some libraries provide both mutable and immutable versions of classes (e.g. *Map*).

## Reimplementing `toString` method
With the previous section implementation, we obtained the following message in the console when we create an instance of the `Rational` class:

```scala
val oneHalf = new Rational(1, 2)
oneHalf: Rational = Rational@32bd4659
```
Note how this *Rational@32bd4659* is less that ideal.

By default, a class inherits the implemention of `toString` from `java.lang.Object`, which is not especially useful in this case. We can override this default inherited implementation using the following syntax:
```scala
class Rational(n: Int, d: Int) {
  override def toString: String = s"$n/$d"
}

val oneHalf = new Rational(1, 2)
oneHalf: Rational = 1/2
```

## Adding Preconditions
As per the specifications for the `Rational` class, the denominator cannot be *zero*. Therefore, we should ensure that a `Rational` cannot be constructed if *zero* is passed as a parameter. This can be done in Scala by adding a precondition &mdash; a constraint on values passed into a method or constructor that the caller must fulfill.

One way to do it is to add a `require()` in to the class's body (which will end up being synthesized into the class primary constructor):

```scala
class Rational(n: Int, d: Int) {
  require(d != 0)
  override def toString: String = s"$n/$d"
}

val oneHalf = new Rational(1, 2)
oneHalf: Rational = 1/2

new Rational(5, 0)
java.lang.IllegalArgumentException: requirement failed
...
```

The `require` method takes one boolean parameter: If the passed value is true, require will return normally, otherwise, it will throw an `IllegalArgumentException`.

## Adding Fields
In this section, we will create the `add` method that implements addition of *rational* numbers.

Initially, you might be tempted to write something like:
```scala

class Rational(n: Int, d: Int) {
  require(d != 0)
  override def toString: String = s"$n/$d"
  def add(other: Rational): Rational = new Rational(n * other.d + other.n * d, d * other.d)
}
```

but unfortunately, that does not compile because `other.n` and `other.d` are unaccessible from the method (are private).

In order to access them, we need to modify the class definition in order to define the numerator and denominator as class fields:

```scala
class Rational(n: Int, d: Int) {
  require(d != 0)
  val numer: Int = n
  val denom: Int = d
  override def toString: String = s"$n/$d"
  def add(other: Rational): Rational = new Rational(n * other.denom + other.numer * d, d * other.denom)
}

val oneHalf = new Rational(1, 2)
val twoThirds = new Rational(2, 3)

oneHalf.add(twoThirds) 
res0: Rational = 7/6

// using operator syntax
oneHalf add twoThirds
res1: Rational = 7/6
```

Note how we have defined two fields `numer` and `denom` and have initialized them with the values of the class parameters. Note that, as a consequence, we can now access the *numerator* and *denominator*.

```scala
val n = oneHalf.numer     // -> 1
val m = twoThirds.denom   // -> 3
```

## Self references
The keyword `this` can be used to refer to the object instance on which the currently executing method was invoked, or if invoked in a constructor, the object instance being constructed.
For example, the `lessThan` method can be programmed as:
```scala
class Rational(n: Int, d: Int) {
  require(d != 0)
  val numer: Int = n
  val denom: Int = d
  override def toString: String = s"$n/$d"
  def add(other: Rational): Rational = new Rational(n * other.denom + other.numer * d, d * other.denom)
  def lessThan(other: Rational): Boolean = this.numer * other.denom < other.numer * this.denom
}

val oneThird = new Rational(1, 3)
val twoFifths = new Rational(2, 5)

oneThird lessThan twoFifths   // <- true
twoFifths lessThan oneThird   // <- false
twoFifths lessThan twoFifths  // <- false
```

Note that in this case, it would have been also possible to leave off `this`:
```scala
  def lessThan(other: Rational): Boolean = numer * other.denom < other.numer * denom
```

But there are cases on which leaving off `this` is not possible
```scala
  def max(other: Rational): Rational = if (this lessThan other) other else this
```

## Auxiliary Constructors
In Scala, constructors other than the primary are called *auxiliary constructors*. For example, a rational number with a denominator of *1* can be written more succinctly as integers (`5/1 = 5`). Thus, it will be nice to have a `Rational(5)` for this purpose:

```scala
class Rational(n: Int, d: Int) {
  // precondition
  require(d != 0)

  // fields
  val numer: Int = n
  val denom: Int = d

  // auxiliary constructors
  def this(n: Int) = this(n, 1)

  // toString override
  override def toString: String = s"$n/$d"

  // methods
  def add(other: Rational): Rational = new Rational(n * other.denom + other.numer * d, d * other.denom)
  def lessThan(other: Rational): Boolean = this.numer * other.denom < other.numer * this.denom
  def max(other: Rational): Rational = if (this lessThan other) other else this
}
```

Auxiliary constructors in Scala start with `def this(...)`. In the previous case, the body of the constructor merely invokes the primary constructor with the *denominator* set to 1.

In Scala, auxiliary constructors must invoke another constructor of the same class as its first action. Therefore, the following auxiliary constructor body is not allowed:
```scala  
  def this(n: Int) = new Rational(n, 1) // not allowed: `this` expected

  def this(n: Int) = {
    required(n > 5) // not allowed, `this` expected
    this(n)
  }
```

The invoked constructor must be either the primary constructor, or another auxiliary constructor defined textually before the calling constructor. Note that this eventually makes the primary constructor the single point of entry of a class. As an additional restriction, only the class primary constructor can invoke the constructor of the superclass.

## Private Fields and Methods
Up until now, our *Rational* class does not support normalization and therefore, our class is not aware that *4/6* can be also written as *2/3*.

To enhace the class we need to add method that computes the greatest common divisor, and use that number to divide both the numerator and denominator.

```scala

class Rational(n: Int, d: Int) {
  // precondition
  require(d != 0)

  // fields
  private val g = gcd(n.abs, d.abs)
  val numer: Int = n / g
  val denom: Int = d / g

  // auxiliary constructors
  def this(n: Int) = this(n, 1)

  // toString override
  override def toString: String = s"$numer/$denom"

  // methods
  def add(other: Rational): Rational = new Rational(numer * other.denom + other.numer * denom, denom * other.denom)
  def lessThan(other: Rational): Boolean = this.numer * other.denom < other.numer * this.denom
  def max(other: Rational): Rational = if (this lessThan other) other else this

  // private methods
  private def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else gcd(b, a % b)
  }
}


val fourSixths = new Rational(4, 6) 
fourSixths: Rational = 2/3

val r = new Rational(66, 42)
r: Rational = 11/7
```

Note that we've added a private method `gcd` that computes the *greater common divisor* recursively. Then, we also add a private field that holds this number so that it can be reused without having to call the method again, and without exposing this value to the consumers of the class.

Then we use that value to normalize the `numer` and `denom` fields, and modify a little bit the rest of the methods to use those fields instead of the class parameters.

## Defining Operators
Now we're going to rename the methods on *Rational* to be able to use them as operators in a more natural way.

As `+` is a legal method name in Scala, we can simply rename the existing `add` method. In the same step, we will also define the `*` method.

```scala
class Rational(n: Int, d: Int) {
  // precondition
  require(d != 0)

  // fields
  private val g = gcd(n.abs, d.abs)
  val numer: Int = n / g
  val denom: Int = d / g

  // auxiliary constructors
  def this(n: Int) = this(n, 1)

  // toString override
  override def toString: String = s"$numer/$denom"

  // methods
  def +(other: Rational): Rational = new Rational(numer * other.denom + other.numer * denom, denom * other.denom)
  def *(other: Rational): Rational = new Rational(numer * other.numer, denom * other.denom)
  def lessThan(other: Rational): Boolean = this.numer * other.denom < other.numer * this.denom
  def max(other: Rational): Rational = if (this lessThan other) other else this

  // private methods
  private def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else gcd(b, a % b)
  }
}


val oneHalf = new Rational(1, 2)
oneHalf: Rational = 1/2

val twoThirds = new Rational(2, 3)
twoThirds: Rational = 2/3

oneHalf + twoThirds
res0: Rational = 7/6

oneHalf * twoThirds
res1: Rational = 1/3  // <- normalized for free by construction
```

Note also that, because of Scala's rules of precedence (see [Operator Precedence and Associativity](../02-basic-types-and-operations/README.md#operator-precedence-and-associativity)), as `*` has higher precedence than `+` the following operations will execute as expected:

```scala
oneHalf + oneHalf * twoThirds
res2: Rational = 5/6

oneHalf + (oneHalf * twoThirds)
res3: Rational = 5/6

(oneHalf + oneHalf) * twoThirds
res4: Rational = 2/3
```

## Identifiers in Scala
Scala has very flexible rules for forming identifiers. There are namely four types of identifiers:
+ alphanumeric identifiers
+ operator identifiers
+ constant identifiers
+ literal identifiers

An alphanumeric identifier starts with a letter or an underscore, followed by further letters, digits or underscored. The `$` is considered a letter, but it is reserved for identifiers used by the Scala compiler and should not be used.

Scala follows Java's convention for identifiers (camel-case, fields, methods, functions, local variables and method parameters starting with lowercase as in *toString*, classes and traits starting with capital letters as *HashSet*). Although underscores are legal identifiers, they are not typically used in Scala programs.

In Scala the word `constant` is not the same as `val`. Even though a val remains constant after it is initialized, it is still a variable. A `constant` is more permanent than `val`. For example, `scala.math.Pi` is a constant. Constants can also be used for *magic numbers* in your code. Constants can also be used in *pattern matching*. In Scala, the naming convention for constants is to make the first character upper case (thus a constant in Java named MAX_VALUE will be named MaxValue).

An operator identifier consists of one or more operator characters (printable ASCII characters such as *+*, *:*, *?*, *~* or *#*). Examples are `+`, `++`, `:::`, `<?>`. The Scala compiler will transform the operator identifiers to make them run on the JVM.

| A word of caution with operator identifiers interpretation |
|------------------------------------------------------------|
| The flexibility that Scala adds to  operator identifiers make that some expressions such as:<br>`x<-y` might be parsed incorrectly, because it will try to look for an operator identifier named `<-` while in reality you might be trying to compare `x` and `-y`.<br>In those circumstances, you have to insert spaces as in `x < -y` or use parentheses.|

A literal identifier is an arbitrary string enclosed in backticks as *&#96;yield&#96;*, *&#96;<clinit>&#96;* and *&#96;x&#96;*. The idea is that you can put any string that's accepted by the runtime as an identifier between back ticks. The result is always a Scala identifier. This works even if the name container in the back ticks would be a Scala reserved word. For example, as *yield* is a reserved word in a Scala you cannot name a method `MyClass.yield()` but you could be able to use `MyClass.*&#96;yield()*&#96;*`.

## Method Overloading
In this section, we will introduce method overloading in our *Rational* class to be able to do mixed arithmetic. That is, doing operations with a *Rational* and a number of other type (i.e. *Int*).

```scala
class Rational(n: Int, d: Int) {
  // precondition
  require(d != 0)

  // fields
  private val g = gcd(n.abs, d.abs)
  val numer: Int = n / g
  val denom: Int = d / g

  // auxiliary constructors
  def this(n: Int) = this(n, 1)

  // toString override
  override def toString: String = s"$numer/$denom"

  // methods
  def +(other: Rational): Rational = new Rational(numer * other.denom + other.numer * denom, denom * other.denom)
  def +(other: Int): Rational = new Rational(numer + other * denom, denom)

  def -(other: Rational): Rational = new Rational(numer * other.denom - other.numer * denom, denom * other.denom)
  def -(other: Int): Rational = new Rational(numer - other * denom, denom)

  def *(other: Rational): Rational = new Rational(numer * other.numer, denom * other.denom)
  def *(other: Int): Rational = new Rational(numer * other, denom)

  def /(other: Rational): Rational = new Rational(numer * other.denom, denom * other.numer)
  def /(other: Int): Rational = new Rational(numer, denom * other)

  // private methods
  private def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else gcd(b, a % b)
  }
}
```

Now the arithmetic operators are overloaded, meaning that each name is now being used by multiple methods. When that happens, the compiler chooses the method that correctly matches the types of the arguments. When using the operator syntax, this is decided by the type of the right hand operator.

## Implicit Conversions
Note that if you try to do:

```scala
scala> 2 * oneHalf
Error:(58, 70) overloaded method value * with alternatives:
  (x: Double)Double <and>
  (x: Float)Float <and>
  (x: Long)Long <and>
  (x: Int)Int <and>
  (x: Char)Int <and>
  (x: Short)Int <and>
  (x: Byte)Int
 cannot be applied to (A$A4.this.Rational)
def get$$instance$$res6 = /* ###worksheet### generated $$end$$ */ 2 * oneHalf
                                                                    ^
```

In the method resolution, the Scala compiler cannot find a method `*` in the `Int` class that can be applied to a `Rational` and thus it fails.

In order to solve this, Scala provides a mechanism known as *implicit conversion* that can be used to tell the system how to convert from an `Int` to a `Rational`.

```scala
implicit def intToRational(x: Int): Rational = new Rational(x)

val twoThirds = new Rational(2, 3)
2 * twoThirds // -> 4 / 3
```

For an implicit conversion to work, it needs to be in scope. If you place that definition inside the `Rational` class definition it won't be in scope for the interpreter. In a future part, we'll deal with techniques that will let you bring them into scope properly.

| A word of caution on operators and implicit conversions    |
|------------------------------------------------------------|
| The use of operator methods and implicit conversions can give rise to client code that is hard to read and understand. The goal to keep in mind when designing libraries is not merely enabling concise client code, but readable, understandable client code. Conciseness can be a big part of readability, but when taken too far it can be counterproductive. |

---
## You know you've mastered this chapter when...

+ You're comfortable with the syntax for a class primary constructor, and how you include code directly into the class's body.
+ You're comfortable using the syntax that allows you to override methods defined in super classes.
+ You know how to include preconditions in a class's body to be executed during instance construction.
+ You're comfortable adding (*public*) fields in a class.
+ You know how to use `this` keyword in Scala to reference the current object instance.
+ You're comfortable defining auxiliary constructors in a class, and you're aware on the specific rules for auxiliary constructors in Scala (they must invoke another constructor of the same class as its first action).
+ You know to how to add private fields and methods to a class.
+ You're comfortable defining *operators* in a class.
+ You're aware of the four types of identifiers in Scala: alphanumeric identifiers, operator identifiers, constant identifiers and literal identifiers.
+ You're comfortable with method overloading mechanism in Scala.
+ You're aware of the implicit conversion mechanism in Scala, that allows you to convert automatically between types.
---

## Projects

### [01 &mdash; Functional Objects Worksheet](./01-functional-objects-worksheet)
IntelliJ worksheet project with several worksheet illustrating the concepts of the section.
