/* Copyright (c) 1997 by Groupe Bull.  All Rights Reserved */
/* $Id: Unknown.java,v 1.1 2002-03-13 20:36:59 plehegar Exp $ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */

package html.tags;

import html.tree.*;

public class Unknown extends HtmlTree {

    public boolean isBlock() {
	return false;
    }

    public boolean isPreformatted() {
	return true;
    }

}
