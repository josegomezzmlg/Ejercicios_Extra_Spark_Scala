import sbt.Keys.libraryDependencies

ThisBuild / version := "0.1."

ThisBuild / scalaVersion := "2.12.15"

lazy val root = (project in file("."))
  .settings(
    name := "Extra_Spark",
      libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core" % "3.3.0",
      "org.apache.spark" %% "spark-sql" % "3.3.0",
      "log4j" % "log4j" % "1.2.17"
    )
  )
