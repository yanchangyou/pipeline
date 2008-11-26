package org.software.matter.molecule.platform.pipeline.core.element.soa;

import java.util.HashMap;
import java.util.Map;

public class Supplier extends SOA {

	private Map serverMap = new HashMap();
	/**
	 * ÃÌº”server
	 * @param server
	 */
	public void addServer(Server server) {
		serverMap.put(server.getName(), server);
	}
	

	/**
	 * ªÒ»°server
	 * 
	 */
	public Server getServer(String serverName) {
		return (Server) serverMap.get(serverName);
	}
	
	public String toString() {
		return this.getName() + " : " + serverMap.toString();
	}

}
