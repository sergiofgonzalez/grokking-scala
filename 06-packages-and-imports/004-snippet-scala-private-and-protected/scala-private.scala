/*
  Scala's private is more restrictive than Java's: only available within 
  the class that contains the member definition
*/

class Outer {
  class Inner {
    private def f() = { println("f") }
    
    class InnerInner {
      f() // OK
    }
  }
  (new Inner).f() // Err: method f() is not visible
}