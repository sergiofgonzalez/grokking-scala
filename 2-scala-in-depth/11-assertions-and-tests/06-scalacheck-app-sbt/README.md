# 03 &mdash; Property-Based Testing with ScalaCheck and ScalaTest Application SBT   
> Using ScalaCheck to do property-based testing

The example features a test in `src/test/scala` that includes a ScalaTest with property-based testing using ScalaCheck.

```scala
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.0" % "test"
```

Note that the test fails with OOM error!
