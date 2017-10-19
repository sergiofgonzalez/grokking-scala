/* define a function that maps numbers 1 to 10 to its ordinal form 1st, 2nd, 3rd, etc. */

def ordinal(n: Int) = {
  println(s"Mapping $n to its ordinal representation")
  n match {
    case 1 => println("1st")
    case 2 => println("2nd")
    case 3 => println("3rd")
    case 4 => println("4th")
    case 5 => println("5th")
    case 6 => println("6th")
    case 7 => println("7th")
    case 8 => println("8th")
    case 9 => println("9th")
    case 10 => println("10th")
    case _ => println(s"Error: Invalid argument $n. Cannot go beyond 10")              
  }
}

val num = scala.util.Random.nextInt(20) + 1
ordinal(num)

