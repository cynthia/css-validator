//
// $Id: StyleSheetOrigin.java,v 1.4 2005-09-14 15:14:18 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.css;

/**
 * @version $Revision: 1.4 $
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
