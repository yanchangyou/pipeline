package org.software.sphere.society.platform.pipeline.lang.code.realnode;

public class Server extends RealNode {
	
	
	public Server() {
	}
	
	protected String host;
	
	public String getHost() {
//		if (host == null || host.length() == 0) {
//			host = this.getMeta().getProperty("host");
//		}
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
}
