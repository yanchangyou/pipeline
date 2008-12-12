package org.software.sphere.society.platform.omega.core.real;

import org.software.sphere.society.platform.omega.core.flow.FlowNode;


public class Server extends RealNode {
	
	
	public Server() {
	}
	
	
	public void appendFlow(FlowNode flow) {
		this.addNextNode(flow);
		flow.setPreNode(this);
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
