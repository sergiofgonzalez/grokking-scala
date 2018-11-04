package bobsdelight;

abstract class Fruit(
  val name: String,
  val color: String)

object Fruits {
  object Apple extends Fruit("apple", "red")
  object Orange extends Fruit(name = "orange", "orange")
  object Pear extends Fruit("pear", "yellowish")

  val menu = List(Apple, Orange, Pear)
}

