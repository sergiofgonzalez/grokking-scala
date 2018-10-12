import Element.elem

object ElementClientApp extends App {

  println(elem("hello to") above elem("world!"))
  println("========================")
  println(elem(Array("one", "two")) beside elem(Array("one")))
  println("========================")

  object Spiral {
    val space = elem(" ")
    val corner = elem("+")

    def spiral(nEdges: Int, direction: Int): Element = {
      if (nEdges == 1)
        elem("+")
      else {
        val sp = spiral(nEdges - 1, (direction + 3) % 4)
        def verticalBar = elem('|', 1, sp.height)
        def horizontalBar = elem('-', sp.width, 1)
        if (direction == 0)
          (corner beside horizontalBar) above (sp beside space)
        else if (direction == 1)
          (sp above space) beside (corner above verticalBar)
        else if (direction == 2)
          (space beside sp) above (horizontalBar beside corner)
        else
          (verticalBar above corner) beside (space above sp)
      }
    }
  }

  import Spiral.spiral

  println(spiral(6, 0))

  println(spiral(11, 0))

  println(spiral(27, 0))
}