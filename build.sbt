name := "abcelo-util-aws-lambda"

organization := "com.abcelo"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.8"

resolvers += "Typesafe Releases" at "https://repo.typesafe.com/typesafe/ivy-releases/"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

initialize := {
  val _ = initialize.value
  if (sys.props("java.specification.version") != "1.8")
    sys.error("Java 8 is required for this project.")
}

val awsLambdaCoreVersion = "1.1.0"
val awsSdkVersion = "1.11.78"
val logbackVersion = "1.2.2"
val scalaTestVersion = "3.0.1"
val slf4jVersion = "1.7.25"

libraryDependencies ++= Seq(
  "com.amazonaws" % "aws-lambda-java-core" % awsLambdaCoreVersion,
  "ch.qos.logback" % "logback-classic" % logbackVersion,
  "org.slf4j" % "slf4j-api" % slf4jVersion % "test",
  "org.slf4j" % "jcl-over-slf4j" % slf4jVersion % "test",
  "org.slf4j" % "log4j-over-slf4j" % slf4jVersion % "test",
  "org.slf4j" % "jul-to-slf4j" % slf4jVersion % "test",
  "org.scalatest" %% "scalatest" % scalaTestVersion % "test"
)
.map(_.exclude("commons-logging", "commons-logging"))
.map(_.exclude("log4j", "log4j"))
.map(_.exclude("org.slf4j", "slf4j-log4j12"))

dependencyUpdatesExclusions := moduleFilter(organization = "org.scala-lang")

scalariformSettings

EclipseKeys.withSource := true

assemblyMergeStrategy in assembly := {
  case "META-INF/io.netty.versions.properties" => MergeStrategy.discard
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

