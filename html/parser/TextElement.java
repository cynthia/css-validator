/* Copyright (c) 1996 by Groupe Bull.  All Rights Reserved */
/* $Id: TextElement.java,v 1.1 2002-03-13 19:58:37 plehegar Exp $ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */


package html.parser;

public class TextElement extends Element {
    static public final String TEXT="text";
    public TextElement() {
	super(TEXT, 0);
    }
}
