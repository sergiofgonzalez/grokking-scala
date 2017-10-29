def printSeparator() = println("===============================================")

/*
  functional for:
    returns a collection rather than perform actions
*/
val files = (new java.io.File(".")).listFiles
for {
  file <- files
  if file.getName.endsWith(".scala")
} yield file

/*
  the syntax is:
    for clauses
      yield body
*/
for (file <- files) {
  yield file.getName  // Syntax error
} 

for (file <- files) yield {
  file.getName // OK
}