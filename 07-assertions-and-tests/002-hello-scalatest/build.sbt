val scalatest = "org.scalatest" %% "scalatest" % "3.0.4" % "test"

lazy val commonSettings = Seq(
  organization := "com.github.sergiofgonzalez",
  version := "0.0.0-SNAPSHOT",
  scalaVersion := "2.12.4"
)

lazy val root = (project in file("."))
  .settings(
    commonSettings,
    name := "HelloScalaTestApp",
    libraryDependencies += scalatest
  )
