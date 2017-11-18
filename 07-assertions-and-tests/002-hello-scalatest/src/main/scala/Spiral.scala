import Element.elem


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

  def main(args: Array[String]) {
    if (args.length == 1) {
      val nSides = args(0).toInt
      println(spiral(nSides, 0))
    } else {
      println("Running shakedown demo")
      
      println("1.- Simple use of above")
      val lineElem1 = elem("Hello to Jason Isaacs!")
      val lineElem2 = elem("Hello to Idris Elba!")
      println(s"lineElem1=$lineElem1, lineElem2=$lineElem2")
      println("===================================\n\n")

      val lineAbove = lineElem1 above lineElem2
      println(s"lineElem1 above lineElem2:\n$lineAbove")
      println("===================================\n\n")


      println("2.- Simple use of below")
      println(s"lineElem1=$lineElem1, lineElem2=$lineElem2")
      val lineBeside = lineElem1 beside lineElem2
      println(s"lineElem1 above lineElem2:\n$lineBeside")
      println("===================================\n\n")


      println("3.- The UniformElement")
      val uniformElem = elem('*', 7, 3)
      println(s"elem('*', 7, 3):\n$uniformElem")
      println("===================================\n\n")


      println("4.- The ArrayElement")
      val arrayElem = elem(Array("hello", "to", "Jason", "Isaacs"))
      println("elem(Array(hello, to, Jason, Isaacs):\n" + arrayElem)  // string interpolation and escaping does not seem to to work
      println("===================================\n\n")      
    }
  }
}
