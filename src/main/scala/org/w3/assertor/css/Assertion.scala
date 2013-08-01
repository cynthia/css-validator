package org.w3.assertor.css

import org.joda.time._
import java.net.URL

case class Assertion(
    id: AssertionTypeId,
    url: URL,
    contexts: Vector[Context],
    lang: String,
    title: String,
    severity: AssertionSeverity,
    description: Option[String],
    timestamp: DateTime = DateTime.now(DateTimeZone.UTC)) {

  def occurrences = scala.math.max(1, contexts.size)

}

object Assertion {

  def countErrorsAndWarnings(assertions: Iterable[Assertion]): (Int, Int) = {
    var errors = 0
    var warnings = 0
    assertions foreach { assertion =>
      assertion.severity match {
        case Error   => errors += assertion.occurrences
        case Warning => warnings += assertion.occurrences
        case Info    => ()
      }
    }
    (errors, warnings)
  }

}
