//
// $Id: CompareFunction.java,v 1.2 2002-04-08 21:19:15 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CompareFunction.java,v $
 * Revision 1.2  2002-04-08 21:19:15  plehegar
 * New
 *
 */
package org.w3c.css.util;

/**
 * The comparaison function for the SortAlgorithm class
 *
 * @version $Revision: 1.2 $
 * @author  Philippe Le Hégaret
 */
public abstract class CompareFunction {
    public abstract boolean compare(Object obj1, Object obj2);
}
