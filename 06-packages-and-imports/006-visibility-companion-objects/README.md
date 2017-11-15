# 006 &mdash; Visibility Rules in Companion Objects
> visibility ryles for companion objects

## Description

In Scala there are no static members and instead you have the *companion object* that contains members that exist only once.
Scala's access rules privilege companion objects and classes when it comes to private or protected accesses, so that an object can access all private members of its companion class, and a class can access all private members of its companion object.

```scala
class Rocket {
  import Rocket.fuel  // refers to the companion object
  private def canGoHomeAgain = fuel > 20
}

object Rocket {
  private def fuel = 10

  def chooseStrategy(rocket: Rocket) = {
    if (rocket.canGoHomeAgain) // refers to class
      goHome()
    else
      pickAStar()
  }

  def goHome() = {}
  def pickAStar() = {}
}
```

## Running the Application
Type `sbt` and then type:

```bash
sbt:ScalaCompanionObjVisibilityApp> run 
```

You can return to the command line just by typing: 
```bash
sbt:ScalaCompanionObjVisibilityApp> exit
```
