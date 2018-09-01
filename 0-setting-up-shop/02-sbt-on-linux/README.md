# Scala: Setting up shop: SBT on Linux   
> Installing *SBT* on Linux, and getting to know the basic commands.

## Installing SBT (Scala Build Tool)
Installation details can be found in Scala SBT website https://www.scala-sbt.org/1.x/docs/Setup.html.

## SBT First Steps
Once installed, you can start SBT by typing `sbt` in the terminal:

```bash
$ mkdir 01-hello-scala
$ cd 01-hello-scala
$ sbt
[warn] No sbt.version set in project/build.properties, base directory: /home/ubuntu/Development/git-repos/side-projects/grokking-scala/0-setting-up-shop/02-sbt-on-linux/01-hello-scala
[info] Set current project to root-01-hello-scala (in build file:/home/ubuntu/Development/git-repos/side-projects/grokking-scala/0-setting-up-shop/02-sbt-on-linux/01-hello-scala/)
[info] sbt server started at local:///home/ubuntu/.sbt/1.0/server/f63a8fbe3181e6c0f260/sock
sbt:root-01-hello-scala>
```

| Note |
|------|
|The previous command will create a `project/` directory |


From the *sbt* prompt you can start the *Scala REPL* by typing `console`:

```bash
sbt:root-01-hello-scala> console
[info] Updating ...
[info] Done updating.
[info] Starting scala interpreter...
Welcome to Scala 2.12.6 (OpenJDK 64-Bit Server VM, Java 1.8.0_181).
Type in expressions for evaluation. Or try :help.
scala> 42
res1: Int = 42

scala> println("Hello to Jason Isaacs")
Hello to Jason Isaacs

scala> 1+2
res3: Int = 3
```

| Problems starting the console? |
|--------------------------------|
| The `console` task tries to compile the current project, and if an error is found the Scala REPL will not be started. In those case, you can use the task `consoleQuick` |


## Listing SBT available tasks
You can list the available sbt tasks by typing `tasks` at the *sbt* prompt.

## Checking SBT version
You can check the sbt version by typing `sbt sbtVersion` in the terminal.

## Scala REPL first steps
Scala provides a REPL (Read-Evaluate-Print-Loop) interpreter that can be used to write and run Scala programs in an interactive way. This tool includes some useful implicit imports to facilitate writing snippets. For example, you can use `println` without qualifyin it because:

```scala
scala> println("Hello to Jason Isaacs")
Hello to Jason Isaacs
```

because the REPL automatically inserts the imports that map `println` to `Console.println`.

The Scala interpreter provides the following commands:

| Command | Description |
|---------|-------------|
| :help   | Prints the help message and all the available commands |
| :cp     | Adds a JAR file to the classpath of the interpreter, so that you can refer to the classes inside the jar
E.g. `:cp tools/junit.jar` |
| :load   | Loads Scala file into the interpreter, so that all the definitions become available for the current snippet |
| :replay (:r) | Resets the interpreter and replays all the previous commands |
| :quit (:q) | Exits from the interpreter |
| :type | Displays the type of an expression. The expression will not change the state of the interpreter
E.g. `:type 1 + 2 // -> Int` |
| :imports | Displays the packages that are imported into the REPL |


After you type an expression and type *enter*, the Scala interpreter will print the result prefixed by an automatically generated or user-defined name that identifies the computed value and that can be used subsequently.

```scala
scala> 1+2
res3: Int = 3

scala> res3 *2
res4: Int = 6
```

| Packages in Scala |
|-------------------|
| The type `Int` names the class `Int` in the package `scala`. Packages in *Scala* are similar to package in *Java*: they  provide a mechanism for information hiding and structuring |



## Projects

### [01 &mdash; Hello Scala](./01-hello-scala)
An empty project used to start the Scala interpreter from *SBT*.
