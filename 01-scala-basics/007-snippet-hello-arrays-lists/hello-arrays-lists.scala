/* Arrays are mutable */
val array = new Array[String](3)
array(0) = "Hello"
array(1) = "to"
array(2) = "Jason Isaacs"

array
array(1)

/*
  When the compiler sees array(1) it transforms it into array.apply(1)
*/
array.apply(1)

/*
  ... and when it sees array(0) = "Hullo" it transforms it into array.update(0, "Hullo")
*/
array.update(0, "Hullo")
array

/*
  More succinct initialization
*/
val actors = Array("Jason", "Idris", "Bill")

/* you can iterate over the arrays using foreach */
array.foreach(str => println(str))
array.foreach(println) // If a function literal consist of one statemet that takes a single argument!!!
array.foreach(println(_))


/* Lists are immutable */
val list = List("Hello", "to", "Jason", "Isaacs")
list

/* You can also iterate using foreach */
list.foreach(println)

/* 
  The cons method `::` can be used to create lists from list or to destructure existing lists:
  `::` creates a new List with all the existing elements from the right plus the new element added to the front
*/
val nums = List(1, 2)
val newNums = 3 :: nums
newNums

/* 
  The method `:+` can be used to append elements to a List
*/

val anotherNums = nums :+ 3
anotherNums

/*
  The method `:::` can be used for list concatenation
*/
val oneTwo = List(1, 2)
val threeFour = List(3, 4)
val oneTwoThreeFour = oneTwo ::: threeFour

oneTwoThreeFour


/*
  Nil represents an empty list
*/

val myList = "Hello" :: "to" :: "Jason" :: "Isaacs" :: Nil
myList

/*
  You can use filter and filterNot to remove certain elements from a List
*/
val oneToTen = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
println(s"even: ${ oneToTen.filter(_ % 2 == 0) }")
println(s"odd : ${ oneToTen.filterNot(_ % 2 == 0 )}")

/*
  You can access individual members of a List using the same syntax
  you use in arrays
*/
oneToTen(5)

/* Scala Lists and Java lists are not compatible, but can be converted */
val scalaList = List(1, 2, 3)
val aJavaList = java.util.Arrays.asList(scalaList.toArray) // <- this creates a Java with a single element, that being the list
aJavaList.size
aJavaList.get(0)

// you can use the special :_* to flatMap the list
val bJavaList = java.util.Arrays.asList(scalaList.toArray:_*)
bJavaList.size
bJavaList.get(0)


/*
  More List methods
*/
val words = List("Here", "I", "am", "once", "again")

// count(pred) : count the number of elements satisfying the predicate
words.count(w => w.length > 4)

// drop(num) : returns a new list in which the num first elements have been removed
words.drop(3)

// dropRight(num) : returns a new list in which the num last elements have been removed
words.dropRight(2)

// exists(pred) : returns whether there is an element in the list satisfying the pred
words.exists(w => w.length == 1)
words.exists(w => w.length > 5)

// filter(pred) : returns a new list containing all the elems of the original list satisfying the pred
words.filter(w => w.length >3)

// forall(pred) : returns true if all elems in the list satisfy the pred
words.forall(w => w.length % 2 == 0)

// foreach(action) : executes an action on each of the elements of the list
words.foreach(w => print(w))

// head/last : returns the first/last element of the list
words.head
words.last

// init : returns a list with all the elements but the last one
words.init

// isEmpty : returns true if the list is empty
words.filter(w => w.length > 10).isEmpty

// length : returns the number of elements in the list
words.length

// map(fn) : returns a list in which all the elements have been applied the fn
words.map(w => w.length)

// mkString(str) : returns the string that results of joining the elements of the list by str
words.map(w => w.length).mkString("~")

// filterNot(pred) : returns a list with all the elems for which the pred is not satisfied
words.filterNot(w => w.length >= 3) 

// reverse : returns a list with the elements reversed
words.filterNot(w => w.length >= 3).reverse

// sorted(alg) : returns a list with the elements sorted according to the comparison alg
words.sorted((w1: String, w2: String) => w1.length - w2.length)

// tail : returns a list with all the elements but the first
words.tail