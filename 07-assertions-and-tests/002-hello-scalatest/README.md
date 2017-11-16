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

## Running the Application
Type `sbt` and then type:

```bash
sbt:PackageObjectsApp> run 
```

You can return to the command line just by typing: 
```bash
sbt:PackageObjectsApp> exit
```
