submitProjectName := "recfun"

scalaVersion := "2.11.5"

scalacOptions ++= Seq("-deprecation", "-feature")

(fork in Test) := false

projectDetailsMap := {
  val currentCourseId = "progfun-002"

  val depsNode = Seq(
    "com.netflix.rxjava" % "rxjava-scala" % "0.15.0",
    "org.json4s" %% "json4s-native" % "3.2.11",
    "org.scala-lang.modules" %% "scala-swing" % "1.0.1",
    "net.databinder.dispatch" %% "dispatch-core" % "0.11.0",
    "org.scala-lang" % "scala-reflect" % scalaVersion.value,
    "org.slf4j" % "slf4j-api" % "1.7.5",
    "org.slf4j" % "slf4j-simple" % "1.7.5",
    "com.squareup.retrofit" % "retrofit" % "1.0.0",
    "org.scala-lang.modules" %% "scala-async" % "0.9.2"
  )

  val depsAkka = Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.3.9",
    "com.typesafe.akka" %% "akka-testkit" % "2.3.9",
    "com.typesafe.akka" %% "akka-persistence-experimental" % "2.3.9"
  )

  Map(
    "example" ->  ProjectDetails(
                    packageName = "example",
                    assignmentPartId = "fTzFogNl",
                    maxScore = 10d,
                    styleScoreRatio = 0.2,
                    courseId=currentCourseId),
    "recfun" ->     ProjectDetails(
                    packageName = "recfun",
                    assignmentPartId = "3Rarn9Ki",
                    maxScore = 10d,
                    styleScoreRatio = 0.2,
                    courseId=currentCourseId),
    "funsets" ->    ProjectDetails(
                    packageName = "funsets",
                    assignmentPartId = "fBXOL6Qd",
                    maxScore = 10d,
                    styleScoreRatio = 0.2,
                    courseId=currentCourseId),
    "objsets" ->    ProjectDetails(
                    packageName = "objsets",
                    assignmentPartId = "95dMMEz7",
                    maxScore = 10d,
                    styleScoreRatio = 0.2,
                    courseId=currentCourseId),
    "patmat" ->     ProjectDetails(
                    packageName = "patmat",
                    assignmentPartId = "3gPmpcif",
                    maxScore = 10d,
                    styleScoreRatio = 0.2,
                    courseId=currentCourseId),
    "forcomp" ->    ProjectDetails(
                    packageName = "forcomp",
                    assignmentPartId = "fG1oZGIO",
                    maxScore = 10d,
                    styleScoreRatio = 0.2,
                    courseId=currentCourseId),
    "streams" ->    ProjectDetails(
                    packageName = "streams",
                    assignmentPartId = "CWKgCFCi",
                    maxScore = 10d,
                    styleScoreRatio = 0.2,
                    courseId=currentCourseId),
    "simulations" -> ProjectDetails(
                     packageName = "simulations",
                     assignmentPartId = "iYs4GARk",
                     maxScore = 10d,
                     styleScoreRatio = 0.2,
                     courseId="progfun2-001"),
    "interpreter" -> ProjectDetails(
                     packageName = "interpreter",
                     assignmentPartId = "1SZhe1Ut",
                     maxScore = 10d,
                     styleScoreRatio = 0.2,
                     courseId="progfun2-001")
)}