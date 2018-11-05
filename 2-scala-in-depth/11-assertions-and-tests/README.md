# Part 2 &mdash; Scala In Depth: Assertions and Tests
> checking that Scala code behaves as expected

---
+ Native support for assertions in Scala: `assert` and `ensuring`
+ Introducing *ScalaTest* framework: Test suites and Behavior-driven development. Feature Specs.
+ Introducing *Specs2* framework: Acceptance and Unit specifications
+ Introducing *ScalaCheck* for property-based testing
---

## Assertions
Assertions in Scala are written as calls of a predefined method `assert`. The expression `assert(condition)` throws an `AssertionError` if condition does not hold true. There's also a version that lets you give an *explanation* `assert(condition, explanation)`, where explanation is of type `Any`.

```scala
def above(that: Element): Element = {
  val this1 = this widen that.width
  val that1 = that widen this.width
  assert(this1.width == that1.width)
  eleme(this1.contents ++ that1.contents)
}
```

There's also a method `ensuring` in `Predef` that lets you perform assertions in a very concise way:

```scala
private def widen(w: Int): Element = {
  if (w <= width)
    this
  else {
    val left = elem(' ', (w - width) / 2, height)
    val right = elem(' ', w - width - left.width, height)
    left beside this beside right
  } ensuring (w <= _.width)
}
```

The `ensuring` method can be used with any result type because of an implicit conversion. Although it looks as if we're invoking `ensuring` on `widen`'s result, we're actually invoking ensuring on a type to which `Element` is implicitly converted.

The method `ensuring` takes one argument &mdash; a predicate functiona that takes a result type and returns `Boolean`, and passes the result to the predicate. If the predicate returns true, ensuring will return the result, otherwise, it will throw an `AssertionError`.

In the previous example, the predicate is `w <= _.width`. The underscore is a placeholder for the argument passed to the predicate. If the width passed to `widen` (that is `w`) is less than or equal to the width of the resulting `Element`, the predicate will result in true and `ensuring` will result in the Element on which it was invoked.

Assertions can be enabled and disabled using the JVM's `-ea` and `-da` flags respectively.

## Testing in Scala
In this section we'll do a quick tour of Scala tools for testing.

### ScalaTest
ScalaTest is the most flexible Scala test framework, as it can be customized to solve different problems and fits for many different approaches.

For example, the following piece of code would be familiar for *JUnit* users:

```scala
import org.scalatest.FunSuite
import Element.elem

class ElementSuite extends FunSuite {
  test("elem result should have passed width") {
    val ele = elem('x', 2, 3)
    assert(ele.width == 2)
  }
}
```

See [Testing Layout Elements with ScalaTest](./02-testing-layout-elements-scalatest-app-sbt/README.md) for an example.


The central concept in *ScalaTest* is the suite &mdash; a collection of tests. A test can be anything with a name that can start and eigher suceed, fail, be pending or canceled. The *trait* `Suite` is the central unit of composition in `ScalaTest`. The `Suite` declares *"lifecycle"* methods defining a default way to run tests, which can be overridden to customize how tests are written and run.

*ScalaTest* offers style traits to support different testing styles. It also provides *mixin* trait and define test suites by composing *Suite* instances.

For example, `FunSuite` supports the *function style*: a `test` method is defined in this trait which will be invoked by the primary constructor of `ElementSuite`. The name of the test is passed between parentheses and the test code itself between curly braces.

*ScalaTest* is integrated into the build tools such as *sbt* and *Maven* and *IDEs*.

#### Informative Failure Reports
ScalaTest can be configured to use very detailed failure reports. By default, if you run the following test methods:

```scala
import org.scalatest.FunSuite

class MySimpleScalaTestSuite extends FunSuite {

  test("width must be 3") {
    val width = 2
    assert(width == 3)
  }

  test("list must contain 4") {
    assert(List(1, 2, 3).contains(4))
  }
}
```

You will get the following report:

