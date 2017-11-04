/*
  Classic (non-functional) approach:
    Enrich class with extra functionality
*/
object FileMatcher {
  private def filesHere = (new java.io.File(".")).listFiles

  def filesEnding(query: String) =
    for (
      file <- filesHere;
      if file.getName.endsWith(query)
    ) yield file

  def filesContaining(query: String) =
    for (
      file <- filesHere;
      if file.getName.contains(query)
    ) yield file

  def filesRegex(query: String) =
    for (
      file <- filesHere;
      if file.getName.matches(query)
    ) yield file
}

FileMatcher.filesEnding(".scala")
FileMatcher.filesContaining("fun")
FileMatcher.filesRegex(".*\\..*")
println("==")

filesMatching(".md", _.endsWith(_))
filesMatching(".md", (filename: String, query: String) => filename.endsWith(query))
println("==")
/*
  Functional approach:
    allow for behavior customization
*/
def filesHere = (new java.io.File(".")).listFiles // returns an iterator on current dir files

def filesMatching(query: String, matcher: (String, String) => Boolean) =
  for (
    file <- filesHere;
    if matcher(file.getName, query)
  ) yield file


def filesEnding(query: String) =
  filesMatching(query, _.endsWith(_))  // same as (filename: String, query: String) => filename.endsWith(query)

def filesContaining(query: String) =
  filesMatching(query, _.contains(_))  // same as (filename: String, query: String) => filename.contains(query)

def filesRegex(query: String) =
  filesMatching(query, _.matches(_))   // same as (filename: String, query: String) => filename.matches(query)

filesEnding(".scala")
filesContaining("fun")
filesRegex(".*\\..*")
println("==")

/*
  It can be even simplified when wrapped
  in an object and using closures
*/
object FileMatcher {
  private def filesHere = (new java.io.File(".")).listFiles

  private def filesMatching(matcher: String => Boolean) =
    for (file <- filesHere; if matcher(file.getName))
      yield file

  def filesEnding(query: String) =
    filesMatching(_.endsWith(query))

  def filesContaining(query: String) =
    filesMatching(_.contains(query))    

  def filesRegex(query: String) =
    filesMatching(_.matches(query))    
}

FileMatcher.filesEnding(".scala")
FileMatcher.filesContaining("fun")
FileMatcher.filesRegex(".*\\..*")
println("==")

/*
  Another example
*/

// Imperative approach
def containsNeg(nums: List[Int]): Boolean = {
  var exists = false
  for (num <- nums)
    if (num < 0) exists = true
  exists
}

containsNeg(List(1, 4, -5, 7, 9))
containsNeg(List(1, 2, 3, 4, 5))
println("==")

// Using higher-order functions
// exists: def exists(predicate: (A) => Boolean): Boolean
//         Tests whether a predicate holds for at least one elem of this sequence
//         that is, the function `exists` expects a function that takes elements of the
//         same type as the List's elements and return boolean
def containsNeg(nums: List[Int]): Boolean = nums.exists(_ < 0) // elem => elem < 0
containsNeg(List(1, 4, -5, 7, 9))
containsNeg(List(1, 2, 3, 4, 5))
println("==")

def containsOdd(nums: List[Int]): Boolean = nums.exists(_ % 2 != 0) // elem => elem % 2 != 0
containsOdd(List(2, 4, 6, 8))
containsOdd(List(2, 3, 6, 4))