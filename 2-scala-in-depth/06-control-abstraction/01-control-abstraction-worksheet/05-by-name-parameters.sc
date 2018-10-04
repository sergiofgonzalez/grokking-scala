/*
  myAssert control structure
  + takes a function value as an argument
  + It will consult a flag to decide whether to invoke the
    received value and check it returns true, or just quietly
    complete
*/

var assertionsEnabled = true

/* version 0 */
def myAssert(predicate: () => Boolean) =
  if (assertionsEnabled && !predicate())
    throw new AssertionError


myAssert(() => 5 > 3)
// myAssert(() => "hello" == "hola") // throws Exception

// It will be better to use
//myAssert(5 > 3) // Err: Type mismatch

/* version 1 with by-name parameters */
def boolAssert(predicate: Boolean) =
    if (assertionsEnabled && !predicate)
        throw new AssertionError

// now we can do
boolAssert(5 > 3)

// however, there's one important difference in the implementation
// now the assertion is evaluated before calling myAssert:

assertionsEnabled = false
val x = 5
myAssert(() => x / 0 == 0)  // OK: assertions disabled
boolAssert(x / 0 == 0) // Arithmetic error before calling the function



