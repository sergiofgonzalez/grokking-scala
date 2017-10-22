package example

import scala.io.Source

object PrintFileLinesV3App extends App {

  def widthOfLineLength(s: String) = s.length.toString.length

  if (args.length > 0) {
    val fileLines = Source.fromFile(args(0)).getLines().toList

    var maxWidthOfLineLength = 0
    for (line <- fileLines) {
      maxWidthOfLineLength = maxWidthOfLineLength.max(widthOfLineLength(line))
    }

    for (line <- fileLines) {
      val numSpaces = maxWidthOfLineLength - widthOfLineLength(line)
      val padding = " " * numSpaces
      println(s"${ padding }${ line.length } | ${ line }")
    }

  } else {
    Console.err.println("Error: \n\tUsage: PrintFileLines <file>")
  }
}
