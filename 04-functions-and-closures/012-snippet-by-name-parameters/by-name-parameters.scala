/*
  Creating a custom control structure that takes no parameters:
    myAssert: performs an assertion on a predicate and fails if it is not true
*/

val assertionsEnabled = true
def myAssert(predicate: () => Boolean) =
  if (assertionsEnabled && !predicate())
    throw new AssertionError

myAssert(() => 5 > 3) // not very nice syntax
myAssert(5 > 3) // Err: found Boolean, expects () => Boolean
println("==")

/*
  Using by-name parameters allows for nicer syntax
*/
val assertionsEnabled = true
def myAssert(predicate: => Boolean) =
  if (assertionsEnabled && !predicate)
    throw new AssertionError

myAssert(5 > 3) // Cool!
myAssert(3 > 5)
println("==")

/*
  There is one caveat in the latter approach:
    + The expression will be evaluated before applying it
      to myAssert, so any side effects will be visible before
      calling the function
*/
val assertionsEnabled = false
def myAssert(predicate: () => Boolean) =
  if (assertionsEnabled && !predicate())
    throw new AssertionError

val x = 5
myAssert(() => x / 0 == 0) // assertion are disabled, so x / 0 is not even evaluated

// According to the book when using by-name parameters the expression is evaluated before the function
// is applied, so this would fail but it doesn't

val assertionsEnabled = false
def myAssert(predicate: => Boolean) =
  if (assertionsEnabled && !predicate)
    throw new AssertionError

val x = 5
myAssert(x / 0 == 0) // It doesnt fail either