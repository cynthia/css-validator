//
// $Id: StyleSheetOrigin.java,v 1.2 2002-04-08 21:16:38 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: StyleSheetOrigin.java,v $
 * Revision 1.2  2002-04-08 21:16:38  plehegar
 * New
 *
 */
package org.w3c.css.css;

/**
 * @version $Revision: 1.2 $
 * @author  Philippe Le Hégaret
 */
public interface StyleSheetOrigin {

    /**
     * This property comes from the UA's default values.
     */  
    public static final int BROWSER = 1;
    
    /**
     * This property comes from the reader's style sheet.
     */  
    public static final int READER = 2;
    
    /**
     * This property comes from the author's style sheet.
     */  
    public static final int AUTHOR = 3;
    
}
