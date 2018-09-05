import scala.collection.mutable

class ChecksumAccumulator {
  private var sum = 0
  def add(b: Byte): Unit= sum += b
  def checksum(): Int = ~(sum & 0xFF) + 1
}

object ChecksumAccumulator {
  private val cache = mutable.Map.empty[String, Int]
  def calculate(s: String) =
    if (cache.contains(s))
      cache(s)
    else {
      val acc = new ChecksumAccumulator
      for (c <- s)
        acc.add(c.toByte)
      val checksum = acc.checksum()
      cache += (s -> checksum)
      checksum
    }
}

ChecksumAccumulator.calculate("Hello")
ChecksumAccumulator.calculate("to")
ChecksumAccumulator.calculate("Jason")
ChecksumAccumulator.calculate("Isaacs")
ChecksumAccumulator.calculate("Hello")