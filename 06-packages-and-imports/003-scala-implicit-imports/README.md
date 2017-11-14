# 003 &mdash; Scala's Implicit Imports
> the three implicit imports added to every Scala program

## Description
Scala adds some imports implicitly to every program:
+ `java.lang._` &mdash; imports all the members of the `java.lang` package which contains standard Java classes (like `Thread` that becomes `java.lang.Thread`).
+ `scala._` &mdash; imports everything from `scala` package which contains the standard Scala library (so you can refer to `List` instead of `scala.List`).
+ `Predef._` &mdash; imports everything in the `Predef` object which contains many definitions of types, methods and implicit conversions commonly used on Scala programs (so you can write `assert` instead of `Predef.assert`).

It's important to note that imports seen later in the program overshadows earlier ones, so Scala's StringBuilder will  be used when you write `StringBuilder` instead of `java.lang.StringBuilder`.

## Running the Application
There is no application to run.