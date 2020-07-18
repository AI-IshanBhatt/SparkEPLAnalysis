name := "SparkEPLAnalysis"

version := "0.1"

scalaVersion := "2.13.3"

lazy val dependencies = Seq(
  // https://mvnrepository.com/artifact/org.apache.spark/spark-sql
  "org.apache.spark" %% "spark-sql" % "3.0.0"
)

lazy val FootBall = project
  .in(file("./FootBall"))
  .settings(
    name:="FootBall",
    libraryDependencies ++= dependencies
  )

lazy val BaseBall = project
  .in(file("./BaseBall"))
  .settings(
    name:="BaseBall",
    libraryDependencies ++= dependencies
  )

lazy val global = project
  .in(file("."))
  .aggregate(FootBall, BaseBall)

//mainClass in assembly := some("SIC.Ch3.Ch3.EntireFolder")
//assemblyJarName := "Chapter3.jar"

val meta = """META.INF(.)*""".r
assemblyMergeStrategy in assembly := {
  case PathList("javax", "servlet", xs @ _*)         => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".html" => MergeStrategy.first
  case "application.conf"                            => MergeStrategy.concat
  case "unwanted.txt"                                => MergeStrategy.discard
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

