
import com.github.sergiofgonzalez.MyClass
import com.github.sergiofgonzalez.domain.Person
import com.github.sergiofgonzalez.persistence.PersonRepository
import com.github.sergiofgonzalez.persistence.tests.PersonRepositoryTests

object ScalaPackagesRunner {

  def main(args: Array[String]): Unit = {
    // instantiating a class with package clause at the top of the file
    val myObj = new MyClass


    // instantiating a class with package defined using packaging syntax
    val person = new Person

    // instantiating the classes with packaging syntax
    val personRepo = new PersonRepository
    val personRepoTests = new PersonRepositoryTests

    println("-- done!!")
  }
}
