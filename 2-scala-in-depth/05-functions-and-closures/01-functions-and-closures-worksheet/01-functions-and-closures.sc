import scala.io.Source


object LongLines {
  def processFile(filename: String, width: Int) :Unit = {
    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(filename, width, line)
  }

  private def processLine(filename: String, width: Int, line: String) = {
    if (line.length > width)
      println(s"${filename}: ${line.trim}")
  }
}

LongLines.processFile("./idea.bat", 50)
LongLines.processFile("./idea.bat", 75)
LongLines.processFile("./idea.bat", 90)