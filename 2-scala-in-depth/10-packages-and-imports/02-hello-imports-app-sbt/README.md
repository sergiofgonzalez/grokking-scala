# 02 &mdash; Hello Imports! Application SBT   
> Scala application project demonstrating some basic concepts about packages and imports

A really simple Scala application using SBT that features a package `bobsdelight` with the following contents:

```scala
package bobsdelight;

abstract class Fruit(
  val name: String,
  val color: String)

object Fruits {
  object Apple extends Fruit("apple", "red")
  object Orange extends Fruit(name = "orange", "orange")
  object Pear extends Fruit("pear", "yellowish")
}
```

That is, we have an abstract class with three objects defined on it.

Then, in the application we use the following code to demonstrate some basic concepts about imports:

```scala
import bobsdelight.Fruit
import bobsdelight.Fruits._

object FruitsApp extends App {

  showFruit(Apple)

  def showFruit(fruit: Fruit) = {
    import fruit._
    println(s"${name}s are ${color}")
  }
}
```

In the first two lines we import the `Fruit` class and the `Fruits` object instances, so that we can do `showFruit(Apple)` without requiring any qualification.
Then, in the `showFruit` class we demonstrate how you can even use `import fruit._` which will let you use the properties of the object without any need to qualify them.

## Running the Project
Import the project and either:
+ right-click on the `FruitsApp` class and select `Run FruitsApp`. Note that sometimes that option does not trigger the proper recompilation of the project so you might get an error telling you that the class was not found.
+ Configure a new *sbt task* with a name of your choice and the action `~run` which will cause configure like a *watch* task that will look for changes in your project and run the application when those are detected.