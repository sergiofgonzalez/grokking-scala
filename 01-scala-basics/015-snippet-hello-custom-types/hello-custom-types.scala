
def factorial(n: BigInt) : BigInt =
  if (n == 0) 1 else n * factorial(n - 1)

factorial(5)

factorial(30)