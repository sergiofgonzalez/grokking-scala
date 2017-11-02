# 008 &mdash; Tail Recursion (snippet)
> tail-recursive calls in Scala

## Description
Functions which call themselves as the last action are called *tail recursive*.

Scala supports *tail recursion optimization* meaning that it can detect if a recursive vall is the last thing that happens in the body of a recursive function and change the compile code to make it more efficient.

A tail-recursive function will not build a new stack frame for each call, and instead all calls will execute in a single frame. This is demonstrated in the example:
```scala
def boomV1(n: Int): Int = 
  if (n == 0) throw new Exception("Fabricated Exception!") else boomV1(n - 1) + 1  // not tail-recursive

def boomV2(n: Int): Int =
  if (n == 0) throw new Exception("Fabricated Exception!") else boomV2(n - 1) // tail-recursive
```

The first one, when showing the traces, will display 6 stack frames, while the second one will only display one.


Note however that the use of tail recursion optimization in Scala is fairly limited because the JVM instruction set:
+ Only recursive calls back to the same function making the call are candidates for optimization
+ If the result of a tail-call optimization goes to a function value, the optimization will not happen.

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load tail-recursion.scala
```