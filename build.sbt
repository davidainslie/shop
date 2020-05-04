import Dependencies._

lazy val root = (project in file(".")).settings(
  inThisBuild(List(
    organization := "com.backwards",
    scalaVersion := "2.13.2",
    version      := "0.3.0-SNAPSHOT"
  )),
  name := "shop",
  scalacOptions ++= Seq(
    "-feature",
    "-language:implicitConversions",
    "-language:higherKinds",
    "-language:existentials",
    "-language:reflectiveCalls",
    "-language:postfixOps",
    "-Yrangepos"
  ),
  libraryDependencies ++= Seq(
    squants
  ),
  libraryDependencies ++= Seq(
    specs2 % Test
  )
)