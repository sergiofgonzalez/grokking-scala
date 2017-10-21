val map = Map(("key1", "value1"), ("key2", "value2"))

map("key1")
map.contains("key2")

/* Elements of the maps are tuples */
val tuple2 = (1, List("one", "uno", "ein"))
tuple2

val aMap = Map(tuple2)


/* but there's a more expressive way to use them */
var capital = Map("US" -> "Washington", "France" -> "Paris")
capital += ("Japan" -> "Tokyo")

println(s"The capital of France is ${ capital("France") }")