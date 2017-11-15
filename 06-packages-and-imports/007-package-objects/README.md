# 007 &mdash; Package Objects
> visibility ryles for companion objects

## Description

Typically, you will add classes, traits and standalone objects to packages. However, Scala also allows you to add any kind of definition that you would put inside a class at the top level of a package:
```scala
package object delights { // package object, not a package
  def showFruit(fruit: Fruit) = {
    import fruit._
    println(s"${name}s are $color")
  }
}
```

In the previous code, you see how a helper method is placed in a *package object* and it becomes a member of the package itself (in scope for the entire package). Any other code in any package can import the method just like it would import a class:
```scala
import delights.showFruit
```

Each package is allowed to have one package object.

*Package objects* are commonly used to hold package-wide type aliases and implicit conversions.

## Running the Application
Type `sbt` and then type:

```bash
sbt:PackageObjectsApp> run 
```

You can return to the command line just by typing: 
```bash
sbt:PackageObjectsApp> exit
```
