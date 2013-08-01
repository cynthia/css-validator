package org.w3.assertor.css

case class Context(
    content: String,
    line: Option[Int], 
    column: Option[Int])
