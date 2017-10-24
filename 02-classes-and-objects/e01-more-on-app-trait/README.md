# e01 &mdash; More on the App trait
> using the `App` *trait*

## Description
Another example using the `App` trait. 

In the program we extend from `App` and create a list of values to calculate which is the concatenation of any arguments received from the command line, plus a set of default values.

```scala
object FallWinterSpringSummer extends App {

  def getValuesToCalculate(): List[String] = {
    val defaultValues = List("fall", "winter", "spring", "summer")
    if (args.isEmpty) 
      defaultValues
    else 
      args.toList ::: defaultValues
  }

  for (value <- getValuesToCalculate)
    println(s"$value: ${ calculate(value) }");
}
```

Note that the function is using `args` without having being explicitly defined, and that we don't need to define any method in the *object*.


## Running the Application
Type `sbt` and then type:

```bash
sbt:FallWinterSpringSummer> run  # runs with the default values
sbt:FallWinterSpringSummer> run one two three # runs with the args ::: default values
```

You can return to the command line just by typing: 
```bash
sbt:Hello> exit
```
