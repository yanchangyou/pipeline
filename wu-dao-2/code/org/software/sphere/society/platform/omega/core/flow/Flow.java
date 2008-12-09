package org.software.sphere.society.platform.omega.core.flow;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import org.software.sphere.society.platform.omega.core.data.nodeXX.DefaultNodeXX;
import org.software.sphere.society.platform.omega.core.session.Session;

public abstract class Flow extends DefaultNodeXX {

	protected List flowList = new ArrayList();

	public void appendFlow(Flow flow) {
		flowList.add(flow);
//		this.addChildElement(flow);
	}

	public List getFlowList() {
		return flowList;
	}

	public void setFlowList(List flowList) {
		this.flowList = flowList;
	}

	public abstract void execute(Session clientSession)
			throws ConnectException, Exception;
	
	public String toString() {
		return this.flowList.toString();
	}

}
