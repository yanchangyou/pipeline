package org.software.sphere.society.platform.pipeline.lang.code.flownode;

import java.net.ConnectException;
import java.util.List;

import org.software.sphere.society.platform.pipeline.common.Logable;
import org.software.sphere.society.platform.pipeline.common.Namable;
import org.software.sphere.society.platform.pipeline.lang.Session;

public abstract class FlowNode implements Logable, Namable {
	
	protected List flowList;
	
	public abstract void execute(Session clientSession) throws ConnectException,
	Exception ;
	
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	public List getFlowList() {
		return flowList;
	}

	public void setFlowList(List flowList) {
		this.flowList = flowList;
	}
	public void defaultExecute(Session clientSession) {
		// TODO Auto-generated method stub
		
	}

	
}

