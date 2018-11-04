# 02 &mdash; Hello World SBT   
> Hello World in Scala on Windows using SBT model

Hello World in Scala using IntelliJ on Windows, but using the Scala Build Tool (sbt) instead of the IntelliJ model for Scala. In order to run it, import the project and then configure a new *sbt task* from the *Edit Configurations...* menu with the task `~run` that causes SBT to rebuild and rerun the project when you save changes to a file in the project.

Note that you can also right-click on the runnable class and select *Run <class-name>*. However, there are some times on which the IDE do not properly rebuild the application. In those cases, manually delete the `target/` directory and try again.