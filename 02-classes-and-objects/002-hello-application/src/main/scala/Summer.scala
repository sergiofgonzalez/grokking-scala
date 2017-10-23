import ChecksumAccumulator.calculate // now we can use calculate without qualifying it

object Summer {
  def main(args: Array[String]): Unit = {
    for (arg <- args)
      println(s"$arg: ${calculate(arg)}")
    println("-- done!!")
  }
}
