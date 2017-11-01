import scala.io.Source

object LongLines {
  def processFile(filename: String, width: Int) = {
    def processLine(filename: String, width: Int, line: String) = {
      if (line.length > width)
        println(s"$filename: ${line.trim}")
    }

    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(filename, width, line)
  }
}

LongLines.processFile("./LongLines.scala", 50)
println("==")

/*
  More succinct implementation as local functions
  get access to the enclosing function parameters 
*/
object LongLines {
  def processFile(filename: String, width: Int) = {
    def processLine(line: String) = {
      if (line.length > width)
        println(s"$filename: ${line.trim}")
    }

    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(line)
  }
}
LongLines.processFile("./LongLines.scala", 50)