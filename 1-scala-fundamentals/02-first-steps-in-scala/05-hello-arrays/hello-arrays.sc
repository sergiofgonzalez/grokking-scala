
// Explicit instantiation of Array of Strings with length 3
val greetStrings: Array[String] = new Array[String](3)
greetStrings(0) = "Hello"
greetStrings(1) = "to"
greetStrings(2) = "Jason Isaacs"

// note that not even `i` has to be declared
for (i <- 0 to 2)
  println(greetStrings(i))

// Type specification for the array is not needed
val greetStrings = new Array[String](3)
greetStrings(0) = "Hello"
greetStrings(1) = "to"
greetStrings(2) = "Jason Isaacs"

// note that not even `i` has to be declared
for (i <- 0 to 2)
  println(greetStrings(i))

// Note the tricks in Scala for the invocation of methods receiving a single parameter
0 to 2
(0).to(2)

1+2
(1).+(2)

println("hello")

// println "hello" // this does not work, as there is no object to bind `println`
Console println "hello" // but thiS DOES work

// This is a much better way to initialize the arrays
val numNames = Array("zero", "one", "two", "three")

numNames.foreach(println)