/*
  Queue definition
*/

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

val q = new SimpleIntQueue
q.put(1)
q.put(2)

q.get()
q.get()

println("==\n")

/*
  Defining Doubling trait as a stackable modification
*/
trait Doubling extends IntQueue {
  abstract override def put(x: Int) = super.put(2 * x)
}

class MyQueue extends SimpleIntQueue with Doubling

val dq = new MyQueue
dq.put(1)
dq.put(2)

dq.get()
dq.get()
println("==\n")

/*
  ... and Scala syntax lets you add the trait when invoking new
*/
val doublingQueue = new SimpleIntQueue with Doubling
doublingQueue.put(5)
doublingQueue.put(10)

doublingQueue.get()
doublingQueue.get()
println("==\n")

/*
  Some other traits for stack modifications
*/
trait Incrementing extends IntQueue {
  abstract override def put(x: Int) = super.put(x + 1)
}

trait Filtering extends IntQueue {
  abstract override def put(x: Int) = if (x >= 0) super.put(x)
}

val queue = new SimpleIntQueue with Incrementing with Filtering
queue.put(-1)
queue.put(0)
queue.put(1)

queue.get()
queue.get()
println("==\n")

val queue = new SimpleIntQueue with Filtering with Incrementing
queue.put(-1)
queue.put(0)
queue.put(1)

queue.get()
queue.get()
queue.get()
println("==\n")