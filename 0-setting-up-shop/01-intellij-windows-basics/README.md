# Scala: Setting up shop: IntelliJ Basics on Windows   
> Installing the *IntelliJ Scala IDE* on Windows and writing and running simple examples using IntelliJ engine and *sbt*.

Setting up IntelliJ on Windows for Scala development. The details about the installation and set up can be found in the official Scala documentation from https://docs.scala-lang.org/getting-started-intellij-track/getting-started-with-scala-in-intellij.html

## [01 &mdash; Hello World IntelliJ](./01-Hello-World-IntelliJ)
Basic Scala examples using IntelliJ IDE on Windows.

## [02 &mdash; Hello World SBT](./02-Hello-World-sbt)
Hello World in Scala using IntelliJ on Windows, but using the Scala Build Tool (sbt) instead of the IntelliJ model for Scala. In order to run it, import the project and then configure a new *sbt task* from the *Edit Configurations...* menu with the task `run~ .` that causes SBT to rebuild and rerun the project when you save changes to a file in the project.

## [03 &mdash; Hello World IntelliJ using sbt with dependencies](./03-Hello-World-sbt-deps)
Same example as above, but adding a dependency on the `build.sbt`.

## [04 &mdash; Hello ScalaTest IntelliJ sbt](./04-Hello-ScalaTest-sbt)
An example demonstrating how to write and build a very simple test of a Scala program using ScalaTest framework and the sbt.
