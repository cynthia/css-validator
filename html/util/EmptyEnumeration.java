/* Copyright (c) 1997 by Groupe Bull.  All Rights Reserved */
/* $Id: EmptyEnumeration.java,v 1.1 2002-03-13 20:37:23 plehegar Exp $ */
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
