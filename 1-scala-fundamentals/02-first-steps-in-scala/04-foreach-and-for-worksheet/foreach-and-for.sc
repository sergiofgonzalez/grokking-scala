val args = List("Scala", "is", "fun")

/* using foreach with function literals */

// explicit
args.foreach((arg: String) => println(arg))

// simplified
args.foreach(arg => println(arg))

// simplest
args.foreach(println)

/* for-expression */
for (arg <- args)
  println(arg)