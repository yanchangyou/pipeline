package org.software.sphere.society.platform.omega.core.element.esoa;


public class Server extends ESOA {
	
	public Server() {
	}
	
	protected String host;
	
	public String getHost() {
		if (host == null || host.length() == 0) {
			host = this.getMeta().getProperty("host");
		}
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * ªÒ»°service
	 * 
	 */
	public Service getService(String serviceName) {
		return (Service) this.context.getChild(serviceName);
	}
}
