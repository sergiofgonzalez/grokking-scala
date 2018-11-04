# 03 &mdash; Hello Package Objects! Application SBT   
> Scala application project introducing the package object concept

A really simple Scala application using SBT that features a package object that includes a helper function.

Then, in the main application, the necessary elements are imported.

## Running the Project
Import the project and either:
+ right-click on the `FruitsApp` class and select `Run FruitsApp`. Note that sometimes that option does not trigger the proper recompilation of the project so you might get an error telling you that the class was not found. In that case, manually delete the `target/` directory and force a project rebuild.
+ Configure a new *sbt task* with a name of your choice and the action `~run` which will cause configure like a *watch* task that will look for changes in your project and run the application when those are detected.