
// Defining a function with all the "optional" parts
def max(x: Int, y: Int): Int = {
  if (x > y) x
  else y
}

max(5, 7)
max(3, 1)
max(9, 9)

// Defining a function omitting all the "optional" parts
def maxLean(x: Int, y: Int) = if (x > y) x else y
maxLean(5, 7)
maxLean(3, 1)
maxLean(9, 9)

// Defining a function for its side effects without args
def greeting() = println("Hello to Jason Isaacs!!!")
greeting()

// Defining a function for its side effects with an arg
def greetMe(myName: String) = println("Hello to " + myName + "!!!")
greetMe("Sergio")



