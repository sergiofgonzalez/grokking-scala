# Part 2 &mdash; Scala In Depth: Packages and Imports
> writing modular code in Scala.

---
+ Basic Notions on Modularization
+ Packages in Scala: package nesting and access
+ Concise access to code in packages: the `_root_` package
+ Imports in Scala
+ Access Modifiers in Scala: scope of protection
+ The package object
---

## Intro
When working with large programs, it's important to minimize coupling &mdash; the extent to which the various parts of the program rely on the other parts. *Low coupling* ensures that a change in one part of the program will have devastating consequences (in terms of changes) in another part of the program.

One way to minimize coupling is through modularization &mdash; dividing the program in smaller parts with a clear differentiation between its interface and its implementation.

This sections deals with modularization in Scala: how to place things in packages, how to make things visible through imports and how to control the visibility of definitions through access modifiers.

## Putting Code in Packages
Scala code resides in the Java platform's global hierarchy of packages. Until now, all of our examples have been defined in the *unnamed package*.

Scala allows you to place code in packages in two ways: first, you can place the contents of an entire file into a package clause at the top of the file:

```scala
package bobsrocket.navigation
class Navigator
```

When defining a package, it is recommended to follow Java's naming convention of defining packages (reverse domain-name).

The other way is the *C#* style, in which you use a package clause and curly braces. This syntax is called *packaging*:

```scala
package bobsrocket.navigation {
  class Navigator
}
```


This syntax also allows nesting of packages, and may come in handy when putting several parts of the same file in different packages (e.g. placing implementation and tests in the same file, but putting the tests in a different package):

```scala
package bobsrocket {
  package navigation {
    class Navigator

    package tests {
      class NavigatorTestSuite
    }
  }
}
```

This way lets you define multiple packages in a single file.

## Concise Access to Related Code
When code is divided into a package hierarchy, it doesn't just help people browsing through the code. It also tells the compiler that the code in the same package is also related in some way. This allows Scala to implement short, unqualified names when accessing code that is in the same package.

Let's see it with examples:

```scala
package bobsrocket {
  package navigation {
    class Navigator {
      //          -> no need to do bobsrocket.navigation.StarMap
      val map = new StarMap
    }
    class StarMap
  }
  class Ship {
    //            -> no need to do bobsrocket.navigation.Navigator
    val nav = new navigation.Navigator
  }
  package fleets {
    class Fleet {
      //          -> no need to do bobsrocket.Ship
      def addShip = { new Ship }
    }
  }
}
```

