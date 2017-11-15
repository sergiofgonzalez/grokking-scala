# 001 &mdash; Hello, Assert and Ensure (snippet)
> introducing the basics for asserting in Scala: `assert` and `ensure`

## Description

Scala provides two methods to perform assertions: `assert` and `ensure`.

The `assert(condition, [explanation])` method throws an `AssertionError` if the condition does not hold. The *explanation* is an optional object that when given, will be used in the message of the assertion error. The type of this second parameter is `Any`:
```scala
def checkGreaterThan10(num: Int) {
  assert(num > 10)
  /* ... do some awesome things with num */
}
```

The `ensure` method defined in the `Predef` object takes a predicate function that takes a result type and returns `Boolean`. If the predicate returns true, ensuring will return the result, otherwise it will throw an `AssertionError`:
```scala
def getGreeting(name: String) = {
  if (name == "Jason Isaacs") {
    s"Hello to Jason Isaacs"
  } else {
    s"Nice to meet you ${name}!!!"
  } ensuring (_.length <= 25) // same as message => message.length <= 25
}
```

In the previous example, when taking the else branch, a value will be returned only if the resulting message is 25 chars or less.

## Running the Snippet
The snippets are intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load hello-assert-ensure.scala
```
