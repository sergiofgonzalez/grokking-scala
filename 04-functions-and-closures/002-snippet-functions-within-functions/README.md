# 002 &mdash; Functions within Functions (snippet)
> defining functions within functions (what is known as local functions) in classes to organize logic

## Description
Apart from declaring functions as `private` to hide them from consumers, Scala also allows to define *functions within functions*. Such *local functions* are just visible on their enclosing block.

```scala
object LongLines {
  def processFile(filename: String, width: Int) = {
    def processLine(filename: String, width: Int, line: String) = {
      if (line.length > width)
        println(s"$filename: ${line.trim}")
    }

    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(filename, width, line)
  }
}
```

Also, when you use local functions, those get access to the parameters of the enclosing function:
```scala
object LongLines {
  def processFile(filename: String, width: Int) = {
    def processLine(line: String) = {
      if (line.length > width)
        println(s"$filename: ${line.trim}")
    }

    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(line)
  }
}
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load LongLines.scala
```