organization := "com.example.shapeless"

name := "shapeless-examples"

version := "1.0"

scalaVersion := "2.12.3"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "1.0.1",
  "com.chuusai" %% "shapeless" % "2.3.3",
//  "com.typesafe.akka" %% "akka-actor" % "2.5.11",
//  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.0",
  "io.circe" %% "circe-core" % "0.9.3",
  "io.circe" %% "circe-generic" % "0.9.3",
  "io.circe" %% "circe-parser" % "0.9.3",
  "io.circe" %% "circe-optics" % "0.9.3",
  "io.circe" %% "circe-shapes" % "0.9.3"
)

scalacOptions ++= Seq("-Ypartial-unification")