Note that:
+ a class can be accessed from within its own package without needing a prefix (that's why `new StarMap` does not need qualification).
+ a package itself can be accessed from its containing package without needing a prefix (that's why `new navigation.Navigator` does not need `bobsrocket` in the qualifier).
+ when using the curly braces syntax, all names accesible in scopes outside the packaging are also available inside it (that's why `new Ship` does not need qualifier, even when it is defining inside a different containing package).


These features are only available if you nest packages. If you stick to Java's style and define one package per file, the only names available will be the ones defined in the current package. For example, the following piece of code will fail, as `bobsrocket.fleets` package has been moved to the top level instead of being nested within `bobsrocket`:

```scala
package bobsrocket {
  class Ship
}

package bobsrocket.fleets {
  class Fleet {
    def addShip = { new Ship } // Err: Ship not in scope
  }
}
```

Scala allows you to nest packages without using curly braces following this style:

```scala
package bobsrocket
class Ship

package fleets
class Fleet {
  def addShip() = { new Ship }
}
```

Another interesting topic shows up when you're coding in a heavily crowded scope where package names are hiding each other. In the following example, the scope of `MissionControl` includes three separate packages named `launch`.

```scala
// file: launch.scala
package launch {
  class Booster3
}

// file: bobsrocket.scala
package bobsrocket {
  package navigation {
    package launch {
      class Booster1
    }
    class MissionControl {
      val booster1 = new launch.Booster1
      val booster2 = new bobsrocket.launch.Booster2
      val booster3 = new _root_.launch.Booster3
    }
  }
  package launch {
    class Booster2
  }
}
```

+ Accessing the first one is easy &mdash; a reference to `launch` by itself will let you use `Booster1`, because it is defined in the closest enclosing scope.
+ Accessing the second one requires you to qualify the top level package in the file.
+ However, accessing `Booster3` is tricky because it is defined on a package that shadows one already available in the file on which `MissionControl` is defined. In these situations, Scala provides the `_root_` package notation. The package `_root_` is defined so that every top-level package is treated as a member of that package. As a result, `_root_.launch` gives you a reference of the top-level `launch` package.

## Imports
In Scala, packages and their member can be imported using *import clauses*. Imported items can then be accessed by a simple name, such as `File` instead of `java.io.File`.

Consider the following example:
```scala
package bobsdelights

abstract class Fruit(
  val name: String,
  val color: String
)

object Fruits {
  object Apple extends Fruit("apple", "red")
  object Orange extends Fruit("orange", "orange")
  object Pear extends Fruit("pear", "yellowish")

  val menu = List(Apple, Orange, Pear)
}
```

An import clause makes members of a package or object available by their names alone without needing to prefix them by the package or object name:

```scala
// easy access to Fruit
import bobsdelights.Fruit

// easy access to all members of bobsdelights
import bobsdelights._

// easy access to all members of bobsdelights.Fruits
import bobsdelights.Fruits._
```

Note that `_` is used in the same way as `*` in Java imports.

Scala imports can appear anywhere in the file, not just at the beginning of a compilation unit. Also, they can refer to arbitrary values:

```scala
def showFruit(fruit: Fruit) = {
  import fruit._
  println(s"${name}s are ${color}")
}
```

Method `showFruit` imports all members of its parameter fruit, which is of type `Fruit`. Thus, the subsequent `println` statement can refer to name and color directly, so that `name` refers to `fruit.name` and `color` to `fruit.color`.

### Scala's Flexible Imports vs. Java's
The principal differences between Scala's and Java imports are:
+ may appear anywhere
+ may refer to objects in addition to packages
+ renaming and hiding some of the imported members is allowed

Also, in Scala, *imports* can import packages themselves, not just their non-package members.

For example, importing `java.util.regex` will let you use regex as a simple name:
```scala
import java.util.regex

class A {
  val pat = regex.Pattern.compile("a*b")
}
```

Imports in Scala can also rename or hide members. This is done with an import selector clause enclosed in braces, which follows the object from which members are imported.

An import selector can consist of the following:
+ a simple name *x*. This includes *x* in the set of imported names.
+ a renaming clause *x => y*. This makes the member named x visible under the name y.
+ a hiding clause *x => _*. This excludes x from the set of imported names.
+ a catch-all *_*. This imports all members except those members mentioned in a preceding clause. If a *catch-all* is given, it must come last in the list of import selectors.


Here we have several examples.


```scala
// imports members Apple and Orange 
import Fruits.{Apple, Orange}

// imports Apple and Orange, renaming Apple to McIntosh
import Fruits.{Apple => McIntosh, Orange}

// imports java.sql.Date as SqlDate (so that you can use both Date and java.sql.Date without having to qualify one)
import java.sql.{Date => SqlDate}

// imports java.sql package as Sql
import java.{sql => Sql}

// imports all members from Fruits, same as import Fruits._
import Fruits.{_}

// imports all members from Fruits, renaming Apple to McIntosh
import Fruits.{Apple => McIntosh, _}

// imports all members of Fruits except Pear.
import Fruits.{Pear => _, _}
```

Note also that the syntax `import p._` is equivalent to `import p.{_}`, and `import p.n` is equivalen to `import p.{n}`.

## Implicit Imports
Scala adds some imports implicitly to every program:

```scala
import java.lang._    
import scala._        // Standard Scala lib
import Predef._       // everything from Predef object
```

The `Predef` object contains many definitions of types, methods and implicit conversions that are commonly used on Scala programs.

Note that for these implicit imports, whatever is defined later takes precedence over what was defined

## Access Modifiers
Members of packages, classes or objects can be labeled with the access modifiers `private` and `protected`. These modifiers restrict access to the members to certain regions of code.

### Private Members
A member labeled `private` is visible only inside the class or object that contains the member definition. In Scala, this rule applies also for inner classes.

```scala
class Outer {
  class Inner {
    private def f() = { println("f") }
    class InnerMost {
      f() // OK
    }
  }
  (new Inner).f() // error: f is not accessible
}
```

### Protected Members
Protected members in Scala are only accessible from subclasses of the class in which the member is defined.

```scala
package p {
  class Super {
    protected def f() = { println("f") }
  }
  class Sub extends Super {
    f() // OK
  }
  class Other {
    (new Super).f() // error: f is not accessible
  }
}
```

### Public Members
Scala has no explicit modifier for public members: Any member not labeled private or protected is public. Public members can be accessed from anywhere.

### Scope of Protection
Access modifiers in Scala can be augmented with qualifiers. A modifier of the form `private[X]` or `protected[X]` means that access is private or protected *"up to X"*, where *X* designates some enclosing package, class or singleton object.

Qualified access modifiers give you very fine-grained control over visibility. In particular, they enable you to express Java's accesibility notions, such as package private, package protected, or private up to outermost class, which are not directly expressible with simple modifiers in Scala, while they also let you express accessibiltiy rules that cannot be expressed in Java.

Consider the following listing:

```scala
package bobsrockets

package navigation {
  private[bobsrockets] class Navigator {
    protected[navigation] def useStarChart() = {}
    class LegOfJourney {
      private[Navigator] val distance = 100
    }
    private[this] var speed = 200
  }
}
package launch {
  import navigation._
  object Vehicle {
    private[launch] val guide = new Navigator
  }
}
```

The class `Navigator` is labeled as `private[bobsrocket]`. This means that this class is visible in all classes and objects that are contained in package bobsrockets. In particular, the access to `Navigator` in `Vehicle` is permitted because package `launch` is contained in package `bobsrockets`. No code outside package `bobsrockets` will have access to class `Navigator`.

This technique is really useful if you want to define things that are visible in several sub-packages of your project but that remain hidden from clients external to your project.

Consider the following table, discussing the effect of *private* qualifiers on LegOfJourney.distance:

| modifier              | Result                             |
|-----------------------|------------------------------------|
| no access modifier    | public access                      |
| private[bobsrockets]  | access within outer package        |
| private[navigation]   | same as package visibility in Java |
| private[Navigator]    | same as private in Java            |
| private[LegOfJourney] | same as private in Scala           |
| private[this]         | access only from same object       |

All qualifiers can also be applied to protected, with the same meaning as private. That is, a modifier `protected[X]` in a class `C` allows access to the labeled definition in all subclasses of `C` and also within the enclosing package, class or object `X`.

Finally, Scala has an access modifier that is even more restrictive than `private`. A definition labeled `private[this]` is accessible only from within the same object that contains the definition. This is called *object-private* and in the example, this would mean that any access must occur from the very same instance of `Navigator`.

For example, this code would not be allowed, even when appearing inside the `Navigator` class:
```scala
val other = new Navigator
other.speed
```

This guarantees that it will not be seen from other objects of the same class.

### Visibility and Companion Objects
In Java, static members and instance members belong to the same class. In Scala, there are no static members, and instead you can have a companion object that contains members that exist only once.

Consider the following piece of code that shows a companion object and its class:

```scala
class Rocket {
  import Rocker.fuel
  private def canGoHomeAgain = fuel > 20
}

object Rocket {
  private def fuel = 10
  def chooseStrategy(rocket: Rocket) = {
    if (rocket.canGoHomeAgain)
      goHome()
    else
      pickAStar()
  }
  def goHome() = {}
  def pickAStar() = {}
}
```

Scala's access rules privilege companion objects and classes when it comes to private or protected accesses. A class shares all its access rights with its companion object and vice versa. In particular, an object can access all private members of its companion class, just as a class can access all private members of its companion object.

For instance, the `Rocket` class can access the `fuel` method, although is declared *private*. Analogously, the `Rocket` object can access the private method `canGoHomeAgain` in `Rocket`.

## Package Objects
The most common definitions found in packages are classes, traits and standalone objects. However, Scala also allows you to put there everything you can put inside a class. For example, if you have a helper method you'd like to put in scope for an entire package, go ahead and put it right at the top level of the package.

To do so, put the definitions in a *package object*. Each package is allowed to have one package object. Any definitions placed in a package object are considered members of the package itself.

Consider the following example:

```scala
// File: bobsdelights/package.scala
package object bobsdelights {
  def showFruit(fruit: Fruit) = {
    import fruit._
    println(s"${name}s are $color")
  }
}

// File: PrintMenu.scala
package printmenu

import bobsdelights.Fruits
import bobsdelights.showFruit

object PrintMenu {
  def main(args: Array[String]) = {
    for (fruit <- Fruits.menu)
      showFruit(fruit)
  }
}
```

The file `package.scala` holds a package object for `bobsdelight`. Syntactically, a package object looks much like one of the curly-braces packaging seen in the [Putting Code in Packages](#putting-code-in-packages) section. The only different is that it includes the *object* keyword &mdash; it's a package object, not a package.
Given that definition, any other code in any package can import the methods just as it would import a class.

*Package objects* are frequently used to hold package-wide type aliases and implicit conversions. The top-level scala package has a package object, and its definitions are available to all Scala code.

*Package objects* are compiled to class files named `package.class` that are located in the directory of the package they augment. It's usefult to keep the same convention for source files, as we did in the example above.

---
## You know you've mastered this chapter when...

+ You understand that modularization is an important tool to reduce coupling into the different parts of a large program.
+ You're comfortable using the two available syntax flavors available in Scala to define packages: the Java one and the *C#* one. You understand that packages in Scala can be nested, so that you can end up with a file defining multiple namespaces. You're aware of the existence of the `_root_` package that comes in handy when some local package is shadowing a package in the top-level.
+ You're comfortable writing imports in Scala: you're aware that `_` brings all the elements of a given package into scope so that you don't have to qualify their names when using them. You're aware that you can use `import` clauses anywhere in a Scala file, and not just at the top of the compilation unit. You're also aware that you can even import an argument in a method, so that you don't need to qualify their fields.
+ You're aware of the capabilities of the `import` clause. How you can import several members and packages at once with the *catch-all* syntax, and how to rename members.
+ You're aware of Scala's access modifiers. You're aware of the syntax for the *scope of protection* that allows for a finer-grained control of the visibility, as you can say that something is visible *up to* X, X being the class/package you use in the scope.
+ You're aware of the package object, and that you can use it to place package-wide type aliases and implicit conversions.
---


## Projects

### [01 &mdash; Packages and Imports](./01-packages-and-imports-worksheet)
IntelliJ worksheet project with several worksheet illustrating the concepts of the section.

