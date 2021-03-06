//logLevel := Level.Debug

import com.typesafe.config._

val conf = ConfigFactory.parseFile(new File("conf/application.conf")).resolve()

name := """UnderClash"""

version := conf.getString("build.version")

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

scalacOptions += "-feature"

// Disable documentation generation
doc in Compile <<= target.map(_ / "none")

libraryDependencies ++= Seq(
  cache,
  ws,
  "com.typesafe.play" %% "play-slick" % "2.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "2.0.0",
  "org.postgresql" % "postgresql" % "9.4-1206-jdbc42",
  "org.webjars" %% "webjars-play" % "2.5.0",
  "org.webjars.bower" % "angular-material" % "1.0.9",
  "org.webjars" % "chartjs" % "2.1.3"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

pipelineStages := Seq(rjs, digest, gzip)
//pipelineStages := Seq(uglify, rjs, digest, gzip)
//pipelineStages := Seq(imagemin, rjs, digest, gzip)

