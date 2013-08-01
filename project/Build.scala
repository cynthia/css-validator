import sbt._
import sbt.Keys._

/**
 * build configuration for css-validator
 */
object CssValidatorBuild extends Build {

  val jettyVersion = "8.1.12.v20130726"
  val javaServletVersion = "3.0.0.v201112011016"

  lazy val cssValidator = Project(
    id = "validator",
    base = file("."),

    settings = Defaults.defaultSettings ++ Seq(
      organization := "org.w3",
      version := "1.0-SNAPSHOT",
      scalaVersion := "2.11.0-M4",
      testOptions in Test += Tests.Argument("exclude(org.w3.assertor.css.SlowTest)"),
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

      libraryDependencies += "org.scalatest" %% "scalatest" % "2.0.M6-SNAP35" % "test",
      libraryDependencies += ("com.ning" % "async-http-client" % "1.7.6"  % "test" notTransitive()).exclude("org.jboss.netty", "netty"),

//      libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.2",
      libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.0.9",

      libraryDependencies += "Xerces-J" % "xercesImpl" % "2.11.0" from jarInZip("http://www.apache.org/dist/xerces/j/binaries/Xerces-J-bin.2.11.0.zip", "xerces-2_11_0/xercesImpl.jar"),
      libraryDependencies += "Xerces-J" % "xml-apis" % "2.11.0" from jarInZip("http://www.apache.org/dist/xerces/j/binaries/Xerces-J-bin.2.11.0.zip", "xerces-2_11_0/xml-apis.jar"),
      libraryDependencies += "validator.nu" % "htmlparser" % "1.3.1" from jarInZip("http://about.validator.nu/htmlparser/htmlparser-1.3.1.zip", "htmlparser-1.3.1/htmlparser-1.3.1.jar"),
      libraryDependencies += "org.w3" % "jigsaw" % "2.2.6" from jarInZip("http://jigsaw.w3.org/Distrib/jigsaw_2.2.6.zip", "Jigsaw/classes/jigsaw.jar"),
      libraryDependencies += "joda-time" % "joda-time" % "2.2"
    )
  )

  def jarInZip(url: String, jarPath: String): String = {
    import java.net.URL
    import java.io.File
    val tmp = new File(System.getProperty("java.io.tmpdir"))
    val retrieved = new File("retrieved")
    val jarName = new File(jarPath).getName
    val jarFile = retrieved / jarName
    if (! jarFile.exists) {
      if (! retrieved.exists) retrieved.mkdir()
      val dirName = new File(jarPath).toPath.iterator.next.toString
      val zipName = new File(new URL(url).getPath).getName
      val zipFile = (tmp / zipName)
      if (! zipFile.exists) {
        println("downloading " + url)
        IO.download(new URL(url), zipFile)
      }
      val files = IO.unzip(
        from = zipFile,
        toDirectory = tmp,
        filter = jarPath)
      files foreach { file =>
        println("moving \"" + file.getName + "\" to \"" + retrieved.getAbsolutePath + "\"")
        IO.move(file, jarFile)
      }
    }
    jarFile.toURI.toString
  }

}
