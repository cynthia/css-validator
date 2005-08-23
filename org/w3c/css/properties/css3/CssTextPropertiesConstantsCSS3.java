//
// $Id: CssTextPropertiesConstantsCSS3.java,v 1.1 2005-08-23 16:24:20 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssTextPropertiesConstantsCSS3.java,v $
 * Revision 1.1  2005-08-23 16:24:20  ylafon
 * Patch by Jean-Guilhem Rouel
 *
 * Better handling of media and properties files
 * Major reorganization of those properties files
 *
 * Revision 1.2  2002/12/23 08:24:07  sijtsche
 * text-decoration values changed
 *
 * Revision 1.1  2002/08/07 14:21:31  sijtsche
 * lining values added
 *
 * Revision 1.2  2002/04/08 21:17:44  plehegar
 * New
 *
 * Revision 1.2  1997/07/30 13:20:21  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/24 01:39:42  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties.css3;

/**
 * @version $Revision: 1.1 $
 */
public interface CssTextPropertiesConstantsCSS3 {
  public static String[] TEXTDECORATION = {
    "none", "underline", "overline", "line-through", "blink", "inherit" };

  public static String[] TEXTDECORATIONMOB = {
    "underline", "inherit" };

  public static String[] VERTICALALIGN = {
    "baseline", "sub", "super", "top", "text-top", "middle",
    "bottom", "text-bottom", "inherit" };

  public static String[] VERTICALALIGNMOB = {
    "baseline", "sub", "super", "inherit" };

  public static String[] TEXTTRANSFORM = {
    "none", "capitalize", "uppercase", "lowercase", "inherit" };

  public static String[] TEXTALIGN = {
    "left", "right", "center", "justify", "inherit", "start", "end" };

}
