package org.software.matter.molecule.platform.pipeline.core.element.soa;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.atom.entity.commons.NameAndTypeAndMetaObject;
import org.software.matter.molecule.platform.pipeline.core.element.common.Request;
import org.software.matter.molecule.platform.pipeline.core.element.common.Response;
import org.software.matter.molecule.platform.pipeline.core.element.pipeline.Pipeline;

public class Service extends NameAndTypeAndMetaObject {

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
