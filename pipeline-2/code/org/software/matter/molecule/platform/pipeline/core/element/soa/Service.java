package org.software.matter.molecule.platform.pipeline.core.element.soa;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.atom.entity.commons.NameAndTypeAndMetaObject;
import org.software.matter.molecule.platform.pipeline.core.element.pipeline.Pipeline;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Request;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Response;

public class Service extends NameAndTypeAndMetaObject {

	private Request request;

	private Response response;

	private Pipeline pipeline;

	private String port;
	
	public String getPort() {
		if (port == null || port.length() == 0) {
			port = this.getMeta().getProperty("port");
		}
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Request getRequest() {
		return request;
	}

	public Pipeline getPipeline() {
		return pipeline;
	}

	public void setPipeline(Pipeline pipeline) {
		this.pipeline = pipeline;
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

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

	public String toString() {
		return new ToStringBuilder(this).toString() + "\npipeline : " + pipeline + "\nrequest : " + request + "\nresponse : " + response;
	}
}
