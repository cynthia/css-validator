//
// $Id: InvalidParamException.java,v 1.3 2005-08-08 13:19:46 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: InvalidParamException.java,v $
 * Revision 1.3  2005-08-08 13:19:46  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.2  2002/04/08 21:19:15  plehegar
 * New
 *
 * Revision 2.1  1997/08/08 15:53:08  plehegar
 * Nothing
 *
 * Revision 1.3  1997/08/06 08:19:44  plehegar
 * Added extends ParseError
 *
 * Revision 1.2  1997/07/30 13:19:37  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/23 14:16:06  plehegar
 * Initial revision
 *
 */
package org.w3c.css.util;


import org.w3c.css.parser.analyzer.ParseException;

/**
 * @version $Revision: 1.3 $
 */
public class InvalidParamException extends ParseException {
    
    /**
     * Create a new InvalidParamException.
     */
    public InvalidParamException() {
	super();
    }
    
    /**
     * Create a new InvalidParamException with an error message.
     *
     * @param message the error message
     */
    public InvalidParamException(String message, ApplContext ac) {
	super(ac.getMsg().getErrorString((message != null)? message : ""));
    }
    
    /**
     * Create a new InvalidParamException with an error message class.
     *
     * @param error the error message class.
     * @param message a message to add
     */
    public InvalidParamException(String error, Object message, ApplContext ac) {
	super(processError(error, 
			   (message != null)?message.toString():null, "", ac));
    }
    
    /**
     * Create a new InvalidParamException.
     *
     * @param error the error message class
     * @param message1 the first message to add
     * @param message1 the second message to add
     */
    public InvalidParamException(String error, Object message1, 
				 Object message2, ApplContext ac) {
	super(processError(error, 
			   (message1 != null)?message1.toString():null,
			   (message2 != null)?message2.toString():null,
			   ac));
    }
    
    private static String processError(String error, String arg1, 
				       String arg2, ApplContext ac) {
	String str = null;

	if (error != null) {
	    str = ac.getMsg().getErrorString(error);
	}
	if (str == null) {
	    return "can't find the error message for " + error;
	} else {
	    // replace all parameters
	    for (int i = 0; (i = str.indexOf("%s")) >= 0 ; ) {
		str = str.substring(0, i) + arg1 + str.substring(i+2);
		arg1 = arg2;
	    }
	    return str;
	}
    }

} // InvalidParamException
