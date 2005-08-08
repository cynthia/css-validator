//
// $Id: Errors.java,v 1.3 2005-08-08 13:18:11 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/**
 * $Log: Errors.java,v $
 * Revision 1.3  2005-08-08 13:18:11  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.2  2002/04/08 21:24:12  plehegar
 * New
 *
 * Revision 2.1  1997/08/08 15:51:49  plehegar
 * Nothing
 *
 */
package org.w3c.css.parser;

import java.util.Vector;

/**
 * Controls all errors in the validator
 * @version $Revision: 1.3 $
 * @see Vector.java
 */
public final class Errors {

  private CssError[] errorData = new CssError[10];
  private int      errorCount;

  private final static int capacityIncrement = 10;

  /**
   * Add an error.
   *
   * @param error The new error.
   */  
  public final void addError(CssError error) {
    int oldCapacity = errorData.length;
    if (errorCount + 1 > oldCapacity) {
      CssError oldData[] = errorData;
      errorData = new CssError[oldCapacity + capacityIncrement];
      System.arraycopy(oldData, 0, errorData, 0, errorCount);
    }
    errorData[errorCount++] = error;
  }

  /**
   * Add errors.
   *
   * @param errors All errors
   */  
  public final void addErrors(Errors errors) {
    int oldCapacity = errorData.length;
    if (errorCount + errors.errorCount + 1 > oldCapacity) {
      CssError oldData[] = errorData;
      errorData = 
	new CssError[oldCapacity + errors.errorCount + capacityIncrement];
      System.arraycopy(oldData, 0, errorData, 0, errorCount);
    }
    System.arraycopy(errors.errorData, 0, errorData, 
		     errorCount, errors.errorCount);
    errorCount += errors.errorCount;
  }

  /**
   * Get the number of errors.
   */  
  public final int getErrorCount() {
    return errorCount;
  }

  /**
   * Get an array with all errors.
   */  
  public final CssError[] getErrors() {
    int oldCapacity = errorData.length;
    if (errorCount < oldCapacity) {
      CssError oldData[] = errorData;
      errorData = new CssError[errorCount];
      System.arraycopy(oldData, 0, errorData, 0, errorCount);
    }
    return errorData;
  }

  /**
   * Get an error with an index.
   *
   * @param index the error index.
   */  
  public final CssError getErrorAt(int index) {
    return errorData[index];
  }
  
}
