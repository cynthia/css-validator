/* Copyright (c) 1997 by Groupe Bull.  All Rights Reserved */
/* $Id: HtmlStreamListener.java,v 1.1 2002-03-13 20:36:59 plehegar Exp $ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */

package html.tags;

public interface HtmlStreamListener {
    public void notifyActivity(int lines, long bytes);
}
