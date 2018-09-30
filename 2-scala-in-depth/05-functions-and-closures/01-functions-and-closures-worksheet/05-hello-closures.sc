
/* Special Function Call Forms I: Repeated args */
def echo(args: String*) =
  for (arg <- args)
    println(arg)

echo("one")
echo ("one", "two", "three", "catorce")

val nums = Array("one", "two", "three", "catorce")
//echo(nums) // Err: type mismatch
echo(nums: _*)

/* Special Function Call Forms II: Named args */
def speed(distance: Float, time: Float) = distance / time

speed(5.5F, 35.0F)

speed(time = 35, distance = 5.5f)

// mixing positional and named
def multiParam(first: Int, second: String, third: Float, fourth: Char) =
  println(
    s"""first=$first
       |second=$second
       |third=$third
       |fourth=$fourth
     """.stripMargin)

multiParam(1, "one", fourth = 'O', third = 1.0f)

/* Special Function Call Forms III: Named args */
def printTime(out: java.io.PrintStream = Console.out) =
  println(System.currentTimeMillis())

printTime()
printTime(Console.err)

def printTime2(out: java.io.PrintStream = Console.out, divisor: Int = 1) =
  println(System.currentTimeMillis() / divisor)
printTime2()
printTime2(Console.err)
printTime2(divisor = 1000)
printTime2(Console.err, divisor = 1000)
