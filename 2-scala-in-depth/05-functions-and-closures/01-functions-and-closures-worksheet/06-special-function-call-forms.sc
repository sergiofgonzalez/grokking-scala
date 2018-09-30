var more = 1

val addMore = (x: Int) => x + more

addMore(5)

/* closures capture variables rather than values themselves */
more = 10
addMore(5)

/*
  the same is true in the opposite direction:
  changes made by the closure are visible outside of the closure
*/

val someNumbers = List(-11, -10, -5, 0, 5, 10)

var sum = 0
someNumbers.foreach(sum += _)
println(sum)

/*
  what happens when there are more than one var
 */

def makeIncreaser(inc: Int) = (x: Int) => x + inc

val inc1 = makeIncreaser(1) // bound with 1
val inc2 = makeIncreaser(2) // bound with 2

inc1(99)
inc2(99)

/* the typical authenticator example */

def authFn(password: String) = (challenge: String) => challenge == password

val user1Authorized = authFn("secret")

if (user1Authorized("pass"))
  println("User has been authenticated")
else
  println("Sorry, user1 was not properly authenticated")

if (user1Authorized("secret"))
  println("User has been authenticated")
else
  println("Sorry, user1 was not properly authenticated")