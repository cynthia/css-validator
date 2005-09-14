/* Copyright (c) 1997 by Groupe Bull.  All Rights Reserved */
/* $Id: EmptyEnumeration.java,v 1.3 2005-09-14 15:14:17 ylafon Exp $ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */

package html.util;

import java.util.*;

public class EmptyEnumeration implements Enumeration {
    public boolean hasMoreElements() {
	return false;
    }

    public Object nextElement() {
	return null;
    }
}
