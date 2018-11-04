import bobsdelight.Fruits
import bobsdelight.showFruit

object FruitsApp extends App {

  for (fruit <- Fruits.menu)
    showFruit(fruit)
}
