package org.software.sphere.society.platform.omega.core.real;

import java.util.Iterator;

import org.software.sphere.society.platform.omega.core.flow.Flow;


public class Server extends RealNode {
	
	
	public Server() {
	}
	
	
	public void addFlow(Flow flow) {
		this.addNextNode(flow);
	}
	
	public Flow getMainServiceFlow() {
		Flow flow = null;
		for (Iterator iter = flow.getFlowList().iterator(); iter.hasNext();) {
			flow = (Flow) iter.next();
			if (flow.getName().equals("main")) {
				break;
			}
		}
		return flow;
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
