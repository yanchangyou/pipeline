package org.software.matter.molecule.platform.pipeline.core.element.rr;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.molecule.platform.pipeline.core.context.PipelineContext;

public class Response {
	private Output output;

	public Output getOutput() {
		return output;
	}

	public void setOutput(Output output) {
		this.output = output;
	}

	public String toString() {
		return new ToStringBuilder(this).toString();
	}
	
	public String getResponseData() {
		return output.getData();
	}

	public void tuneResponseData(PipelineContext pipelineContext) {
		output.tunePipelineContextToResult(pipelineContext);		
	}
}
