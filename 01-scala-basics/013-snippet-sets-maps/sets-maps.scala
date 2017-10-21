
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