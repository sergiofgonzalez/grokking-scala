import Element.elem

object ElementClientApp extends App {

  /* Now we have a way to see the representation of Element's as Strings */
  println((elem(Array("singleElement"))).toString)
  println((elem(Array("elem1", "elem2"))).toString)
  println((elem(Array("elem1", "elem2", "elem3"))).toString)

  println((elem("hello")).toString)

  println((elem('*', 10, 2)))


  /* Now we test the methods */
  println(elem("hello") above elem("world"))
  println(elem("hello ") beside elem("to ") beside elem("Jason Isaacs"))


  val line = elem('*', "hello to Jason Isaacs".length, 1)
  val greeting = elem("hello ") beside elem("to ") beside elem("Jason Isaacs")

  println(line above greeting)

  /* this implementation needs an enhancement though */
  println(elem("hello to") above elem("world!"))
  println("========================")
  println(elem(Array("one", "two")) beside elem(Array("one")))
}