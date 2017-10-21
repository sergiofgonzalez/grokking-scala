val numStrList = List("uno", "dos", "tres", "catorce", "cinco", "seis", "siete", "ocho")

/* good old for receiving a generator */

for (num <- numStrList) {
  if (num.length > 3) {
    println(num)
  }
}

/* this is similar to good olde for */
for (i <- 0 to numStrList.length)
  println(numStrList(i))

/* 
  alternative syntax :
    + The for includes the generator num <-numStrList
    + and a guard
    + The expression will be executed only if the guard is true
*/
for (
  num <- numStrList;  // <- note the semi-colon
  if (num.length > 5)
) println(num)



/* another example with the alt syntax */
val files = new java.io.File(".").listFiles

for (
 file <- files;
 filename = file.getName;
 if (filename.endsWith(".scala"))
 ) println(file)


/* using several generators in the for */
val nums = List(1, 2, 3)
val chars = List('a', 'b', 'c')

for (num <- nums; ch <- chars)
  println(s"$num$ch")

/* for allows to use curly braces instead of parentheses */
for { ch <- chars; num <- nums }
  println(s"$ch$num")

/* 
  Scala allows you to use the functional form of for, in which
  you return a value, rather than operate on the for body (i.e. imperative for)
*/
val aList = List(1, 2, 3)
val bList = List(10, 20, 30)

val resultList = for { a <- aList; b <- bList } yield a + b  // it's a List[Int]