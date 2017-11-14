
/* 
  Scala's protected access modifier grants access to fields 
  for subclasses of the defining class 
*/
package p {
  class Super {
    protected def g() = { println("g") }
  }
  class Sub extends Super {
    g() // OK
  }
  class OtherInSamePackage {
    (new Super).g() // Err: Access to protected is not permitted, even when in the same package
  }
}