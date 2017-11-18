# 002 &mdash; Hello, ScalaTest
> n/a

## Description

ScalaTest is one of the testing frameworks available for Scala. It is prepared to fit many testing styles, including *JUnit style* tests.

```scala
import org.scalatest.FunSuite

class MySuite extends FunSuite {

  test("string width must be greater than 10") {
    val str = s"Hello to Jason Isaacs"
    assert(s.length > 10)
  }
}
```

With ScalaTest, you extend a `Suite` trait and mix in traits. In the example above, the test is created by extending the `FunSuite` (for function based suite).

There are runners for ScalaTest for *IntelliJ IDEA* and *Eclipse* and build tools like *sbt* and *Maven*, but you can also run a suite simply by invoking `execute` on it:

```scala
(new MySuite()).execute()
```

It is possible to tell ScalaTest what was the expected result of an assertion using `assertResult` method:

```scala
  test("List was expected to be 4 elements long") {
    assertResult(4) {
      List(1, 2, 3).length
    }
  }
```

You can also test for expected exceptions:
```scala
  test("check an exception is raised") {
    assertThrows[ArithmeticException] {
      val x = 5 / 0
    }
  }
```

### DiagrammedAssertions
Supposedly, you can enable a mode for which you get more information on the failed assertions:
```scala
import org.scalatest.Assertions._
import org.scalatest.DiagrammedAssertions._ 

test("List must contain 4 (with diagrammed assertions)") {
  assert(List(1, 2, 3).contains(4))
}
```

However, at least when tests are run from sbt, i haven't been able to see it in action.

### Test as Specifications
Writing test specs is a *BDD (behavior-driven development)* testing style that puts emphasis on writing human-readable specifications of the expected behavior of the code, along with a piece of source code that verifies the specified behavior.

```scala
class ElementSpec extends FlatSpec with Matchers {

  "A UniformElement" should "have width equal to the passed value" in {
    val e = elem('x', 2, 3)
    e.width should be (2)
  }

  it should "have height equal to the passed value" in {
    val e = elem('x', 2, 3)
    e.height should be (4)
  }
```

In a `FlatSpec` you write tests as *specifier clauses*, starting with a name for the *subject under test* as a string (i.e. "A UniformElement"), followed by `should`, `must` or `can`, followed by a string that identifies the specific behavior being tested on the subject, followed by `in` and the piece of code that tests the behavior.

In subsequent tests, you use `it` to refer to the *subject under test*.

Note also that ScalaTest provides many matchers in its DSL that lets you write assertions like natural language as in `e.width should be (2)`.


## Configuring the Application for your tests

ScalaTest must be defined as a dependency on your build properties file `build.sbt`. By reading the official [sbt documentation](http://www.scala-sbt.org/1.0/docs/Basic-Def.html) I figured out that this is the most sensible way to do it:

```scala
val scalatest = "org.scalatest" %% "scalatest" % "3.0.4" % "test"

lazy val commonSettings = Seq(
  organization := "com.github.sergiofgonzalez",
  version := "0.0.0-SNAPSHOT",
  scalaVersion := "2.12.4"
)

lazy val root = (project in file("."))
  .settings(
    commonSettings,
    name := "HelloScalaTestApp",
    libraryDependencies += scalatest
  )
```

It must be also noted that in order for your test to recognize the downloaded artifact, the tests must be placed under `src/test/scala`. 

Note also that I did not modify the `Dependencies.scala` file that is found under `./project`.

## Running the Application
Type `sbt` and then...

To run all the tests:
```bash
sbt:sbt:HelloScalaTestApp> test
```

To run a particular test:
```bash
sbt:PackageObjectsApp> testOnly MyFunSuite
```

Note how when using `testOnly` you have to provide the test class name rather than the filename.