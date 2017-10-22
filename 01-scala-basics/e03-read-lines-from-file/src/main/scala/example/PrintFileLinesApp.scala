package example

import scala.io.Source

object PrintFileLinesApp extends App {

  if (args.length > 0) {
    for (line <- Source.fromFile(args(0)).getLines())
      println(s"[${line.length}] $line")
  } else {
    Console.err.println("Error: \n\tUsage: PrintFileLines <file>")
  }
}
