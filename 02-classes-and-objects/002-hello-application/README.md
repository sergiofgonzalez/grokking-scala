# 002 &mdash; Hello, Scala application
> classes, fields and methods in Scala

## Description
A Scala application is a standalone singleton object with a method named `main` that takes one parameter of type `Array[String]` and has a result type `Unit`.

In the example, we create a standalone application named `Summer` which will compute the checksum for all the argument it receives from the command-line:

```scala
import ChecksumAccumulator.calculate

object Summer {
  def main(args: Array[String]): Unit = {
    for (arg <- args)
      println(s"$arg: ${calculate(arg)}")
  }
}
```

It must be noted that in Scala you're not required to name the file after the class it contains. However, it is a good practice to do that.

## Running the Application
Type `sbt` and then type:

```bash
sbt:Hello> run one two three
```

You can return to the command line just by typing: 
```bash
sbt:Hello> exit
```
