/* Arrays are mutable */
val array = new Array[String](3)
array(0) = "Hello"
array(1) = "to"
array(2) = "Jason Isaacs"

array

/* you can iterate over the arrays using foreach */

array.foreach(println)
array.foreach(println(_))


/* Lists are immutable */
val list = List("Hello", "to", "Jason", "Isaacs")
list

/* You can also iterate using foreach */
list.foreach(println)

/* 
  The cons operator `::` can be used to create lists from list or to destructure existing lists:
  `::` creates a new List with all the existing elements from the right plus the new element added to the front
*/
val nums = List(1, 2)
val newNums = 3 :: nums
newNums

/* 
  The operator `:+` can be used to append elements to a List
*/

val anotherNums = nums :+ 3
anotherNums

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