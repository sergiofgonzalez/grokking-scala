val args = List("Scala", "is", "fun")

// The simplest
var i = 0
while (i < args.length) {
  println(args(i))
  i += 1
}

// printing in one line
var i = 0
while (i < args.length) {
  if (i != 0)
    print(" ")
  print(args(i))
  i += 1
}