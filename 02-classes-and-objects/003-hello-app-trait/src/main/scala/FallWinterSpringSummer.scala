import ChecksumAccumulator.calculate // now we can use calculate without qualifying it

object FallWinterSpringSummer extends App {
  for (season <- List("fall", "winter", "spring"))
    println(s"$season: ${ calculate(season) }");
}
