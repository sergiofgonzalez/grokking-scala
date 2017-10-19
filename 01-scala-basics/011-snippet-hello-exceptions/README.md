# 011 &mdash; Hello, Exceptions (snippet)
> introducing exception handling in Scala

## Description
In Scala, you throw exceptions as in *Java*:
```scala
def classify(n: Int) = n match {
  case group1 if (n >= 0 && n <= 100) => println(s"$n is a number between 0 and 100")
  case group2 if (n >= 101 && n <= 1000) => println(s"$n is a number between 101 and 1000")
  case _ => throw new IllegalArgumentException(s"$n cannot be classified")  // <- throwing!!!
}
```
and you catch them using a single try-catch block that includes pattern matching:
```scala
try {
  classify(5555)
} catch {
  case e : IllegalArgumentException => s"Error calling classify: ${ e.getMessage }"
  case _ : => "Unexpected exception caught" 
}

```



## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load more-pattern-matching.scala
```