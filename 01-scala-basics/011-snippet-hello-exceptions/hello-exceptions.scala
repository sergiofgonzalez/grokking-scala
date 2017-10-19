/*
   Define a function that receives an  int argument and classifies it as
   + a number between 0 and 100
   + a number between 101 and 1000
   + throws an IllegalArgumentException otherwise
*/

def classify(n: Int) = n match {
  case group1 if (n >= 0 && n <= 100) => println(s"$n is a number between 0 and 100")
  case group2 if (n >= 101 && n <= 1000) => println(s"$n is a number between 101 and 1000")
  case _ => throw new IllegalArgumentException(s"$n cannot be classified")
}

try {
  classify(5)
  classify(555)
  classify(5555)
} catch {
  case e: IllegalArgumentException => s"Error calling classify: ${ e.getMessage }"
}
