
object FindLongLines {

  /* 
    args:
      - the first parameter is the width
      - the remaining parameters are all the files on which to apply the LongLines.process
  */
  def main(args: Array[String]): Unit = {
    val width = args(0).toInt
    for (arg <- args.drop(1))
      LongLines.processFile(arg, width)
  }
}
