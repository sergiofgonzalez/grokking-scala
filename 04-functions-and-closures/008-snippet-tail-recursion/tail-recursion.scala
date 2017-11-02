/*
  tail-recursive function
*/

// classic, not tail-recursive implementation
def factorial(n: Int): Int = {
  if (n == 1)
    1
  else
    n * factorial(n -1)  // not tail-recursive: it performs a computation after the recursive call
}

factorial(1)
factorial(2)
factorial(3)
factorial(4)
factorial(5)
println("==")

// tail-recursive
def factorial(n: Int, acc: Int = 1): Int = {
  if (n == 1)
    acc
  else
    factorial(n - 1, n * acc)
}

factorial(5)
println("==")


/*
  Checking the tail-recursive optimization
*/
def boomV1(n: Int): Int = 
  if (n == 0) throw new Exception("Fabricated Exception!") else boomV1(n - 1) + 1  // not tail-recursive

boomV1(5)
println("==")

def boomV2(n: Int): Int =
  if (n == 0) throw new Exception("Fabricated Exception!") else boomV2(n - 1) // tail-recursive
boomV2(5)
println("==")
