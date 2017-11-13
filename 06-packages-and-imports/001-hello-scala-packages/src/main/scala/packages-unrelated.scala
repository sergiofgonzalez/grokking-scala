package launch {
  class Booster3
}

package rockets {
  package navigation {
    package launch {
      class Booster1
    }
    class MissionControl {
      val booster1 = new launch.Booster1 // OK: enclosing packages (no need to refer to rockets.navigation.launch)
      val booster2 = new rockets.launch.Booster2 // OK: using complete path to refer to Booster2
      val booster3 = new _root_.launch.Booster3 // OK: need _root_ to start from unnamed package
    }
  }
  package launch {  // adding contents to existing package
      class Booster2
  }
}