# 003 &mdash; Hello, App trait
> creating a Scala runnable application using the `App` *trait*

## Description
The `App` *trait* can be used to save some boilerplate code for Scala applications.

Instead of having to define an *object* with a `main(args: Array[String])` method you can extend from `App` instead:

```scala
object MyApp extends App {
  for (arg <- args)
    println(s"arg: $arg")
}
```

Note that you don't even need to define a method inside the object.


## Running the Application
Type `sbt` and then type:

```bash
sbt:FallWinterSpringSummer> run
```

You can return to the command line just by typing: 
```bash
sbt:Hello> exit
```