```
2 did not equal 3
ScalaTestFailureLocation: MySimpleScalaTestSuite at (MySimpleScalaTestSuite.scala:7)
Expected :3
Actual   :2
 <Click to see difference>

org.scalatest.exceptions.TestFailedException: 2 did not equal 3

List(1, 2, 3) did not contain 4
```

This can be improved using `DiagrammedAssertions`:

```scala
import org.scalatest.FunSuite
import org.scalatest.DiagrammedAssertions

class MySimpleScalaTestSuite extends FunSuite with DiagrammedAssertions {

  test("list must contain 4") {
    assert(List(1, 2, 3).contains(4))
  }
}
```

That will display:
```
assert(List(1, 2, 3).contains(4))
       |    |  |  |  |        |
       |    1  2  3  false    4
       List(1, 2, 3)
```


Note that ScalaTest's assert methods do not differentiate between the actual and expected results. If you want to emphasize the difference, you can use the `assertResult` method:

```scala
  test("expected width must be 3") {
    assertResult(3) {
      val width = 2
      width
    }
  }
```

It is also possible to check that a method has thrown an exception:

```scala
  test("should throw an exception") {
    assertThrows[IllegalArgumentException] {
      throw new IllegalArgumentException("fabricated exception")
    }
  }
}
```

ScalaTest also provides an `intercept` method that lets you inspect the exception further:

```scala
  test("should get fabricated exception message") {
    val caught = intercept[IllegalArgumentException] {
      throw new IllegalArgumentException("fabricated exception")
    }

    assert(caught.getMessage == "fabricated exception")
  }
```

#### Tests as Specifications
*Behavior-driven development (BDD)* emphasize writing human-readable specifications of the expected behavior of code and accompanying tests. ScalaTest also supports this approach:

```scala
import org.scalatest.{DiagrammedAssertions, FlatSpec, Matchers}
import Element.elem

class ElementSpec extends FlatSpec with Matchers with DiagrammedAssertions {

  "A UniformElement" should "have a width equal to the passed value" in {
    val ele = elem('x', 2, 3)
    ele.width should be (2)
  }

  it should "have a height equal to the passed value" in {
    val ele = elem('x', 2, 3)
    ele.height should be (3)
  }

  it should "throw an IllegalArgumentException if passed a negative width" in {
    an [IllegalArgumentException] should be thrownBy {
      elem('x', -2, 3)
    }
  }
}
```

In this approach, you write tests as *specifier clauses*. You start by writing a name for the *subject under test* as a string (`"A UniformElement"`), followed by `should`, `must` or `can` then a string that specifies the behavior, then `in`, followed by the code that tests the specified behavior.

Note that the previous listing also illustrates ScalaTest *matchers* DSL.

ScalaTest also supports a type of specifications that facilitate the communication between the people who decide what a software system should do, and the people that implement the software:

```scala
import org.scalatest._

class TvSetSpec extends FeatureSpec with GivenWhenThen {
  feature("TV power button") {
    scenario("User presses power button when TV is off") {
      Given("a TV set that is switched off")
      When("the power button is pressed")
      Then("the TV should switch on")
      pending
    }
  }
}
```

See how this testing style guide a conversation about software requirements:
+ You must identify specific features
+ Then specify those features in terms of scenarios
+ Then focus the conversation around what shoud happen when a given action is triggered

See how this style does not force you to implement the result right away, but rather you can label the test as *pending* until the implementation is available.

An example of this type of test can be found in [04 &mdash; Exploring ScalaTest BDD tests](./04-bdd-scalatest-app-sbt).

#### Organizing and Running Tests
In ScalaTest, you organize large test suites by nesting *Suites* inside *Suites*. When a *Suite* is executed, it will execute its nested *Suites* as well as its tests. The nested *Suites* will in turn execute their nested *Suites* and so on.

