val nums = Array("1", "2", "3", "4", "5")
val chars = Array("a", "b", "c", "d", "e")

nums zip chars

/*
  Manual zipping
*/
val zipped = new Array[String](nums.length)
for (i <- 0 until nums.length)
  zipped(i) = nums(i) + chars(i)
zipped

/*
  Functional
*/
val zipped = for (
  (num, char) <- nums zip chars
) yield num + char

