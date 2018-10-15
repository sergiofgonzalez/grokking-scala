# Part 2 &mdash; Scala In Depth: Packages and Imports
> writing modular code in Scala.

---
+ Basic Notions on Modularization
+ Packages in Scala: package nesting and access
+ Concise access to code in packages: the `_root_` package
+ Imports in Scala
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
    //          -> no need to do bobsrocket.navigation.Navigator
    val nav = new navigation.Navigator
  }
  package fleets {
    class Fleet {
      //                -> no need to do bobsrocket.Ship
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

---
## You know you've mastered this chapter when...

+ You understand that modularization is an important tool to reduce coupling into the different parts of a large program.
+ You're comfortable using the two available syntax flavors available in Scala to define packages: the Java one and the *C#* one. You understand that packages in Scala can be nested, so that you can end up with a file defining multiple namespaces. You're aware of the existence of the `_root_` package that comes in handy when some local package is shadowing a package in the top-level.
+ You're comfortable writing imports in Scala: you're aware that `_` brings all the elements of a given package into scope so that you don't have to qualify their names when using them. You're aware that you can use `import` clauses anywhere in a Scala file, and not just at the top of the compilation unit. You're also aware that you can even import an argument in a method, so that you don't need to qualify their fields.
---


## Projects

### [01 &mdash; Packages and Imports](./01-packages-and-imports-worksheet)
IntelliJ worksheet project with several worksheet illustrating the concepts of the section.

