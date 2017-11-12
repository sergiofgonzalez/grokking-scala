# 024 &mdash; Traits as Stackable Modifications
> illustrating the concept of stackable modifications of an existing class using Scala traits

## Description
Providing *stackable modifications* to classes is another major use (along with rich interfaces) of traits.
Defining traits for stackable modifications means modify the behavior of an underlying class without providing a full-featured implementation of the underlying class.

As an example, let's illustrate how to stack modifications to a simple queue of integers featuring only `get` and `put` operations.

```scala
abstract class IntQueue {
  def get() : Int
  def put(x: Int)
}

import scala.collection.mutable.ArrayBuffer

class SimpleIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]
  def get() = buf.remove(0)
  def put(x: Int) = buf += x
}
```

Now, let's go with the traits:
+ Doubling &mdash; double all integers that are put in the queue
+ Incrementing &mdash; increment all integers that are put in the queue
+ Filtering &mdash; filter out negative integers from a queue

These treats represent modifications, as rather than define full-feature queues, they just modify the behavior of the underlying queue class.

```scala
trait Doubling extends IntQueue {
  abstract override def put(x: Int) = super.put(2 * x)
}

class MyQueue extends SimpleIntQueue with Doubling
```

Note that:
+ The trait declares a superclass that is an actual class, not a trait. This also means that the trait can only be *mixed in* with classes that extend from `IntQueue`.
+ The trait declares an *abstract override put* method whose implementation consists in calling the superclass method. This would be illegal for regular classes, but will work for traits because super calls in traits are dynamically bound. In order to let the compiler know that you're doing this on purpose (i.e. you want this call to use an actual implementation coming from another trait or class that gives a concrete implementation of `put`) you mark the method as `abstract override`.


And, as we expect, Scala adds some syntactic sugar to be able to add your trait while instantiating objects:
```scala
val doublingQueue = new SimpleIntQueue with Doubling
doublingQueue.put(5) // <- will put a 10 
```

It must be noted that the order of the traits is significant:
```scala
trait Incrementing extends IntQueue {
  abstract override def put(x: Int) = super.put(x + 1)
}

trait Filtering extends IntQueue {
  abstract override def put(x: Int) = if (x >= 0) super.put(x)
}

val queue = new SimpleIntQueue with Incrementing with Filtering
```

In the previous snippet, `Filtering` is applied before `Incrementing` and therefore:
```scala
queue.put(-1) // <- Filtering is applied first, so not put in the queue
queue.put(0)  // <- puts a 1
queue.put(1)  // <- puts a 2
```

while:
```scala
val queue = new SimpleIntQueue with Filtering with Incrementing
queue.put(-1) // <- Incrementing is applied first, so put(0)
queue.put(0)  // <- puts a 1
queue.put(1)  // <- puts a 2
```


## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:

```
scala> :load traits-stackable-modifications.scala
```