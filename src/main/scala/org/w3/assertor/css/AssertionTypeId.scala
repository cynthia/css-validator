package org.w3.assertor.css

/** an unique id to identify the type/class/kind of an Assertion */
case class AssertionTypeId(uniqueId: String) extends AnyVal {
  override def toString = uniqueId
}

object AssertionTypeId {

  def apply(assertion: Assertion): AssertionTypeId = {
    apply(assertion.title)
  }

  def fromTitle(title: String): AssertionTypeId = {
    val uniqueId = title.hashCode.toString
    new AssertionTypeId(uniqueId)
  }

}

