/*
  imperative form of function that prints multiplication table:

   0   0   0   0   0   0   0   0   0   0
   1   2   3   4   5   6   7   8   9  10
   2   4   6   8  10  12  14  16  18  20
   3   6   9  12  15  18  21  24  27  30
   4   8  12  16  20  24  28  32  36  40
   5  10  15  20  25  30  35  40  45  50
   6  12  18  24  30  36  42  48  54  60
   7  14  21  28  35  42  49  56  63  70
   8  16  24  32  40  48  56  64  72  80
   9  18  27  36  45  54  63  72  81  90
  10  20  30  40  50  60  70  80  90 100
*/


def printMultiTable() = {
  var i = 0
  while (i <= 10) {
    var j = 1
    while (j <= 10) {
      val prod = (i * j).toString
      var k = prod.length
      while (k < 4) {
        print(" ")
        k += 1
      }
      print(prod)
      j += 1
    }
    println()
    i += 1
  }
}

printMultiTable()

/*
  Alternative functional form:
    + return data instead of perform an action
    + avoid using while, for with indices
*/

// returns a row as a sequence (e.g. makeRowSeq(5) = Vector("   0", "   5", "  10", ..., "  50"))
def makeRowSeq(row: Int) =
  for (col <- 0 to 10) yield {
    val prod = (row * col).toString
    val padding = " " * (4 - prod.length)
    padding + prod
  }

// returns a row as a string (e.g. makeRow(5) = "   0   5  10...  50")
def makeRow(row: Int) = makeRowSeq(row).mkString

// returns table as a string with one row per line
def multiTable() = {
  val tableSeq = for (row <- 1 to 10)
    yield makeRow(row)
  
  tableSeq.mkString("\n")
}

val table = multiTable()
println("== table ==================================")
println(table)

println("== components =============================")
makeRowSeq(5)
makeRow(5)