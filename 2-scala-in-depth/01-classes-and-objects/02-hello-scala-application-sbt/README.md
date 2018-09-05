# 02 &mdash; Hello Scala Application SBT   
> Scala application project using SBT model

A simple Scala application using SBT that can also be run on IntelliJ IDE. The application defines a class `ChecksumCalculator` and an entry point in `Summer.scala` to illustrate how to define an entry point for a Scala application.

Running and debugging in IntelliJ is pretty straightforward: you just open the project and edit the source code files and whenever you want to run you just right-click on the `Summer.scala` file and select Run.

On SBT is also pretty straightforward, you just have to start sbt on the command line and then select the `run` task, maybe followed by some parameters the application might expect:

```bash
sbt:02-hello-scala-application-sbt> run
[info] Running Summer
Usage: Summer arg1 arg2 ... argn
[success] Total time: 1 s, completed Sep 5, 2018 6:57:12 PM

sbt:02-hello-scala-application-sbt> run Hello to Jason Isaacs
[info] Running Summer Hello to Jason Isaacs
Hello: -244
to: -227
Jason: -251
Isaacs: -84
[success] Total time: 0 s, completed Sep 5, 2018 6:57:41 PM
```