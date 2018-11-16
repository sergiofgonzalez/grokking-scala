

def getSize(x: Any): Int = {
  if (x.isInstanceOf[String]) {
    val s = x.asInstanceOf[String]
    s.size
  } else {
    -1
  }

}

getSize("Hello to Jason Isaacs")
getSize(List(1, 2, 3))
getSize(Map(1-> "one", 2-> "two"))