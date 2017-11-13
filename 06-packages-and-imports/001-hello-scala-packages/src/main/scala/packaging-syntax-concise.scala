package com.github.sergiofgonzalez.services {
  class PersonService
}

package com.github.sergiofgonzalez.services.rest {
  class PersonController {
    def doSomething() = {
      // val personService = new PersonService // Err: PersonService is not in scope
    }
  }
}