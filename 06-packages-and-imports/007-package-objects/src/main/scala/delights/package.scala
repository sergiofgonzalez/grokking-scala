package object delights {
  def showFruit(fruit: Fruit) = {
    import fruit._
    println(s"${name}s are $color")
  }
}