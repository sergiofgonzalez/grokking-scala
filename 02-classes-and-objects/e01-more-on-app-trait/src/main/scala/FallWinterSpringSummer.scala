import ChecksumAccumulator.calculate // now we can use calculate without qualifying it

object FallWinterSpringSummer extends App {

  def getValuesToCalculate(): List[String] = {
    val defaultValues = List("fall", "winter", "spring", "summer")
    if (args.isEmpty) 
      defaultValues
    else 
      args.toList ::: defaultValues
  }

  for (value <- getValuesToCalculate)
    println(s"$value: ${ calculate(value) }");
}
