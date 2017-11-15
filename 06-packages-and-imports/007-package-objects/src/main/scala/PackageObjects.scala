package printmenu

import delights.Fruits
import delights.showFruit

object PackageObjectsApp {
  def main(args: Array[String]) = {
    for (fruit <- Fruits.menu)
      showFruit(fruit)
  }
}