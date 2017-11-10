/* Null */
val i: Int = null  // Err: Null is ineligible for implicit conversion

/* Nothing: throwing an exception is an expression of type Nothing */
def raiseError(message: String): Nothing = throw new Exception(message)