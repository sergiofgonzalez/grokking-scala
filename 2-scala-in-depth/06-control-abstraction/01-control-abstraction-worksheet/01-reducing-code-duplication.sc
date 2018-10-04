import java.io.File

/* version 1: search for files ending in... */
//object FileMatcher {
//  private def filesHere = (new java.io.File(".")).listFiles
//
//  def filesEnding(query: String) =
//    for {
//      file <- filesHere
//      if (file.getName.endsWith(query))
//    }
//      yield file
//}



/* version 2: add support for files containing string... */
//object FileMatcher {
//  private def filesHere = (new java.io.File(".")).listFiles
//
//  def filesEnding(query: String) =
//    for {
//      file <- filesHere
//      if (file.getName.endsWith(query))
//    }
//      yield file
//
//  def filesContaining(query: String) =
//    for {
//      file <- filesHere
//      if (file.getName.contains(query))
//    }
//      yield file
//}


/* version 3: add suport for regular expressions... */
//object FileMatcher {
//  private def filesHere = (new java.io.File(".")).listFiles
//
//  def filesEnding(query: String) =
//    for {
//      file <- filesHere
//      if (file.getName.endsWith(query))
//    }
//      yield file
//
//  def filesContaining(query: String) =
//    for {
//      file <- filesHere
//      if (file.getName.contains(query))
//    }
//      yield file
//
//  def filesRegex(query: String) =
//    for {
//      file <- filesHere
//      if file.getName.matches(query)
//    }
//      yield file
//}


/* version 4: using higher-order function to remove duplication */
//object FileMatcher {
//  private def filesHere = (new File(".")).listFiles
//
//  def filesMatching(query: String, matcher: (String, String) => Boolean) =
//    for {
//      file <- filesHere
//      if matcher(file.getName, query)
//    } yield file
//
//  /* backward compatibility */
//  def filesEnding(query: String) =
//    filesMatching(query, _.endsWith(_)) // same as (file, query) => file.endsWith(query)
//
//  def filesContaining(query: String) =
//    filesMatching(query, _.contains(_)) // same as (file, query) => file.contains(query)
//
//  def filesRegex(query: String) =
//    filesMatching(query, _.matches(_)) // same as (file, query) => file.matches(query)
//}

/* version 5: remove passing the query */
object FileMatcher {
  private def filesHere = (new File(".")).listFiles

  def filesMatching(matcher: String => Boolean) =
    for {
      file <- filesHere
      if matcher(file.getName)
    } yield file

  /* backward compatibility */
  def filesEnding(query: String) =
    filesMatching(_.endsWith(query)) // same as (file) => file.endsWith(query)

  def filesContaining(query: String) =
    filesMatching(_.contains(query)) // same as (file) => file.contains(query)

  def filesRegex(query: String) =
    filesMatching(_.matches(query)) // same as (file, query) => file.matches(query)
}

FileMatcher.filesEnding(".bat")
FileMatcher.filesContaining("or")
FileMatcher.filesRegex(".*or.*\\.bat")