def printSeparator() = println("===============================================")

/*
  You get nested iteration when adding multiple `<-` clauses
*/
val chars = List("a", "b", "c")
val nums = List("1", "2", "3")

for (
  ch <- chars;
  num <- nums
  )
  println(s"${ch}${num}")

/*
  You can also apply conditions when using nested iteration
*/

def fileLines(file: java.io.File) = scala.io.Source.fromFile(file).getLines.toList
val files = (new java.io.File(".")).listFiles()
def grep(pattern: String) =
  for (
    file <- files
    if (file.getName.endsWith(".scala"));
    line <- fileLines(file)
    if (line.trim.matches(pattern))
  )
    println(s"Match Found: $file => $line")

grep(".*for.*")
printSeparator()

/*
  Using curly braces instead of parentheses
*/
val chars = List("a", "b", "c")
val nums = List("1", "2", "3")

for {
  ch <- chars;
  num <- nums
  }
  println(s"${ch}${num}")
printSeparator()

/*
  You can also create "mid-stream variable bindings"
*/
def fileLines(file: java.io.File) = scala.io.Source.fromFile(file).getLines.toList
val files = (new java.io.File(".")).listFiles
def grep(pattern: String) {
  for {
    file <- files
    if file.getName.endsWith(".scala")
    line <- fileLines(file)
    trimmed = line.trim
    if trimmed.matches(pattern)
  } println(s"Match Found: $file => $trimmed")
}

grep(".*io.*")
