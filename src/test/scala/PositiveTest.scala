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

class PositiveTest extends WordSpec with MustMatchers with BeforeAndAfterAll {

  override def beforeAll(): Unit = {
    server.start()
  }
  
  override def afterAll(): Unit = {
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
      val array: Array[Handler] = Array(cssValidator, staticFilesHandler, new DefaultHandler())
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

  "test suite" in {

    nbErrors("http://localhost:2719/static/positive/align-content/css3/001.css") must be(0)

  }

}
