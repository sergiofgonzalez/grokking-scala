# 010 &mdash; More pattern matching (snippet)
> more involved examples of pattern matching

## Description
The snippets illustrates more involved examples about pattern matching than the one found in [009 &mdash; Hello, pattern matching](../009-hello-pattern-matching).

### Example 1: Pattern matching with types
You can use the same syntax `n match` found in the [simple example](../009-hello-pattern-matching) to match variable types:
```scala
def printType(obj: AnyRef) = obj match {
  case s: String => println(s"This is a String: $s")
  case l: List[_] => println(s"This is a List: $l")
  case a: Array[_] => println(s"This is an Array: ${ a.mkString(",") }")
  case d: java.util.Date => println(s"This is a Date <3")
}
```

Note that in this case, you have to use a variable that will be bound to the value matched.

### Example 2: Pattern matching as an infix operator
You can use `match` as an infix operator as in this example, that filters out the elements of a list beyond the 2nd 
```scala
List(1, 2, 3) match {
  case first :: second :: rest => List(first, second)
  case _ => {
    println("fallback option")
    Nil
  }
}
```

### Example 3: Using guards in pattern matching
You can include guards that will act as the matching rule for the case: 

```scala
def rangeMatcher(n: Int) = n match {
  case within10 if (within10 <= 10) => println(s"$n is within 0 to 10")
  case within100 if (within100 <= 100) => println(s"$n is within 11 to 100")
  case beyond100 if (beyond100 < Int.MaxValue) => println(s"$n is greater than 100")
}
```

### Example 4: A better ordinal implementation
Using guards and Scala ranges (see [e01 &mdash; Hello, ranges (snippet)](.../e01-hello-ranges)) an implementation of a function that maps a number with its ordinal is created.
```scala
val suffixes = List("th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th")

def ordinal(n: Int) = n match {
  case tenTo20 if 10 to 20 contains tenTo20 => s"${tenTo20}th"  // 10th, 11th, 12th, 13th... 20th
  case rest => s"${ rest }${ suffixes( rest % 10 ) }"
}
```

Note that in this case, instead of using the placeholder `_` we use a variable and we name it `rest`. This is not reserved keyword, and I could have used `case whatever => s"${ whatever }${ suffixes( whatever % 10 ) }"` for the same effect.


## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load more-pattern-matching.scala
```