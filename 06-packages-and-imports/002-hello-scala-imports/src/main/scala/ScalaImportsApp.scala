// single member import
import com.github.sergiofgonzalez.delights.Fruit


/*
  There are more examples in the README file than in the program!
*/
object ScalaImportsApp {

  def main(args: Array[String]) {
    // import all members of Fruits object (brings Apple, Orange, Pear and menu)
    import com.github.sergiofgonzalez.delights.Fruits._
    showFruit(Orange)

    val fruit: Fruit = Pear
    import fruit._
    println(name)
    println("-- done!!!")
  }

  def showFruit(fruit: Fruit) {
    /* import members of fruit parameter: name and color!!! */
    import fruit._
    println(s"${name}s are ${color}")
  }
}
