//
// $Id: PagedProperty.java,v 1.2 2002-04-08 21:17:21 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */

package org.w3c.css.paged;

import org.w3c.css.properties.CssProperty;

/**
 * @version $Revision: 1.2 $
 */
public abstract class PagedProperty extends CssProperty {

  /**
   * Returns true if the property is inherited
   */
  public boolean Inherited() {
    return PagedProperties.getInheritance(this);
  }

}
