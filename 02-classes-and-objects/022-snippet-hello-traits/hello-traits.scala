
trait Philosophical {
  def philosophize() = {
    println("I consume memory, therefore i am")
  }
}

class Frog extends Philosophical {
  override def toString = "green"
}

val kermit = new Frog
kermit.philosophize()

val anotherFrog: Philosophical = new Frog
anotherFrog.philosophize()

class Animal

class Frog extends Animal with Philosophical {
  override def toString = "green animal"
}

val kermit = new Frog
kermit.philosophize()

trait HasLegs
class Frog extends Animal with Philosophical with HasLegs {
  override def toString = "green animal with legs"
}
val kermit = new Frog
kermit.philosophize()


class Frog extends Animal with Philosophical {
  override def toString = "green animal"
  override def philosophize() = println("It ain't easy being green")
}

val kermit = new Frog
kermit.philosophize()

// Note that if you force kermit to be of type Animal, the philosophize method invocation will not work
val kermit: Animal = new Frog
kermit.philosophize()  // Err: philosophize is not a member of Animal

/*
  Traits are like classes but they don't allow class parameters
*/
trait Point(x: Int, y: Int) // Err: trait objects may not have parameters
println("=============\n\n")

/* 
  Modeling Rectangular objects with traits
*/

class Point(val x: Int, val y: Int)  // Using val defines x, y as fields in the class

trait Rectangular {
  def topLeft: Point
  def bottomRight: Point

  def left = topLeft.x
  def right = bottomRight.x
  def width = right - left

  def top = topLeft.y
  def bottom = bottomRight.y
  def height = bottom - top
}

class Rectangle(val topLeft: Point, val bottomRight: Point) extends Rectangular {
  override def toString = s"{(${topLeft.x}, ${topLeft.y}), (${bottomRight.x}, ${bottomRight.y})}"
}

val rect = new Rectangle(new Point(1, 1), new Point(10, 10))
rect.left
rect.right
rect.top
rect.bottom
rect.width
rect.height