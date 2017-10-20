package example

object Hello extends App {
  require(args.size >= 2, s"at minimun this application expects two arguments, but received ${ args.size }")
  args.foreach(arg => println(s"arg=$arg"))
  println(s"args.head=${ args.head }")
  println(s"args.last=${ args.last }")
}
