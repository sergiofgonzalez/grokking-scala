/*
  Assert
*/
def checkGreaterThan10(num: Int) {
  assert(num > 10)
  println("greater than 10")
}

def checkGreaterThan10WithMessage(num: Int) {
  assert(num > 10, "not greater than 10")
  println("greater than 10")
}


object MessageHolder {
  val name = "Jason Isaacs"

  override def toString = s"Hello to $name, the number passed is no greater than 10"
}

def checkGreaterThan10WithObject(num: Int) {
  assert(num > 10, MessageHolder)
  println("greater than 10")
}


checkGreaterThan10(5)
checkGreaterThan10WithMessage(7)
checkGreaterThan10WithObject(9)

/* 
  Ensuring
*/

def getGreeting(name: String) = {
  if (name == "Jason Isaacs") {
    s"Hello to Jason Isaacs"
  } else {
    s"Nice to meet you ${name}!!!"
  } ensuring (_.length <= 25) // same as message => message.length <= 25
}

getGreeting("Jason Isaacs")
getGreeting("Idris")
getGreeting("Riz Ahmed")