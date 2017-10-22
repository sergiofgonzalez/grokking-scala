
/*
  Default way of creating (inmmutable) sets
*/
var jetSet = Set("Boeing", "Airbus")
jetSet += "Lear"

jetSet
jetSet.contains("Cessna")

/*
  Mutable set
*/
import scala.collection.mutable

val actorSet = mutable.Set("Jason", "Idris")
actorSet += "Riz"

/*
  Forcing the type to immutable HashSet
*/
import scala.collection.immutable.HashSet

val ahashSet = HashSet("Tomatoes", "Chillies")
val b = ahashSet + "Parsley"

/* 
  Mutable map
*/
import scala.collection.mutable

val treasureMap = mutable.Map[Int, String]()
treasureMap += (1 -> "Go to island")
treasureMap += (2 -> "Find big X on the ground")
treasureMap += (3 -> "Dig until find treasure chest")

treasureMap(2)

/*
  Immutable Maps are the default
*/
val romanNumerals = Map(1 -> "I", 2 -> "II", 3-> "III", 4 -> "IV", 5 -> "V")
romanNumerals(4)