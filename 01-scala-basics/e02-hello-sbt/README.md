# e02 &mdash; Hello, sbt
> running Scala programs with sbt

## Description
*sbt* is a build tool for Scala. This project will explain the steps you should follow to build Scala programs and run them.

### Installation
If you're reading this, you have probably installed *sbt* already, but if that is not the case, it is recommended that you follow what's written in the [Getting started with sbt guide](http://www.scala-sbt.org/1.x/docs/Getting-Started.html), which begins with the simple concepts and installation.


Once installed, you can check it works by checking the version using:
```bash
$ sbt sbtVersion
[info] 1.0.2
```

### Setup the structure for a simple Hello, world application
You can use `sbt new` command to quickly setup a simple *sbt* project:

```bash
$ cd 01-scala-basics/
$ sbt new sbt/scala-seed.g8
```
Then choose `e02-hello-sbt` as the project name, so that all the source code and resources are created in that directory.

### Updating the application
Once the application has been created, modify the source code for the `Hello.scala` file found under `src/main/scala/example`.

```scala
package example

object Hello extends App {
  require(args.size >= 2, s"at minimun this application expects two arguments, but received ${ args.size }")
  args.foreach(arg => println(s"arg=$arg"))
  println(s"args.head=${ args.head }")
  println(s"args.last=${ args.last }")
}
```

In the program, we create an object `Hello` which extends from `App` and checks that at least 2 arguments are received from the command line. Then it prints them and uses `args.head` and `args.last` to print the first and last elements of the args collection.

### Running the application
Type `sbt` and then use `run` to validate how your app works:

```bash
sbt:Hello> run one
sbt:Hello> run one two three
sbt:Hello> run one two
```
Note that *run* output looks really verbose.

You can return to the command line just by typing: 
```bash
sbt:Hello> exit
```

### Understanding the project's file structure
The structure of an *sbt* project looks pretty much like a Maven Java project:
```
build.sbt <build definiton file>
project/
  build.properties
  Dependencies.scala
src/
  main/
    resources/
      <files to include in the jar>
    scala/
      <main Scala sources>
    java/
      <main Java sources>
  test/
    resources/
      <files to include in test jar here>
    scala/
      <test Scala sources>
    java/
      <test Java sources>
target/
  <build products>
```


The build definition is stored in a `build.sbt` file located in the project's root dir. Other *.sbt* files located under `project/` directory


### sbt basic commands

| command    | description                                                                                   |
|------------|-----------------------------------------------------------------------------------------------|
| sbt        | starts the build tool for Scala shell                                                         |
| compile    | compiles the project in the working dir                                                       |
| run        | runs the project in the working dir                                                           |
| exit       | leaves the sbt shell                                                                          |
| clean      | deletes generated files (in the target dir)                                                   |
| ~testQuick | continuous development mode: runs tests on changes                                            |
| test       | compiles and runs all tests                                                                   |
| console    | starts the Scala shell with a a classpath that includes the compiled sources and dependencies |
| run <arg>* | runs the main class for the project in the same JVM as sbt                                    |
| package    | creates a jar with the compiled sources and resource files in the working dir                 |
| reload     | reloads the build definitions                                                                 |
| sbtVersion | reports the sbt version                                                                       |

### Understanding the basics of the build.sbt

This section explains the bare minimum of the build.sbt syntax to survive with the simplest examples. The complete guide is much more comprehensive and can be found in the [Getting Started Guide](http://www.scala-sbt.org/1.x/docs/Getting-Started.html)
First, create a file `project/build.properties` and include the sbt version your project uses (for consistent results).
```
sbt.version=1.0.2
```

Then, the build definition is defined in `build.sbt` file, and it consists of a set of projects. The contents of `build.sbt` from the `sbt new` command we used generated the following contents:
```scala
import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.3",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Hello",
    libraryDependencies += scalaTest % Test
  )
```

According to the docs, this can be further simplified as:
```scala
lazy val root = (project in file("."))
  .settings(
    organization := "com.github.sergiofgonzalez",
    name := "Hello",    
    scalaVersion := "2.12.3",
    version := "0.1.0-SNAPSHOT"
  )
```

Each *subproject* is configured by key-value pairs (`name := "Hello"`)

To add library dependencies you use the `libraryDependencies` keyword.
