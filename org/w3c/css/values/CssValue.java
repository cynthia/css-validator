//
// $Id: CssValue.java,v 1.3 2005-08-08 13:19:47 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssValue.java,v $
 * Revision 1.3  2005-08-08 13:19:47  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.2  2002/04/08 21:19:46  plehegar
 * New
 *
 * Revision 2.2  1997/08/20 11:39:49  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:53:07  plehegar
 * Nothing
 *
 * Revision 1.3  1997/07/30 13:19:37  plehegar
 * Updated package
 *
 * Revision 1.2  1997/07/24 00:23:40  plehegar
 * Added equals()
 *
 * Revision 1.1  1997/07/18 20:28:51  plehegar
 * Initial revision
 *
 */
package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

/**
 * @version $Revision: 1.3 $
 */
public abstract class CssValue {

    String cssversion;

  /**
   * Set the value of this value.
   *
   * @param s     the string representation of the value.
   * @param frame For errors and warnings reports.
   * @exception InvalidParamException The unit is incorrect
   */  
  public abstract void set(String s, ApplContext ac) 
      throws InvalidParamException;

  /**
   * Returns the internal value
   */  
  public abstract Object get();

  /**
   * Compares two values for equality.
   *
   * @param value The other value.
   */  
  public boolean equals(Object value) {
    return super.equals(value);
  }

    public void setCssVersion(String cssversion) {
	this.cssversion = cssversion;
    }
    
    public boolean isDefault() {
	return false;
    }

}
