abstract class IntQueue {
  def get: Int
  def put(x: Int): Unit
}

import scala.collection.mutable.ArrayBuffer

class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]
  def get: Int = buf.remove(0)
  def put(x: Int) = buf += x
}

val queue = new BasicIntQueue
queue.put(10)
queue.put(20)

queue.get
queue.get

/* stackable modification: Doubling */
trait Doubling extends IntQueue {
  abstract override def put(x: Int): Unit = super.put(2 * x)
}

class MyDoublingQueue extends BasicIntQueue with Doubling

val myDoublingQueue = new MyDoublingQueue
myDoublingQueue.put(10)
myDoublingQueue.put(20)

myDoublingQueue.get
myDoublingQueue.get

/* mixing in a trait at the point of instantiation */
val myQueue = new BasicIntQueue with Doubling
myQueue.put(50)
myQueue.put(100)
myQueue.get
myQueue.get

/* Stackable modification: Incrementing */
trait Incrementing extends IntQueue {
  abstract override def put(x: Int): Unit = super.put(x + 1)
}

trait Filtering extends IntQueue {
  abstract override def put(x: Int): Unit = if (x >= 0) super.put(x)
}

val q = (new BasicIntQueue with Incrementing with Filtering)
q.put(-1)
q.put(0)
q.get