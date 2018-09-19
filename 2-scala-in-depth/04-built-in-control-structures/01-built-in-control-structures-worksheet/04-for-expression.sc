import java.io.File

/* simple use: iterate over a collection */

// Iterating over a collection (Array[File] in this case)
println(new File(".").getAbsolutePath)

val filesHere = (new File(".")).listFiles()
for (file <- filesHere)
  println(file)

// Iterating over a Range of integers
for (i <- 1 to 3)
  println(s"iteration: $i")

for (i <- 1 until 3)
  println(s"iteration: $i")

/* Filtering: adding an if clause in the for expression */
for (file <- filesHere if file.getName.endsWith(".bat"))
  println(file)

// Adding additional clauses
for (
  file <- filesHere
  if file.isFile
  if file.getName.endsWith(".bat")
  ) println(file)

/* Nested loops */
import scala.io.Source

def fileLines(file: File) = Source.fromFile(file).getLines().toList

def grep(pattern: String) = {
  for (
    file <- filesHere
    if file.getName.endsWith(".bat");
    line <- fileLines(file)
    if (line.matches(pattern))
  ) println(s"$file: $line")
}

grep(".*ECHO.*")

// Using curly braces has the added benefit of not having to use ;

def grepCurl(pattern: String) = {
  for {
    file <- filesHere
    if file.getName.endsWith(".bat")
    line <- fileLines(file)
    if (line.matches(pattern))
  } println(s"$file: $line")
}

grepCurl(".*JAVA.*")

/* mid-stream variable binding */
def grepTrimmed(pattern: String) = {
  for {
    file <- filesHere
    if file.getName.endsWith(".bat")
    line <- fileLines(file)
    trimmedLine = line.trim
    if (trimmedLine.matches(pattern))
  } println(s"$file: $trimmedLine")
}

grepTrimmed(".*JDK.*")

/* Producing new collections */
def batFiles =
  for {
    file <- filesHere
    if file.getName.endsWith(".bat")
  } yield file

batFiles

val lineLengths =
  for {
    file <- filesHere
    if file.getName.endsWith(".bat")
    line <- fileLines(file)
    trimmedLine = line.trim
    if trimmedLine.matches(".*SET.*")
  } yield {
    println(s"match found on $file: $trimmedLine")
    trimmedLine.length
  }

lineLengths