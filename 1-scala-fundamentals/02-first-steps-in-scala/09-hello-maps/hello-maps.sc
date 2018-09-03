import scala.collection.mutable

val treasureMap = mutable.Map[Int, String]()
treasureMap += (1 -> "Go to island")
treasureMap += (2 -> "Find big X on the ground")
treasureMap += (3 -> "Dig, dig and dig")

println(treasureMap(2))

(1 -> "Go to island") // -> this is syntactic sugar to return a Tuple2 (Int, String)


// inmmutable maps
val romanNumerals = Map(1 -> "I", 2 -> "II", 3 -> "III", 4 -> "IV")
romanNumerals(3)