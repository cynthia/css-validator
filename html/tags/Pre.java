/* Copyright (c) 1997 by Groupe Bull.  All Rights Reserved */
/* $Id: Pre.java,v 1.1 2002-03-13 20:36:59 plehegar Exp $ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */

package html.tags;

import html.tree.*;

public class Pre extends HtmlTree {

    public boolean isBlock() {
	return true;
    }

    public boolean isPreformatted() {
	return true;
    }
}
