/* version I: Imperative version */
//def containsNeg(nums: List[Int]): Boolean = {
//  var exists = false
//  for {
//    num <- nums
//    if (num < 0)
//  } exists = true
//  exists
//}


/* version II: leveraging exists */
def containsNeg(nums: List[Int]): Boolean =
  nums.exists(_ < 0) // same as nums.exists(num => num < 0)


containsNeg(List(1, 4, 5, 10))
containsNeg(List(1, 6, -4, 10))

def containsOdd(nums: List[Int]): Boolean = nums.exists(_ % 2 == 1)
containsOdd(List(2, 4, 6, 8 , 10))
containsOdd(List(2, 4, 5, 8, 10))