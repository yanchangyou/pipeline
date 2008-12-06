package org.software.sphere.society.platform.omega.core.element.esoa;


public class Supplier extends ESOA {

	public Supplier() {
	}
	
	/**
	 * ªÒ»°server
	 * 
	 */
	public Server getServer(String serverName) {
		return (Server) this.getContext().getChild(serverName);
	}
}
