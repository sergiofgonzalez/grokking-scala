/* Custom value types */

class Dollars(val amount: Int) extends AnyVal {
  override def toString: String = s"$amount"
}

val money = new Dollars(100)
money.amount