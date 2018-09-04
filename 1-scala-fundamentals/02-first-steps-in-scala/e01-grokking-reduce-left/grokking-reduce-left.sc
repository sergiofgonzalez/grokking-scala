// I typically name the parameters according to their "imperative" meaning
// For example
val nums = List(0, 1, 2, 3, 4, 5)

nums.reduceLeft((accumulator, element) => accumulator + element)

// That is, reduceLeft argument is a function that is given an
// accumulator variable and the current element, so if your
// job is to sum the given numbers, you just do accumulator + element

// However, the ScalaDocs give a wonderful functional definition
// I have always missed:
// reduceLeft applies a binary operator to all elements of the
// sequence, going left to right:
// op( op( ... op(x_1, x_2) ... x_n-1), x_n)

// So, when applied to List(1) with + being the op
// +(0, 1)

// for List(1, 2)
// +(+(0, 1), 2) = +(1, 2) = 3

// for List(1, 2, 3)
// +(+(+(0, 1), 2), 3) = +(+(1, 2), 3) = +(3, 3) = 6

val list = List(1, 2, 3, 4, 5)
list.reduceLeft((a, b) => a + b)

// Now the question how is reducing lists of size 1
// easy:
// for lists of size one, the first element is returned
// without applying the function
val singleElemList = List(5)
singleElemList.reduceLeft((a, b) => a + b)
singleElemList.reduceLeft((a, b) => a * b)
singleElemList.reduceLeft((a, b) => if (a > b) a else b)

// It becomes evident with this stupid reduction
singleElemList.reduceLeft((a, b) => a.toString.length + b.toString.length) // -> 5, the function is never applied
list.reduceLeft((a, b) => a.toString.length + b.toString.length)  // because a.toString.length = 1, b.toString.length = 1


