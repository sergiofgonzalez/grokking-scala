val oneTwo = List(1, 2)
val twoThree = List(2, 3)
val oneTwoThreeFour = oneTwo ::: twoThree // `:::` is the list concatenation operator

println(oneTwo)
println(twoThree)
println(oneTwoThreeFour)

// the `::` cons operator is use to prepend a new element to the beginning of a list
val oneTwoThree = 1 :: twoThree


// Using Nil and the cons operator to initialize lists
val oneFourSix = 1 :: 4 :: 6 :: Nil

/* list methods and usages */
val emptyList = Nil
val anotherEmptyList = List()
if (emptyList == anotherEmptyList) println("Equal")

val thrill = "Will" :: "fill" :: "until" :: Nil

// accessing the element at index 2
thrill(2)

// counting the elements whose length is 4
thrill.count(s => s.length == 4)

// drop the initial 2 elements
thrill.drop(2)

// drop the last 2 elements
thrill.dropRight(2)

// checks for existence of an element of length 4
thrill.exists(s => s.length == 4)

// filter elements whose length is not 4
thrill.filter(s => s.length == 4)

// checks if all the elements ends with "l"
thrill.forall(s => s.endsWith("l"))

// apply an action to all the elements
thrill.foreach(s => println(s))
thrill.foreach(println)

// get the first element of the list
thrill.head

// get the list of all the elements but the last
thrill.init

// checks if the list is empty
thrill.isEmpty
emptyList.isEmpty
anotherEmptyList.isEmpty

// get the last element of the list
thrill.last

// get a list resulting of transforming each of the elements
thrill.map(s => s + "y")

// make a string with the elements of the list
thrill.mkString(", ")

// filter all the elements not having length 4
thrill.filterNot(s => s.length == 4)

// reverse the elements of the list
thrill.reverse

// sort the elements of the list
thrill.sortWith((s, t) => s.charAt(0).toLower < t.charAt(0).toLower)

// get the list of all the elements minus the first
thrill.tail