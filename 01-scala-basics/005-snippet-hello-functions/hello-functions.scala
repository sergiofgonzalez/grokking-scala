
/*
  Function that receives a single String argument and returns a String that interpolates the
  received argument with the message "Hello to <arg>"
*/
def getGreeting(name: String) : String = { s"hello to $name" }

getGreeting("Jason Isaacs")

/*
  Thanks to the powerful type inference system of Scala, you can forget about the return type
  in most of the cases
*/
def getGreetingLean(name: String) = { s"hello to $name" }

getGreetingLean("Idris Elba")

/*
  The `=` is not used to separate the function definition from the body, but rather
  to activate the type inference for the return value of the function, so it's necessary if
  you return a value from the function
*/

def getGreetinWrong(name: String) { 
  println("Inside the function")
  s"hello to $name"  

}

val result = getGreetinWrong("Riz Ahmed")
result

/*
  Parentheses can be omitted in the function definition, if no args are expected
*/
def sayHello = { s"Hello, there!" }

sayHello  // <- Note that parentheses can be dropped if not passing arguments

/*
  curly braces can be omitted if the function body is a single line
*/
def sayHelloLean = s"Hello, there!"

sayHelloLean


/*
  Example: function that returns the max between two integers

  Note that return is optional, if not used, the value of the last
  expression will be used
*/
def max(a: Int, b: Int) : Int = {
  if (a > b)
    a
  else
    b
}

max(4, 5)
max(5, 4)
max(5, 6)
max(5, 5)


/*
  Example: Parameterized Functions 

  Function that receives an argument of a generic type and returns a list of that type 
  whose single element is the one received
*/
def toList[A](elem: A) : List[A] = List(elem)

toList("hello, world")
toList(5)
toList(1.23)


/*
  Example: Functions as first-class citizens

  List in Scala feature a method `foldLeft` much as `reduce` does.
  It receives an initial value, and a function for the reduction  operation
*/
val nums = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
nums.foldLeft(0)((a: Int, b: Int) => a + b)

// types of the arguments for the function can be dropped
nums.foldLeft(0)((a, b) => a + b)

// and it can be a named function in a var
val reduceFunction = (a: Int, b: Int) => a + b
nums.foldLeft(0)(reduceFunction)

// or a "def" named function
def reduceDefFunction(a: Int, b: Int) : Int = a + b
nums.foldLeft(0)(reduceDefFunction)

// and you can use placeholders to identify the params passed to the function
nums.foldLeft(0)(_ + _)

// Scala also supports the following alternative syntax when passing functions as args
nums.foldLeft(0){ (_ + _) }
nums.foldLeft(0){ _ + _ }
nums.foldLeft(0){ (a, b) => a + b }
nums.foldLeft(0)( (a, b) => a + b )

/* 
  foldLeft is defined as:
  def foldLeft(initialValue: Int, operator: (Int, Int) => Int) = { ... }
*/


/*
  Defining a function that accepts a function that returns an Int
*/
def printResult(fn: () => Int) = { 
  val result = fn
  println(s"result=$result")
}

def gimme5() : Int = 5
printResult(gimme5)

printResult(((a: Int, b: Int) => a - b)(5, 2))
printResult(() => 5) // I can't make this work


/*
  Defining a function fn that accepts a function that returns and Int
*/

def fn(op: (Int, Int) => Int) {
  println(s"the result of executing the function with args: (2, 3) is ${ op(2, 3) }")
}

def addNums(a: Int, b: Int) : Int = a + b
fn(addNums)
fn((a: Int, b: Int) => a - b)