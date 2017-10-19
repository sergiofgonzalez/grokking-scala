
/* 
  var is a regular variable: it can be reassigned
*/
var name = "Jason"
name = "Idris"

/* val is a variable declared as final */
val surname = "Isaacs"
surname = "Elba" // Error: reassignment to val


/* you can specify the type of a particular variable while declaring it */
val num: Int = 2

/* You can use the placeholder _ to indicate you will eventually know */
var untypedWillKnowLater = _
untypedWillKnowLater = "The time has come" // Err: it is required to give a type

var willKnowLater: String = _
willKnowLater = "The time has come" // this works


/* Scala evaluates the variables as they are declared, unless you specifically tell otherwise */
def someTimeConsumingOperation() = {
  "Hello to Jason Isaacs"
}

lazy val forLater = someTimeConsumingOperation() // returns <lazy>
val forNow = someTimeConsumingOperation()

/* it can be used with simple assignments too */
lazy val num = 5 // <lazy>

/* you can force evaluation by using the var */
forLater
num


/* peeking into destructuring assignment in Scala */
val first :: rest = List(1, 2, 3)
first
rest