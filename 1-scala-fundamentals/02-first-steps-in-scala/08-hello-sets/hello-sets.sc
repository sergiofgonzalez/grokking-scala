var jetSet = Set("Boeing", "Airbus")
jetSet += "Learjet"

println(jetSet)
jetSet.contains("Cessna")

// Note that we cannot add more elems to a set defined with val because it's immutable
val mySet = Set("one", "two", "three")
// mySet += "Catorce" // -> Err: received is not assignable

// += is a shorthand
jetSet = jetSet + "Beechcraft"

// Mutable sets require an import
import scala.collection.mutable

val movieSet = mutable.Set("Sicario", "Regression")
movieSet += "Detroit" // OK, movieSet is mutable
println(movieSet)

// Using an implementation explicitly
import scala.collection.immutable.HashSet

val hashSet = HashSet("Tomatoes", "Chillies", "Onions")
// hashSet += "Coriander" // Err: It's immutable
println(hashSet + "Coriander")