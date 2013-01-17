import sbt._
import sbt.Keys._

/**
 * build configuration for css-validator
 */
object CssValidatorBuild extends Build {

  lazy val getMissingJars = TaskKey[Unit]("get-missing-jars", "Get missing jars")

  val jettyVersion = "8.1.7.v20120910"
  val javaServletVersion = "3.0.0.v201112011016"

  lazy val cssValidator = Project(
    id = "validator",
    base = file("."),

    settings = Defaults.defaultSettings ++ Seq(
      organization := "org.w3",
      version := "1.0-SNAPSHOT",
      scalaVersion := "2.10.0",
      libraryDependencies += "commons-collections" % "commons-collections" % "3.2.1" intransitive(),
      libraryDependencies += "commons-lang" % "commons-lang" % "2.6" intransitive(),
      libraryDependencies += "org.apache.velocity" % "velocity" % "1.7" intransitive(),
      libraryDependencies += "tagsoup" % "tagsoup" % "1.2" from "http://home.ccil.org/~cowan/XML/tagsoup/tagsoup-1.2.jar",
      libraryDependencies += "org.eclipse.jetty" % "jetty-webapp" % jettyVersion % "compile" intransitive(),
      libraryDependencies += "org.eclipse.jetty" % "jetty-util" % jettyVersion % "compile" intransitive(),
      libraryDependencies += "org.eclipse.jetty" % "jetty-server" % jettyVersion % "compile" intransitive(),
      libraryDependencies += "org.eclipse.jetty" % "jetty-io" % jettyVersion % "compile" intransitive(),
      libraryDependencies += "org.eclipse.jetty" % "jetty-http" % jettyVersion % "compile" intransitive(),
      libraryDependencies += "org.eclipse.jetty" % "jetty-security" % jettyVersion % "compile" intransitive(),
      libraryDependencies += "org.eclipse.jetty" % "jetty-continuation" % jettyVersion % "compile" intransitive(),
      libraryDependencies += "org.eclipse.jetty" % "jetty-xml" % jettyVersion % "compile" intransitive(),
      libraryDependencies += "org.eclipse.jetty" % "jetty-servlet" % jettyVersion % "compile" intransitive(),
      libraryDependencies += "org.eclipse.jetty" % "jetty-servlets" % jettyVersion % "compile" intransitive(),
      libraryDependencies += "org.eclipse.jetty.orbit" % "javax.servlet" % javaServletVersion % "compile" artifacts (Artifact("javax.servlet", "jar", "jar")) intransitive(),
      ivyXML :=
        <dependency org="org.eclipse.jetty.orbit" name="javax.servlet" rev={ javaServletVersion }>
          <artifact name="javax.servlet" type="orbit" ext="jar"/>
        </dependency>,

      libraryDependencies += "org.scalatest" % "scalatest_2.10.0-RC5" % "2.0.M5-B1" % "test",
      libraryDependencies += ("com.ning" % "async-http-client" % "1.7.6"  % "test" notTransitive()).exclude("org.jboss.netty", "netty"),

//      libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.2",
      libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.0.9",

      getMissingJars := {
        import java.net.URL
        import java.io.File
        val tmp = new File(System.getProperty("java.io.tmpdir"))
        val lib = new File("lib")
        if (! (lib / "xercesImpl.jar").exists) {
          val url = new URL("http://www.apache.org/dist/xerces/j/binaries/Xerces-J-bin.2.11.0.zip")
          lib.mkdir()
          val files = IO.unzipURL(from = url, toDirectory = tmp, filter = "xerces-2_11_0/xercesImpl.jar" | "xerces-2_11_0/xml-apis.jar")
          files foreach { file =>
            println("moving \"" + file.getAbsolutePath + "\" to \"" + lib.getAbsolutePath + "\"")
            IO.move(file, lib / file.getName)
          }
        }
        if (! (lib / "htmlparser-1.3.1.jar").exists) {
          val url = new URL("http://about.validator.nu/htmlparser/htmlparser-1.3.1.zip")
          lib.mkdir()
          val files = IO.unzipURL(from = url, toDirectory = tmp, filter = "htmlparser-1.3.1/htmlparser-1.3.1.jar")
          files foreach { file =>
            println("moving \"" + file.getAbsolutePath + "\" to \"" + lib.getAbsolutePath + "\"")
            IO.move(file, lib / file.getName)
          }
        }
        if (! (lib / "jigsaw.jar").exists) {
          val url = new URL("http://jigsaw.w3.org/Distrib/jigsaw_2.2.6.zip")
          lib.mkdir()
          val files = IO.unzipURL(from = url, toDirectory = tmp, filter = "Jigsaw/classes/jigsaw.jar")
          files foreach { file =>
            println("moving \"" + file.getAbsolutePath + "\" to \"" + lib.getAbsolutePath + "\"")
            IO.move(file, lib / file.getName)
          }
        }
      },

      (compile in Compile) <<= (compile in Compile).dependsOn(getMissingJars)

    )
  )

}
