name := "05-bdd-specs2-app-sbt"

version := "0.1"

scalaVersion := "2.12.7"

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "4.3.4" % "test")
scalacOptions in Test ++= Seq("-Yrangepos")