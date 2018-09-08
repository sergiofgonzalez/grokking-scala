import ChecksumCalculator.calculate

object FallWinterSpringSummer extends App {
  println("args: " + args.toList)

  for (season <- List("fall", "winter", "spring", "summer"))
    println(season + ": " + calculate(season))
}