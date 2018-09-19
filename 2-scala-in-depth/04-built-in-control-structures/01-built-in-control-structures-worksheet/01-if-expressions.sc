val oneItemList = "one" :: Nil // same as List("one")
val severalItemsList = "one" :: "two" :: "three" :: Nil // same as List("one", "two", "three")
val noItemsList = Nil // same as List.empty, same as List()

println(if (noItemsList.size == 1) s"${noItemsList.size} result found" else s"${noItemsList.size} results found")
println(if (oneItemList.size == 1) s"${oneItemList.size} result found" else s"${oneItemList.size} results found")
println(if (severalItemsList.size == 1) s"${severalItemsList.size} result found" else s"${severalItemsList.size} results found")