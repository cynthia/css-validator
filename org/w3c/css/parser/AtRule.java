//
// $Id: AtRule.java,v 1.2 2002-04-08 21:24:11 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * AtRule.java
 * $Id: AtRule.java,v 1.2 2002-04-08 21:24:11 plehegar Exp $
 */
package org.w3c.css.parser;

/**
 * @version $Revision: 1.2 $
 * @author  Philippe Le Hégaret
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
}
