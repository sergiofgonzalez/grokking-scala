# 008 &mdash; Match expressions (snippet)
> match expressions

## Description
Scala's `match` expression lets you select from a number of alternatives.
```scala
firstArg match {
  case "salt" => println("pepper")
  case "chips" => println("salsa")
  case "eggs" => println("bacon")
  case _ => println("huh?")
}
```

Note that:
+ *match expression* can be used with all kind of types, not just integers and strings
+ there are no `break` at the end of each alternative
+ the *match expression* result in a value (although that is not used in the previous example)
```scala
val side = firstArg match {
  case "salt" => "pepper"
  case "chips" => "salsa"
  case "eggs" => "bacon"
  case _ => "huh?"
}

println(side)
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load match-expressions.scala
```