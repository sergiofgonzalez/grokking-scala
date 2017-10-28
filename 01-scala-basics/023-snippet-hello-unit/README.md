# 023 &mdash; Hello, Unit (snippet)
> the `Unit` result type

## Description
In Scala, a function or construct that does not result any interesting type is said to return a `Unit`.

In reality, there's a value (and only one value) whose type is `Unit` and is written `()`.

```scala
def greetMe(name: String) = println(s"Hey there, $name!")

() == greetMe("Idris") // true
```

Another thing to take into account is that:
```scala
var line = ""
while ((line = readLine()) != "")  // this will never end
  println(s"Read: $line")
```

does not work in Scala because in Scala, assignment such as `line = readLine()` returns a `Unit` and therefore, `Unit == String` will never be true.


## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load hello-unit.scala
```