import ChecksumCalculator.calculate

object Summer {
  def main(args: Array[String]) =
    if (args.isEmpty)
      println("Usage: Summer arg1 arg2 ... argn")
    else
      for (arg <- args)
        println(arg + ": " + calculate(arg))
}
