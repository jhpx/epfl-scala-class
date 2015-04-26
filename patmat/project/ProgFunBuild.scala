import sbt._
import Keys._
import ch.epfl.lamp.CourseraBuild
import ch.epfl.lamp.SbtCourseraPlugin.autoImport._

object ProgfunBuild extends CourseraBuild {
  override def assignmentSettings: Seq[Setting[_]] = Seq(
    // This setting allows to restrict the source files that are compiled and tested
    // to one specific project. It should be either the empty string, in which case all
    // projects are included, or one of the project names from the projectDetailsMap.
    currentProject := "",

    // Packages in src/main/scala that are used in every project. Included in every
    // handout, submission.
    commonSourcePackages += "common",

    // Packages in src/test/scala that are used for grading projects. Always included
    // compiling tests, grading a project.
    
    libraryDependencies += "ch.epfl.lamp" %% "scala-grading-runtime" % "0.1",

    // add scala-xml dependency when needed (for Scala 2.11 and newer) in a robust way
    // this mechanism supports cross-version publishing
    // taken from: http://github.com/scala/scala-module-dependency-sample
    libraryDependencies := {
      CrossVersion.partialVersion(scalaVersion.value) match {
        // if scala 2.11+ is used, add dependency on scala-xml module
        case Some((2, scalaMajor)) if scalaMajor >= 11 =>
          libraryDependencies.value ++ Seq(
            "org.scala-lang.modules" %% "scala-xml" % "1.0.3",
            "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.3",
            "org.scala-lang.modules" %% "scala-swing" % "1.0.1")
        case _ =>
          // or just libraryDependencies.value if you don't depend on scala-swing
          libraryDependencies.value :+ "org.scala-lang" % "scala-swing" % scalaVersion.value
      }
    },

    // Files that we hand out to the students
    handoutFiles <<= (baseDirectory, projectDetailsMap, commonSourcePackages) map {
      (basedir, detailsMap, commonSrcs) =>
      (projectName: String) => {
        val details = detailsMap.getOrElse(projectName, sys.error("Unknown project name: "+ projectName))
        val commonFiles = (PathFinder.empty /: commonSrcs)((files, pkg) =>
          files +++ (basedir / "src" / "main" / "scala" / pkg ** "*.scala")
        )
        (basedir / "src" / "main" / "scala" / details.packageName ** "*.scala") +++
        commonFiles +++
        (basedir / "src" / "main" / "resources" / details.packageName / "*") +++
        (basedir / "src" / "test" / "scala" / details.packageName ** "*.scala") +++
        (basedir / "build.sbt") +++
        (basedir / "project" / "build.properties") +++
        (basedir / "project" ** ("*.scala" || "*.sbt")) +++
        (basedir / "project" / "scalastyle_config_progfun.xml") +++
        (basedir / "lib_managed" ** "*.jar") +++
        (basedir * (".classpath" || ".project")) +++
        (basedir / ".settings" / "org.scala-ide.sdt.core.prefs")
      }
    })
}