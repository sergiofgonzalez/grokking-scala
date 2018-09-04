val args = Array("Scala", "is", "fun")

// Purely imperative style
// usage of var, function with side effects
def printArgs(args: Array[String]): Unit = {
  var i = 0
  while (i < args.length) {
    println(args(i))
    i += 1
  }
}

printArgs(args)

// using for expression makes it more functional
// no usage of var, still side effects
def printArgs2(args: Array[String]): Unit = {
  for (arg <- args)
    println(arg)
}

printArgs2(args)

// or using foreach
// no usage of var, still side effects, but passing a function literal while traversing
def printArgs3(args: Array[String]): Unit = {
  args.foreach(arg => println(arg))
}

printArgs3(args)

// or even more succinct without fat arrow
// no usage of var, still side effects, really concise
def printArgs4(args: Array[String]): Unit = {
  args.foreach(println)
}

printArgs4(args)

// going all-in functional style
// Now the function has referential transparency, no var, no side-effects
def formatArgs(args: Array[String]) = args.mkString("\n")

println(formatArgs(args))

// Functional programming makes testing easier
val result = formatArgs(Array("One", "Two", "Three"))
assert(result == "One\nTwo\nThree") // we wouldn't be able to do that with imperative code