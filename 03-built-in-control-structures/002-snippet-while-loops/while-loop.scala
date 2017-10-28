def greatestCommonDivisor(x: Long, y: Long): Long = {
  var a = x
  var b = y
  while (a != 0) {
    val temp = a
    a = b % a
    b = temp
  }
  b
}

greatestCommonDivisor(216, 126)

println("======================")
var line = ""
do {
  line = readLine()
  println(s"Read: $line")  
} while (line != "")