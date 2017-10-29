/* list files in current dir */
var files = new java.io.File(".").listFiles
for (file <- files)
  println(s"File found: $file")
println("===========")

/* with list */
val nums = List("one", "two", "three", "catorce")
for (numStr <- nums)
  println(numStr)
println("===========")

/* with ranges */
for (num <- 1 to 5)
  println(num)

for (num <- 0 until 5)
  println(num)
println("===========")  


/* Iterating over a collection with index */
var files = new java.io.File(".").listFiles
for (i <- 0 to files.length - 1)
  println(s"$i: ${files(i)}")
