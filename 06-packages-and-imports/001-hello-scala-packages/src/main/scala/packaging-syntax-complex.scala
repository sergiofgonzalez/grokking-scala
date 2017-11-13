package com.github.sergiofgonzalez {
  
  package persistence {
    class PersonRepository

    package tests {
      class PersonRepositoryTests {
        def testSomeScenario() = {
          // PersonRepository can be instantiated without explicit import
          val p = new PersonRepository
        }
      }
    }
  }
}