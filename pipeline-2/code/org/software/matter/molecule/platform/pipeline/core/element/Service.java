package org.software.matter.molecule.platform.pipeline.core.element;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.atom.entity.commons.NamedObject;
import org.software.matter.atom.entity.commons.Typable;

public class Service extends NamedObject implements Typable {

	private Request request;

	private Response response;

	private Pipeline pipeline;

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
