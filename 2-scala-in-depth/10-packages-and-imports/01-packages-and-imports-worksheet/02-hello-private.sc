class Outer {
  def sayHello() = println("Hi, I'm outer")
  private def hiddenGreeting = println("Hello to Jason")

  class Inner {
    // (new Outer).hiddenGreeting() // not accessible from outside the class
  }
}

val outer = new Outer
outer.sayHello()
// outer.hiddenGreeting() // not available

