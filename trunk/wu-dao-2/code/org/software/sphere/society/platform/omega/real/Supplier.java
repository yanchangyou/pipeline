package org.software.sphere.society.platform.omega.real;

import org.software.sphere.society.platform.omega.data.node0X.String;

public class Supplier extends RealNode {
	
	/**
	 * ��ȡserver
	 * 
	 */
	public Server getServer(String serverName) {
		return (Server) this.getChildRealNode(serverName);
	}
}
