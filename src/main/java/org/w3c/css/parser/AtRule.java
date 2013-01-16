//
// $Id: AtRule.java,v 1.7 2012-10-10 07:47:30 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * AtRule.java
 * $Id: AtRule.java,v 1.7 2012-10-10 07:47:30 ylafon Exp $
 */
package org.w3c.css.parser;

/**
 * @author Philippe Le Hegaret
 * @version $Revision: 1.7 $
 */
public abstract class AtRule {

	/**
	 * Returns the at rule keyword
	 */
	public abstract String keyword();

	/**
	 * The second must be exactly the same of this one
	 */
	public abstract boolean canApply(AtRule atRule);

	/**
	 * The second must only match this one
	 */
	public abstract boolean canMatch(AtRule atRule);

	public boolean isEmpty() {
		return false;
	}

	public String lookupPrefix() {
		return keyword();
	}
}
