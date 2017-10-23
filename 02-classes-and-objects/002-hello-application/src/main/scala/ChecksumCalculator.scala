class ChecksumAccumulator {
  private var sum = 0;
  def add(b: Byte): Unit = sum += b  // procedure
  def checksum(): Int = return ~(sum & 0xFF) + 1
}

// Companion object for class ChecksumAccumulator
import scala.collection.mutable

object ChecksumAccumulator {
  private val cache = mutable.Map.empty[String, Int]

  def calculate(s: String): Int =
    if (cache.contains(s))
      cache(s)
    else {
      val acc = new ChecksumAccumulator
      for (ch <- s)
        acc.add(ch.toByte)
      val cs = acc.checksum() // checksum for the string
      cache += (s -> cs) // Add the element s -> cs to the cache map
      cs
    }
}