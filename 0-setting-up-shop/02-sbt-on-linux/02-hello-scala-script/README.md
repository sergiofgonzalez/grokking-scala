# 02 &mdash; Hello Scala Scripts 
> Running Scala code stored in files from the Scala console

This project illustrates how to run some Scala code placed in a file. In the directory there is a `hello.scala` file that can be executed by starting *sbt* and then from the console using `:load <scala-file>`.

```
$ sbt
[info] Updated file /home/ubuntu/Development/git-repos/side-projects/grokking-scala/0-setting-up-shop/02-sbt-on-linux/02-hello-scala-script/project/build.properties: set sbt.version to 1.2.1
[info] Loading project definition from /home/ubuntu/Development/git-repos/side-projects/grokking-scala/0-setting-up-shop/02-sbt-on-linux/02-hello-scala-script/project
[info] Updating ProjectRef(uri("file:/home/ubuntu/Development/git-repos/side-projects/grokking-scala/0-setting-up-shop/02-sbt-on-linux/02-hello-scala-script/project/"), "root-02-hello-scala-script-build")...
[info] Done updating.
[info] Set current project to root-02-hello-scala-script (in build file:/home/ubuntu/Development/git-repos/side-projects/grokking-scala/0-setting-up-shop/02-sbt-on-linux/02-hello-scala-script/)
[info] sbt server started at local:///home/ubuntu/.sbt/1.0/server/a8e0daccb1f2708c1b1b/sock

sbt:root-02-hello-scala-script> consoleQuick
[info] Updating ...
[info] Done updating.
[info] Starting scala interpreter...
Welcome to Scala 2.12.6 (OpenJDK 64-Bit Server VM, Java 1.8.0_181).
Type in expressions for evaluation. Or try :help.

scala> :load hello.scala
Loading hello.scala...
Hello to Jason Isaacs
```

Note that you cannot do `sbt run hello.scala` as *run* expects a class to execute, not a script.