//
// $Id: CssPrinterStyle.java,v 1.1 2002-03-13 19:55:33 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssPrinterStyle.java,v $
 * Revision 1.1  2002-03-13 19:55:33  plehegar
 * New
 *
 * Revision 1.1  1997/08/20 11:41:28  plehegar
 * Initial revision
 *
 */
package org.w3c.css.parser;

import org.w3c.css.properties.CssProperty;

/**
 * This class is invoke by all propperties when a print is required.
 *
 * @version $Revision: 1.1 $
 * @see org.w3c.css.parser.CssStyle#print
 */
public interface CssPrinterStyle {

  /**
   * Print this property.
   *
   * @param property The property to print.
   * @see org.w3c.css.properties.CssProperty#toString
   * @see org.w3c.css.properties.CssProperty#getPropertyName
   */
  public void print(CssProperty property);
}
