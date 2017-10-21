
def parseArgs(args: Array[String]): Map[String, List[String]] = {
  def nameValuePair(paramName: String) = {
    def values(commaSeparatedValues: String) = commaSeparatedValues.split(",").toList
    val index = args.indexWhere(_ == paramName)
    (paramName, if (index == -1) Nil else values(args(index + 1)))
  }

  Map(nameValuePair("-d"), nameValuePair("-h"))
}

/*
  -d key1,value1 key2,value2
  -h headerKey1,hederValue1, headerKey2,headerValue2 headerKey3,headerValue3
*/
val args = new Array[String](4)
args(0) = "-d"
args(1) = "d1,d2"
args(2) = "-h"
args(3) = "h1,h2,h3"

parseArgs(args)