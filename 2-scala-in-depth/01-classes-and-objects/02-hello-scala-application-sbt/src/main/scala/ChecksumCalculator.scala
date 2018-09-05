import scala.collection.mutable

class ChecksumCalculator {
  private var sum = 0
  def add(b: Byte): Unit = sum += b
  def checksum(): Int = ~(sum & 0xFF) + 1
}

object ChecksumCalculator {
  private val cache = mutable.Map.empty[String, Int]
  def calculate(s: String): Int =
    if (cache.contains(s))
      cache(s)
    else {
      val acc = new ChecksumCalculator
      for (c <- s)
        acc.add(c.toByte)
      val checksum = acc.checksum()
      cache += (s -> checksum)
      checksum
    }
}