//
// $Id: UserProperty.java,v 1.1 2002-03-13 19:56:44 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */

package org.w3c.css.user;

import org.w3c.css.properties.CssProperty;

/**
 * @version $Revision: 1.1 $
 */
public abstract class UserProperty extends CssProperty {

  /**
   * Returns true if the property is inherited
   */
  public boolean Inherited() {
    return UserProperties.getInheritance(this);
  }

}
