import bobsdelight.Fruit
import bobsdelight.Fruits._

object FruitsApp extends App {

  showFruit(Apple)

  def showFruit(fruit: Fruit) = {
    import fruit._
    println(s"${name}s are ${color}")
  }
}
