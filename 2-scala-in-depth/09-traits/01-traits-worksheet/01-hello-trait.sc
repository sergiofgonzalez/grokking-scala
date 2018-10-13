trait Philoshophical {
  def philosophize() = println("I consume memory, therefore I am!")
}

class Frog extends Philoshophical {
  override def toString: String = "green"
}

val frog = new Frog
frog.philosophize()

/* a trait also defines a type */
val phil: Philoshophical = frog
phil.philosophize()

/* using with... */
class Animal
trait HasLegs

class Turtle extends Animal with Philoshophical with HasLegs {
  override def toString = "green shell"
}

/* overriding methods from the trait */
class Tortoise extends Animal with Philoshophical {
  override def toString: String = "green shell"
  override def philosophize(): Unit = println(s"It ain't easy having a ${toString}!")
}

val t = new Tortoise
t.philosophize()
