import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "lift"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      // Add your project dependencies here,
      "org.tautua.markdownpapers" % "markdownpapers-core" % "1.3.2",
      "com.google.guava" % "guava" % "13.0.1"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      // Add your own project settings here      
    )

}
