package org.software.sphere.society.platform.omega.core.flow;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import org.software.sphere.society.platform.omega.common.Logable;
import org.software.sphere.society.platform.omega.common.PropertyDefine;
import org.software.sphere.society.platform.omega.core.data.Node;
import org.software.sphere.society.platform.omega.core.data.node0X.String;
import org.software.sphere.society.platform.omega.core.data.nodeXX.DefaultNodeXX;
import org.software.sphere.society.platform.omega.core.execute.Request;
import org.software.sphere.society.platform.omega.core.execute.Response;
import org.software.sphere.society.platform.omega.core.execute.Session;

public abstract class Flow extends DefaultNodeXX implements Logable {
	
	protected List flowList = new ArrayList();

	protected String service;

	protected Request request;

	protected Response response;

	
	protected PropertyDefine propertyDefine;
	
	public PropertyDefine getPropertyDefine() {
		return propertyDefine;
	}

	public void setPropertyDefine(PropertyDefine propertyDefine) {
		this.propertyDefine = propertyDefine;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}
	

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
