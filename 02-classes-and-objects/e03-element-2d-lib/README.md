# e03 &mdash; Element 2D lib
> the Element library as a Scala app.

## Description
The first implementation for a library for building and rendering 2D layout elements, in which each element represent a rectangle filled with text.

The library will provide factory methods:
+ `elem` &mdash; constructs a new element withe the given arguments
+ `above` &mdash; places the given first argument on top of the second argument
+ `beside` &mdash; places the given first argument on the left of the second argument

As an example, the library must be able to support:
```scala
val col1 = elem("hello") above elem("###")
val col2 = elem("***") above elem("world")
col1 beside col2
```

and rendering the previous construct would produce:
```
hello ***
### world
```

The elements will be modeled with a type named `Element`.

## Running the Application
Type `sbt` and then type:

```bash
sbt:Spiral> run  # perform shakedown tests where the different classes are demonstrated
sbt:Spiral> run 6  # create a spiral with 6 sides and print it on the console
sbt:Spiral> run 11 # create a spiral with 11 sides and print it on the console
sbt:Spiral> run 17 # create a spiral with 17 sides and print it on the console
```

You can return to the command line just by typing: 
```bash
sbt:Hello> exit
```
