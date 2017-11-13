# 001 &mdash; Hello, Scala Packages
> grokking the peculiarities of Scala's packaging system and its syntax

## Description
Scala code resides in the Java's global hierarchy of packages. If you don't specify a package for the code in your programs (or snippets) it will be placed in the *unnamed* package.

There are two ways to place code into named packages:
+ By placing a `package` clause at the top of the file.
+ By using the *packaging* syntax, consisting in enclosing a section in curly braces that contains the definition of the packages. 


```scala
// package-in-top.scala
package com.github.sergiofgonzalez

class MyScalaClass
```

```scala
// packaging-syntax-simple.scala
package com.github.sergiofgonzalez.domain {
  class MyScalaPerson
}
```

This former way of defining packages will allow you to structure the code in a single file in different packages:
```scala
// packaging-syntax-complex.scala
package com.github.sergiofgonzalez {
  
  package persistence {
    class PersonRepository

    package tests {
      class PersonRepositoryTests {
        def testSomeScenario() {
          // No need to explicitly import PersonRepository to instantiate it
          val p = new PersonRepository
        }
      }
    }
  }
}
```

Scala also allows you to use a variant of this packaging syntax without enclosing one package into another, however, the scope rules are different in this case:
```scala
package com.github.sergiofgonzalez.services {
  class PersonService
}

package com.github.sergiofgonzalez.services.rest {
  class PersonController {
    def doSomething() = {
      val personService = new PersonService // Err: PersonService is not in scope
    }
  }
}
```

As a final example, take a look at this piece of source code in which completely unrelated packages are all placed in the same file, with some package names hiding other existing packages:
```scala
package launch {
  class Booster3
}

package rockets {
  package navigation {
    package launch {
      class Booster1
    }
    class MissionControl {
      val booster1 = new launch.Booster1 // OK: enclosing packages (no need to refer to rockets.navigation.launch)
      val booster2 = new rockets.launch.Booster2 // OK: using complete path to refer to Booster2
      val booster3 = new _root_.launch.Booster3 // OK: need _root_ to start from unnamed package
    }
  }
  package launch {  // adding contents to existing package
      class Booster2
  }
}
```

In summary:
+ Java's *package clause* is still valid for Scala
+ Scala's allows for *packaging syntax* which helps with structuring code in enclosing *namespaces*
+ Scala's does not force you to place the package in their corresponding folder
+ Scala's packaging approach is clearly more flexible than Java's


## Running the Application
Type `sbt` and then type:

```bash
sbt:ScalaPackagesRunner> run 
```

You can return to the command line just by typing: 
```bash
sbt:ScalaPackagesRunner> exit
```
