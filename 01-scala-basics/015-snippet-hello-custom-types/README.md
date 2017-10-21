# 014 &mdash; Custom Types look like Built-in Types (snippet)
> custom types that look like built-in types

## Description
Scala syntax allows you to use custom types such as built-in types. To illustrate it, the snippet defines a function that computes the factorial of a number using the `BigInt` type, which is not a built-in type.

Therefore, you can do:
```scala
def factorial(n: BigInt) : BigInt =
  if (n == 0) 1 else n * factorial(n - 1)
```

Note that you can check if the BigInt is zero using `==` and that you can use `*` to multiply.

Scala doesn't have operator overloading (it actually does not have the concept of operator), but it lets you use method names like `+`, `-`, etc.

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load hello-custom-types.scala
```