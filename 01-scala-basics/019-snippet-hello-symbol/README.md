# 019 &mdash; Hello, Symbol literals (snippet)
> illustrates the concept of Symbol literals

## Description
*Symbol literals* are used in situations where you would use an identifier in a dynamically typed language.
A *Symbol literal* is written `'<the-symbol>` where *<the-symbol>* is any alphanumeric identifier. Such literals will be mapped to instances of the predefined class `scala.Symbol`.

There is not much you can do with a symbol, except find out its name:

```scala
val s = 'aSymbol

val symbolName = s.name
```

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load hello-symbol.scala
```