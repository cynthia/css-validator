package org.w3.assertor.css

import java.net._
import org.w3c.css.util._
import java.io._
import java.util._

class CacheConnection(url: URL, cr: CacheResponse) extends Connection {

  def getBody(): InputStream = cr.getBody()
  def getHeaders(): Map[String, List[String]] = cr.getHeaders()
  def getHeaderField(name: String): String = ???
  def getContentEncoding(): String = ???
  def getContentType(): String = ???
  def getURL(): URL = url
  def isHttpURL(): Boolean = url.getProtocol == "http" || url.getProtocol == "https"


}
