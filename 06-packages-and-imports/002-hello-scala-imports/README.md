# 002 &mdash; Hello, Scala Imports
> illustrating the flexibility of the `import` clause

## Description

In Scala, packages and their members can be imported using `import` clauses.

An import clause makes members of a package or object available by their names alone without needing to prefix them by the package or object name.
Scala supports the following *import* flavors:
```scala
package com.github.sergiofgonzalez.delights

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

// easy access to Fruit class
import com.github.sergiofgonzalez.delights.Fruit

// easy access to all members of the com.github.sergiofgonzalez.delights package
import com.github.sergiofgonzalez.delights._

// easy access to all members of Fruits object
import com.github.sergiofgonzalez.delights.Fruits._

// aliasing members of an object/variable
val fruit: Fruit = Pear
import fruit._ 
println(name) // <- pear
```

Also, Scala imports can appear anywhere in the code, and not only at the beginning of a compilation unit:

```scala
  def showFruit(fruit: Fruit) {
    /* import members of fruit parameter: name and color!!! */
    import fruit._
    println(s"${name}s are ${color}")
  }
```

You can also use a selector to bring into context only the objects you want to use:
```scala
import Fruits.{Apple, Orange}
```

You can also rename the objects that you bring into context:
```scala
// Apple is renamed as McIntosh
import Fruits.{ Apple => McIntosh, Orange}
```

Renaming can become very useful to be able to use classes with the same name without completely qualifying them:
```scala
import java.sql.{Date => SqlDate}
import java.Date
```

Alternatively, you could have renamed the package, to be able to do things like:
```scala
// alias java.sql as S
import java.{sql => S}

var mySqlDate: S.Date
```

The `_` can also be used when using renaming:
```scala
// import all members from Fruits, renaming Apple to McIntosh
import Fruits.{Apple => McIntosh, _}
```

And the following trick can be use to hide specific members:
```scala
// imports all members of Fruits except Pear
import Fruits.{Pear => _, _}
```

In summary, Scala's import clauses:
+ may appear anywhere in your source code
+ may refer to objects (singleton or regular) in addition to packages themselves
+ let you rename and hide some of the imported members

## Running the Application
Type `sbt` and then type:

```bash
sbt:ScalaImportsApp> run 
```

You can return to the command line just by typing: 
```bash
sbt:ScalaImportsApp> exit
```
