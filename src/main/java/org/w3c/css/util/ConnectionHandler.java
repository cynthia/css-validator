package org.w3c.css.util;

import java.io.IOException;
import java.net.URL;

public interface ConnectionHandler {

	public Connection getConnection(URL url, URL referrer, int count, ApplContext ac) throws IOException;
	
}
