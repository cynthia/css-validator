package org.w3c.css.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.CacheResponse;
import java.util.List;
import java.util.Map;

// @@@
abstract public class CacheConnection extends Connection {

	private CacheResponse cr;
	
	public CacheConnection(CacheResponse cr) {
		this.cr = cr;
	}
	
	@Override
	public InputStream getBody() throws IOException {
		return cr.getBody();
	}

}
