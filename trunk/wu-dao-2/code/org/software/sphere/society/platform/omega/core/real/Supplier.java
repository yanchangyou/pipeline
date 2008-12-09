package org.software.sphere.society.platform.omega.core.real;


public class Supplier extends RealNode {
	
	/**
	 * ªÒ»°server
	 * 
	 */
	public Server getServer(String serverName) {
		return (Server) this.getChildRealNode(serverName);
	}
}
