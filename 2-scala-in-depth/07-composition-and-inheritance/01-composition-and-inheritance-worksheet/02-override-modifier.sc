/* override modifier is not needed when overriding abstract methods */
abstract class Rabbit(val kind: String) {
  def speak(): Unit
}

class KillerRabbit extends Rabbit("killer") {
  def speak() = println(s"Hi, I'm a $kind rabbit")
}

val kr = new KillerRabbit
kr.speak()

/* override modifier is needed when overriding concrete methods */
class KillerWhiteRabbit extends KillerRabbit {
//  def speak(): Unit = println(s"Hi, I'm a killer white rabbit") // Err: method needs override modifier
  override def speak(): Unit = println(s"Hi, I'm a killer white rabbit")
}

val kwr = (new KillerWhiteRabbit()).speak()