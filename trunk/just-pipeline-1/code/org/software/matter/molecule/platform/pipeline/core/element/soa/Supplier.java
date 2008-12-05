package org.software.matter.molecule.platform.pipeline.core.element.soa;


public class Supplier extends SOA {

	public Supplier() {
	}
	
	/**
	 * ��ȡserver
	 * 
	 */
	public Server getServer(String serverName) {
		return (Server) this.getContext().getChild(serverName);
	}
	
	public String toString() {
		return this.getName();
	}

}
