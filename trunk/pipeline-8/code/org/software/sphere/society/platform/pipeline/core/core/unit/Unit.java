package org.software.sphere.society.platform.pipeline.core.core.unit;

import org.software.sphere.society.platform.pipeline.core.core.Request;
import org.software.sphere.society.platform.pipeline.core.core.Response;
import org.software.sphere.society.platform.pipeline.core.data.DataNode;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.core.flow.FlowNode;

public abstract class Unit extends FlowNode {

	/**
	 * 服务, 按名定义的服务, 可能对应多个现实的服务
	 */
	protected java.lang.String service;
	
	public abstract void encodeRequestData(Request request, DataNode paramater);
	
	public abstract void decodeResponseData(Response response, String responseData);

	
	public abstract Object getGod() throws Exception;
	
	public java.lang.String getService() {
		return service;
	}

	public void setService(java.lang.String service) {
		this.service = service;
	}
	
	public java.lang.String toString(){ 
		return super.toString() + ", servive :" + service;
	}
}
