//
// $Id: AtRule.java,v 1.4 2007-11-26 05:07:17 ot Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * AtRule.java
 * $Id: AtRule.java,v 1.4 2007-11-26 05:07:17 ot Exp $
 */
package org.w3c.css.parser;

/**
 * @version $Revision: 1.4 $
 * @author  Philippe Le Hegaret
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
    public abstract boolean canMatched(AtRule atRule);

    public boolean isEmpty() {
		return false;
	}
}
