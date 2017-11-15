class Rocket {
  import Rocket.fuel  // refers to the companion object
  private def canGoHomeAgain = fuel > 20
}

object Rocket {
  private def fuel = 10

  def chooseStrategy(rocket: Rocket) = {
    if (rocket.canGoHomeAgain) // refers to class
      goHome()
    else
      pickAStar()
  }

  def goHome() = {}
  def pickAStar() = {}
}