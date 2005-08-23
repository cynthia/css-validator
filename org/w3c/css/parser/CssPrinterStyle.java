//
// $Id: CssPrinterStyle.java,v 1.3 2005-08-23 16:22:54 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssPrinterStyle.java,v $
 * Revision 1.3  2005-08-23 16:22:54  ylafon
 * Patch by Jean-Guilhem Rouel
 *
 * Better handling of media and properties files
 * Major reorganization of those properties files
 *
 * Revision 1.2  2002/04/08 21:24:12  plehegar
 * New
 *
 * Revision 1.1  1997/08/20 11:41:28  plehegar
 * Initial revision
 *
 */
package org.w3c.css.parser;

import org.w3c.css.properties.css1.CssProperty;

/**
 * This class is invoke by all propperties when a print is required.
 *
 * @version $Revision: 1.3 $
 * @see org.w3c.css.parser.CssStyle#print
 */
public interface CssPrinterStyle {

  /**
   * Print this property.
   *
   * @param property The property to print.
   * @see org.w3c.css.properties.css1.CssProperty#toString
   * @see org.w3c.css.properties.css1.CssProperty#getPropertyName
   */
  public void print(CssProperty property);
}
