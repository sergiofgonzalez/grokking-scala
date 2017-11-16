
/* basic equality checks */
1 == 2
1 != 2
2 == 2

val aList = List(1, 2, 3)
val bList = List(1, 2, 3)
aList == bList

1 == 1.0

List(1, 2, 3) == "hello"

/* == does not fail on null */
List(1, 2, 3) == null
null == List(1, 2, 3)

/* comparison may yield true on different objects if their contents are the same */
("he" + "llo") == "hello"
