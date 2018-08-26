# 03 &mdash; Hello World SBT deps  
> Specifying dependencies using IntelliJ SBT

The project demonstrates how to add new dependencies to a SBT project, by simply editing the `build.sbt` file and adding the line:

```scala
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.0"
```