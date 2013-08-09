package org.w3c.css

import java.io._
import org.w3c.css.util._
import java.net.URL

// useful for testing purpose
class WrappedConnectionHandler(handler: ConnectionHandler) extends ConnectionHandler {
  def getConnection(url: URL, referrer: URL, count: Int, ac: ApplContext): Connection = new Connection {
    val conn = handler.getConnection(url, referrer, count, ac)

    def getBody(): InputStream = {
      println("call getBody()")
      conn.getBody()
    }
    def getContentEncoding(): String = {
      val r = conn.getContentEncoding
      println("call getContentEncoding " + r)
      r
    }
    def getContentLocation(): String = {
      val r = conn.getContentLocation
      println("call getContentLocation " + r)
      r
    }
    def getContentType(): String = {
      val r = conn.getContentType
      println("call getContentType " + r)
      r
    }
    def getURL(): URL = {
      val r = conn.getURL
      println("call getURL " + r)
      r
    }
    def isHttpURL(): Boolean = {
      val r = conn.isHttpURL
      println("call isHttpURL " + r)
      r
    }
  }

}