You can nest suites manually (using Scala code to nest the suites) or automatically (providing the package names to the ScalaTest's Runner).

### Specs2
The *Specs2* testing framework is another open source tool written in Scala that supports the BDD style of testing but with a different syntax.

*Specs2* supports two major writing styles: the *acceptance specification* and the *unit specification*.

This is an example of an *acceptance specification*:
```scala
import org.specs2._
import Element.elem

class ElementSpec extends Specification {
  def is = s2"""
    This is a specification to check the UniformElement behavior

    The UniformElement should
      have a width equal to the passed value                                $e1
      have a height equal to the passed value                               $e2
      throw an IllegalArgumentException if passed negative width            $e3

    """

  def e1 = {
    val ele = elem('x', 2, 3)
    ele.width must be_==(2)
  }

  def e2 = {
    val ele = elem('x', 2, 3)
    ele.height must be_==(3)
  }

  def e3 = {
    elem('x', -2, 3) must throwA[IllegalArgumentException]
  }
}
```

And this is a *unit specification*:
```scala
import Element.elem
import org.specs2._

class ElementUnitFlavorSpec extends mutable.Specification {
  "A UniformElement" >> {
    "should have a width equal to the passed value" in {
      val ele = elem('x', 2, 3)
      ele.width must be_==(2)
    }
    "should have a height equal to the passed value" >> { // >> is the same as in
      val ele = elem('x', 2, 3)
      ele.height must be_==(3)
    }
    "should throw an IllegalArgumentException if passed a negative value" in {
      elem('x', -2, 3) must throwA[IllegalArgumentException]
    }
  }
}
```

### ScalaCheck
ScalaCheck is another OSS framework that enables you to specify properties that the code under test must obey. For each property, ScalaCheck will generate data and execute assertions that check whether the property holds.

Condider the following example in which ScalaCheck capabilities are used from a ScalaTest:

```scala
import org.scalatest.WordSpec
import org.scalatest.prop.PropertyChecks
import org.scalatest.MustMatchers._
import Element.elem

class ElementSpec extends WordSpec with PropertyChecks {
  "elem result" must {
    "have passed width" in {
      forAll { (w: Int) =>
        whenever (w > 0) {
          elem('x', w, 3).width must equal (w)
        }
      }
    }
  }
}
```

The framework provides methods capable of generating the values and test each one of them to check that the condition holds true for all of them.

See [06 &mdash; BDD with Specs2](./06-scalacheck-app-sbt) for an example.


---
## You know you've mastered this chapter when...

+ You're comfortable using `assert` and `ensuring` in your code to perform assertions to check conditions in your methods. 
+ You know how to create simple tests extending from ScalaTest framework `FunSuite` trait. You understand several patterns involved when doing tests: using assertions, emphasizing results vs. expected, expecting exceptions, etc.
+ You understand how to do BDD specs using ScalaTest and you're comfortable using the *matchers DSL* to perform assertions using natural language. You're aware of the *feature specs* and how it facilitates the communication between the people deciding what a software system should do, and the people implementing it.
+ You're comfortable reading and writing tests using *Specs2* framework as well, and can differentiate the two styles it supports: *acceptance* and *unit* specs.
+ You understand the concept behind property-based testing, and know to how read and write ScalaCheck code.
---


## Projects

### [01 &mdash; Assertions](./01-assertions-worksheet)
IntelliJ worksheet project with several worksheet illustrating the concepts of the section.

### [02 &mdash; Testing Layout Elements with ScalaTest](./02-testing-layout-elements-scalatest-app-sbt)
SBT app illustrating *ScalaTest* framework concepts.

### [03 &mdash; Exploring ScalaTest Failure Reports](./03-scalatest-failure-reports-app-sbt)
SBT app illustrating *ScalaTest* failure reports functionality.

### [04 &mdash; Exploring ScalaTest BDD tests](./04-bdd-scalatest-app-sbt)
SBT app illustrating *ScalaTest* BDD approach and the matchers DSL.

### [05 &mdash; BDD with Specs2](./05-bdd-specs2-app-sbt)
SBT app illustrating *Specs2* BDD approach and the *acceptance* and *unit* specs.

### [06 &mdash; BDD with Specs2](./06-scalacheck-app-sbt)
SBT app illustrating *ScalaCheck* property-based testing.

