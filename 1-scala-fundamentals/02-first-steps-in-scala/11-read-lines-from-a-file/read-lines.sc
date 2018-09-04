import scala.io.Source
import java.nio.file.Paths

val filePath = "C:\\Users\\sergio.f.gonzalez\\git\\grokking-scala\\1-scala-fundamentals\\02-first-steps-in-scala\\11-read-lines-from-a-file\\"
val filename = "read-lines.sc"

val fileAbsolutePath = Paths.get(filePath, filename).toString

// First approach: not good formatting but really succinct
def printLines(filePath: String): Unit = {
  for (line <- Source.fromFile(filePath, "UTF-8").getLines())
    println(line.length + " | " + line)
}

// printLines(fileAbsolutePath)

// Second approach: better formatting, awful implementation
def betterPrintLines(filePath: String): Unit = {
  def maxWidthLen(lines: List[String]) : Int = {
    var maxLen = 0;
    for (line <- lines) {
      maxLen = Math.max(maxLen, line.length)
    }
    maxLen.toString.length
  }

  def padRight(numStr: String, width: Int): String = {
    if (numStr.length >= width) numStr
    else {
      var paddedNum: String = ""
      var i = 0
      while (i < width - numStr.length) {
        paddedNum = paddedNum + " "
        i += 1
      }
      paddedNum + numStr
    }
  }

  val lines = Source.fromFile(filePath, "UTF-8").getLines().toList
  val maxWidthLenValue = maxWidthLen(lines)

  for (line <- lines)
    println(padRight(line.length.toString, maxWidthLenValue) + " | " + line)
}

betterPrintLines(fileAbsolutePath)

// More functional approach
def bestPrintLines(filePath: String): Unit = {
  def widthOfLength(s: String) = s.length.toString.length

  val lines = Source.fromFile(filePath, "UTF-8").getLines().toList
  val longestLine = lines.reduceLeft((acc, elem) => if (acc.length > elem.length) acc else elem)
  val maxWidth = widthOfLength(longestLine)

  for (line <- lines) {
    val numSpaces = maxWidth - widthOfLength(line)
    val padding = " " * numSpaces // hey, i didn't know that!!
    println(padding + line.length + " | " + line)
  }
}

bestPrintLines(fileAbsolutePath)