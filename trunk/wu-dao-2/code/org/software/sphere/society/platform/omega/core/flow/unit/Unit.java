package org.software.sphere.society.platform.omega.core.flow.unit;

import org.software.sphere.society.platform.omega.common.PropertyDefine;
import org.software.sphere.society.platform.omega.core.execute.Request;
import org.software.sphere.society.platform.omega.core.execute.Response;
import org.software.sphere.society.platform.omega.core.flow.Flow;

public abstract class Unit extends Flow {

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
	
	public String toString() {
		return super.toString() + ", property define : " + this.getPropertyDefine() + ", request : " + this.request + ", response : " + this.response;
	}

}
