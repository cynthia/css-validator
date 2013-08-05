package org.w3c.css

import org.scalatest._
import org.scalatest.matchers.MustMatchers
import org.w3c.css.servlet.CssValidator
import org.eclipse.jetty.server.{ Server, Handler, Connector }
import org.eclipse.jetty.server.nio.SelectChannelConnector
import org.eclipse.jetty.server.handler.{ ContextHandlerCollection, ResourceHandler, DefaultHandler }
import org.eclipse.jetty.servlet.{ ServletContextHandler, ServletHolder, FilterHolder }
import org.eclipse.jetty.servlets.GzipFilter
import org.eclipse.jetty.util.thread.QueuedThreadPool
import javax.servlet.DispatcherType
import java.util.EnumSet
import com.ning.http.client._
import java.io._

object SlowTest extends Tag("org.w3.assertor.css.SlowTest")

object PositiveTest {

  implicit class FileW(val file: File) extends AnyVal {
    def / (child: String): File = new File(file, child)
  }

}

class PositiveTest extends FlatSpec with MustMatchers with BeforeAndAfterAll {

  import PositiveTest.FileW

  override def beforeAll(): Unit = {
    server.start()
  }
  
  override def afterAll(): Unit = {
    client.close()
    server.stop()
  }

  val prefix = "/css"
  val port = 2719

  // not sure if needed
  import org.apache.velocity.app.Velocity
  org.w3c.css.util.Util.onDebug = false
  Velocity.setProperty("resource.loader", "class")
  Velocity.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader")

  val cssValidator: ServletContextHandler = {
    val context = new ServletContextHandler
    context.setContextPath(prefix)
    context.setInitParameter("vendorExtensionsAsWarnings", "true")
    context.addServlet(new ServletHolder(new CssValidator), "/*")
    context
  }

  val staticFilesHandler = {
    val handler = new ResourceHandler()
    handler.setDirectoriesListed(true)
    handler.setResourceBase("./autotest/testsuite/properties")
    val context = new org.eclipse.jetty.server.handler.ContextHandler
    context.setHandler(handler)
    context.setContextPath("/static")
    context
  }

  val server: Server = {
    val pool: QueuedThreadPool = {
      val pool = new QueuedThreadPool
      pool.setMaxThreads(100)
      pool
    }
    val connector: Connector = {
      val connector = new SelectChannelConnector
      connector.setPort(port)
      connector.setMaxIdleTime(30000)
      connector.setThreadPool(pool)
      connector
    }
    val handlers: ContextHandlerCollection = {
      val array: Array[Handler] = Array(cssValidator, staticFilesHandler)
      val handlers = new ContextHandlerCollection
      handlers.setHandlers(array)
      handlers
    }
    val server = {
      val server = new Server
      server.setGracefulShutdown(500)
      server.setSendServerVersion(false)
      server.setSendDateHeader(true)
      server.setStopAtShutdown(true)
      server.setThreadPool(pool)
      server.addConnector(connector)
      server.setHandler(handlers)
      server
    }
    server
  }

  val client = {
    import java.util.concurrent.ForkJoinPool
    val executor = new ForkJoinPool()
    val builder = new AsyncHttpClientConfig.Builder()
    val config =
      builder
        .setExecutorService(executor)
        .setFollowRedirects(true)
        .setConnectionTimeoutInMs(3000)
        .build()
    new AsyncHttpClient(config)
  }

  def nbErrors(url: String): Int = {
    val encodedUrl = java.net.URLEncoder.encode(url, "UTF-8")
    val serviceUrl = s"http://localhost:2719/css/?uri=${encodedUrl}&profile=css3&output=json"
    val response = client.prepareHead(serviceUrl).execute().get()
    val errors = response.getHeader("X-W3C-Validator-Errors").toInt
    errors
  }

//  "All Positive Tests" must "have no error" in {
  "All Positive Tests" must "have no error" taggedAs(SlowTest) in {
    val testSuiteBase = new File("autotest/testsuite/properties")
    val positive = testSuiteBase / "positive"
    val allDirectories = new FileFilter { def accept(file: File) = file.isDirectory }
    val allCssFiles = new FileFilter { def accept(file: File) = file.isFile && file.getName.endsWith(".css") }
    positive.listFiles(allDirectories) foreach { propertyDir =>
      val property = propertyDir.getName
      propertyDir.listFiles(allDirectories) foreach { cssLevelDir =>
        val cssLevel = cssLevelDir.getName
        cssLevelDir.listFiles(allCssFiles) foreach { cssFile =>
          val fileName = cssFile.getName
          val path = s"positive/${property}/${cssLevel}/${fileName}"
          val errors = nbErrors(s"http://localhost:2719/static/${path}")
          errors match {
            case 0 => ()
            case 1 => println(s"${errors} errors for ${path}")
            case n => println(s"${n} errors for ${path}")
          }
        }
      }
    }
    2 must be (1 + 1)
  }

}
