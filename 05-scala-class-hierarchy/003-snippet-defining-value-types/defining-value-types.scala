
/*
  Creating a value class
*/

class Dollars(val amount: Int) extends AnyVal {
  override def toString() = s"$${amount}"
}

val someMoney = new Dollars(100000)
someMoney.amount

class SwissFrancs(val amount: Int) extends AnyVal {
  override def toString() = s"$amount CHF"
}

val someSwissMoney = new SwissFrancs(1000)
someSwissMoney.amount

