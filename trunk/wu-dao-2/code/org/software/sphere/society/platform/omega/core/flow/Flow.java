package org.software.sphere.society.platform.omega.core.flow;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import org.software.sphere.society.platform.omega.core.data.Node;
import org.software.sphere.society.platform.omega.core.data.node0X.String;
import org.software.sphere.society.platform.omega.core.data.nodeXX.DefaultNodeXX;
import org.software.sphere.society.platform.omega.core.execute.Session;

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
	
	public java.lang.String toString() {
		return this.flowList.toString();
	}
	
	public Node getNextNode(String nodeName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Node getNextNodeByPath(String pathName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Node getNextNodeByPath(String[] pathNamesArray) {
		// TODO Auto-generated method stub
		return null;
	}
}
