import sbt._

object Dependencies {
  lazy val specs2: ModuleID = "org.specs2" %% "specs2-core" % "4.9.4"
  lazy val squants: ModuleID = "org.typelevel" %% "squants" % "1.6.0"
}