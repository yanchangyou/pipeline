package org.software.sphere.society.platform.omega.core.real;


public class Supplier extends RealNode {
	
	/**
	 * ��ȡserver
	 * 
	 */
	public Server getServer(String serverName) {
		return (Server) this.getChildRealNode(serverName);
	}
}
