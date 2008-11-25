package org.software.matter.molecule.platform.pipeline.core.element.rr;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.molecule.platform.pipeline.core.context.PipelineContext;

public class Request extends Req {

	private Input input;

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}
	

	public String toString() {
		return new ToStringBuilder(this).toString();
	}
	
	public void setRequestData(String requestData) {
		input.setData(requestData);
	}

	public String getRequestData() {
		return input.getData();
	}

	public void tuneRequestData(PipelineContext pipelineContext) {
		input.tuneParamToPipelineContext(pipelineContext);		
	}
}
