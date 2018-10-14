

/*

class Animal
trait Furry extends Animal
trait HasLegs extends Animal
trait FourLegged extends HasLegs
class Cat extends Animal with Furry with FourLegged

  Linearization:
  1) Animal -> AnyRef -> Any
  2) Furry -> Animal -> AnyRef -> Any
  3) FourLegged -> HasLegs -> Furry -> Animal -> AnyRef -> Any
  4) Cat -> FourLegged -> HasLegs -> Furry -> Animal -> AnyRef -> Any

  When any of these classes and traits invokes a method via super, the implementation invoked will
  be the first implementation to its right in the linearization

  To test it, i've added some methods and super calls
 */

class Animal {
  def one() = println("one!")
  def two() = println("two!")
}
trait Furry extends Animal {
  abstract override def two() = super.two()
  def three() = println("three!")
}
trait HasLegs extends Animal
trait FourLegged extends HasLegs {
  override def two() = println("two from FourLegged!")
}


class Cat extends Animal with Furry with FourLegged {
  override def one() = super.one()
  override def two() = super.two()
}


val myCat = new Cat
myCat.one()
myCat.two()
myCat.three()


