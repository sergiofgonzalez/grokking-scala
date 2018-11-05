# 03 &mdash; Testing Layout Elements with ScalaTest Application SBT   
> Using ScalaTest `FunSuite` to test the Scala application project featuring the *layout elements* class hierarchy

The example features a test in `src/test/scala` that includes a test for a particular functionality. The `build.sbt` project includes an additional dependency:

```scala
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
```

The test can be run in several different ways:
+ IntelliJ &mdash; right-click on the test class and select *Run ElementSuite*
+ SBT &mdash; from the *sbt* prompt run:
```
sbt:02-testing-layout-elements-scalatest-app-sbt> test
[info] Updating ...
[info] downloading https://repo1.maven.org/maven2/org/scalactic/scalactic_2.12/3.0.5/scalactic_2.12-3.0.5.jar ...
...
[info] Done compiling.
[info] ElementSuite:
[info] - elem result should have passed width
[info] Run completed in 725 milliseconds.
[info] Total number of tests run: 1
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 1, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
[success] Total time: 20 s, completed Nov 5, 2018 10:10:20 AM
sbt:02-testing-layout-elements-scalatest-app-sbt>
```
