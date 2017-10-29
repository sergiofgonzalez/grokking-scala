def printSeparator() = println("===============================================")

/*
  Filtering in the generator
*/
val files = (new java.io.File(".")).listFiles

for (file <- files if file.getName.endsWith(".scala"))
  println(file)
printSeparator()

/*
  Additional filters are allowed in the for-expression
*/
val files = (new java.io.File(".")).listFiles

for (
  file <- files
  if file.isFile
  if file.getName.endsWith(".scala")
  )
  println(file)
printSeparator()

/* 
  More traditional imperative filtering
*/
val files = (new java.io.File(".")).listFiles

for (file <- files)
  if (file.getName.endsWith(".scala"))
    println(file)
printSeparator()

