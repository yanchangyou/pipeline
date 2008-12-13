package org.software.sphere.society.platform.pipeline.core.real;

import org.software.sphere.society.platform.pipeline.core.data.node0X.String;

public class Supplier extends RealNode {
	
	/**
	 * ªÒ»°server
	 * 
	 */
	public Server getServer(String serverName) {
		return (Server) this.getNextRealNode(serverName);
	}
}
