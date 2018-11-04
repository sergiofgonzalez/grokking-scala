package object bobsdelight {

  def showFruit(fruit: Fruit): Unit = {
    import fruit._
    println(s"${name}s are ${color}")
  }
}
