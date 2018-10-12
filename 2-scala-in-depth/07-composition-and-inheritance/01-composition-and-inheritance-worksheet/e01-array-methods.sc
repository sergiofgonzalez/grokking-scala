val nums1 = Array(0, 1, 2, 3)
val nums2 = Array(4,  5)

val nums = nums1 ++ nums2

/* Iterating over an array */
for (i <- 0 until nums.length)
  println(s"nums($i): ${nums(i)}")


/* zip operation */

// the smallest array is the one driving the results length
nums1 zip nums2
nums2 zip nums1

val result =
  for ((num1, num2) <- nums1 zip nums2)
    yield num1.toString + num2.toString

for (item <- result)
  println(s"item found: $item")

/* fill operation:
  fill[T](n: Int)(elem: => T)

 returns the array that contains the result of some element
 computation a number of times

 In plain imperative words, it will create an array with n elements
 in which each of the elements is elem.
*/
val line = "xyz"
val height = 3
val filledArray = Array.fill(height)(line)