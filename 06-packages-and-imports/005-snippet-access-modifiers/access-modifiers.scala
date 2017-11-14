/*
  Access modifiers: fine-control of member visibility
*/

package rockets

package navigation {
  private[rockets] class Navigator { // this class is visible in all classes and objects in package rockets
    protected[navigation] def useStarChart() = {}
    class LefOfJourner {
      private[Navigator] val distance = 100
    }
    private[this] var speed = 200 // means speed is only visible within the same object
  }
}

package launch {
  import navigation._
  object Vehicle {
    private[launch] val guide = new Navigator // new Navigator is permitted because defined as private[rockets] class Navigator
  }
}