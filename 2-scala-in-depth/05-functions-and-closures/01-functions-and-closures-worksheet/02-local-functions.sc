import scala.io.Source

/* First approach: convert private method into local function */
/*
object LongLines {
  def processFile(filename: String, width: Int) :Unit = {
    def processLine(filename: String, width: Int, line: String) = {
      if (line.length > width)
        println(s"${filename}: ${line.trim}")
    }

    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(filename, width, line)
  }
}
*/

/* Further improvement: get rid of width and filename */
object LongLines {
  def processFile(filename: String, width: Int) :Unit = {
    def processLine(line: String) = {
      if (line.length > width)
        println(s"${filename}: ${line.trim}")
    }

    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(line)
  }
}


LongLines.processFile("./idea.bat", 50)
LongLines.processFile("./idea.bat", 75)
LongLines.processFile("./idea.bat", 90)