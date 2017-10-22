# e03 &mdash; Reading lines from a file
> basic Scala program that read lines from a file

## Description
Illustrates how to write a program that read lines from a file and prints them out prepended with the number of characters in each line.

```
 6 | Hello
17 | This is your life
36 |It does not get any better than this
```

The final application is created in three steps.

### Step 1 - Classic imperative approach

Using `Source.fromFile`, which returns an iterator for the lines in the file it receive as argument, you can create the first version of the program.

```scala
import scala.io.Source

object PrintFileLinesApp extends App {

  for (line <- Source.fromFile(args(0)).getLines())
    println(s"[${line.length} | $line")
}
```

It has only a couple of problems:
+ It does not properly pad the length indicator
+ It's purely imperative (the program is only based on side effects) &mdash; Scala programs should prefer vals, immutable objects and methods without side effects.

### Step 2 - Migrating to a more functional approach

To solve the first problem detected on the previous approach, we define a function that will return the number of characters needed for the length indicator.
In order to be able to iterate through the lines twice, we also store the lines in a *List*, and use a for to compute the max number of characters that will be needed for the length indicator.

```scala
import scala.io.Source

object PrintFileLinesV1App extends App {

  def widthOfLineLength(s: String) = s.length.toString.length

  val fileLines = Source.fromFile(args(0)).getLines().toList

  var maxWidthOfLineLength = 0
  for (line <- fileLines) {
    maxWidthOfLineLength = maxWidthOfLineLength.max(widthOfLineLength(line))
  }

  for (line <- fileLines) {
    val numSpaces = maxWidthOfLineLength - widthOfLineLength(line)
    val padding = " " * numSpaces
    println(s"${ padding }${ line.length } | ${ line }")
  }
}
```
The only improvement opportunity is the loop that computes max length indicator, which can be converted in a reduce operation.

### Step 3 - Final approach

The final implementation uses a more functional approach, in which only the final printout is an imperative for, but the rest of the implementation is functional.

```scala
package example

import scala.io.Source

object PrintFileLinesV2App extends App {

  def widthOfLineLength(s: String) = s.length.toString.length

  val fileLines = Source.fromFile(args(0)).getLines().toList

  val longestLine = fileLines.reduceLeft((acc, line) => if (acc.length > line.length) acc else line)
  val maxWidthOfLineLength = widthOfLineLength(longestLine)

  for (line <- fileLines) {
    val numSpaces = maxWidthOfLineLength - widthOfLineLength(line)
    val padding = " " * numSpaces
    println(s"${ padding }${ line.length } | ${ line }")
  }
}
```


### Running the application
Type `sbt` and then type:

```bash
sbt:Hello> run                  # you'll get error and usage info
sbt:Hello> run README.md        # will print README.md lines
sbt:Hello> run Non-existing.txt # java.io.FileNotFoundException
```

You will be presented with a simple menu with the different versions of files you can run.

You can return to the command line just by typing: 
```bash
sbt:Hello> exit
```
