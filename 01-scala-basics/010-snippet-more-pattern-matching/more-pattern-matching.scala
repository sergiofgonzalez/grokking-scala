/*
  Function that prints the type of the argument it receives

  Note that in this case, we're force to use value that will bind the obj that matches
*/

def printType(obj: AnyRef) = obj match {
  case s: String => println(s"This is a String: $s")
  case l: List[_] => println(s"This is a List: $l")
  case a: Array[_] => println(s"This is an Array: ${ a.mkString(",") }")
  case d: java.util.Date => println(s"This is a Date <3")
}

printType("foobar")
printType(Nil)
printType(List(1, 2, 3))
printType(new Array[Int](10))
printType(new java.util.Date())

/*
  Inline pattern matching as an operator:
    pattern matching is used to filter out elements beyond the 2nd in a List
*/
List(1, 2, 3) match {
  case first :: second :: rest => List(first, second)
  case _ => {
    println("fallback option")
    Nil
  }
}

List(1) match {
  case first :: second :: rest => List(first, second)
  case _ => {
    println("fallback option")
    Nil
  }
}


/* 
  Pattern matching with guards:
    Function that returns whether a given number is between 0 and 10, between 11 and 100 or greater than 100
*/
def rangeMatcher(n: Int) = n match {
  case within10 if (within10 <= 10) => println(s"$n is within 0 to 10")
  case within100 if (within100 <= 100) => println(s"$n is within 11 to 100")
  case beyond100 if (beyond100 < Int.MaxValue) => println(s"$n is greater than 100")
}

rangeMatcher(5)
rangeMatcher(55)
rangeMatcher(555)


/*
  Ordinal for any number
*/

val suffixes = List("th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th")

def ordinal(n: Int) = n match {
  case tenTo20 if 10 to 20 contains tenTo20 => s"${tenTo20}th"  // 10th, 11th, 12th, 13th... 20th
  case rest => s"${ rest }${ suffixes( rest % 10 ) }"
}

ordinal(1)
ordinal(3)
ordinal(15)
ordinal(52)

