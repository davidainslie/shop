import sbt._

object Dependencies {
  lazy val specs2: ModuleID = "org.specs2" %% "specs2-core" % "3.9.5"
  lazy val squants: ModuleID = "org.typelevel" %% "squants" % "1.3.0"
}