
/* using assert */
def getGreeting(yourNameHere: String): String = {
  val message = s"Hello to ${yourNameHere}!!!"
  assert(message.length <= 20, "Please use a shorter name")
  message
}

println(getGreeting("Jason"))
// println(getGreeting("Jason Isaacs")) // AssertionError

/* using ensuring is much more concise */
def getGreeting2(yourNameHere: String): String = {
  {
    val message = s"Hello to ${yourNameHere}!!!"
    message
  } ensuring ( _.length <= 20)
}

println(getGreeting2("Steve"))
println(getGreeting2("Jeremy Irons")) // AssertionError