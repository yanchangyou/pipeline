package org.software.matter.molecule.platform.pipeline.core.element.rr;

import org.software.matter.molecule.platform.pipeline.core.context.PipelineContext;


public class JSONInput extends Input {

	public byte[] toByteType() {
		return data.getBytes();
	}

	public String toStringType() {
		return data;
	}

	public String toXMLType() {
		return data;
	}

	public String toJSONType() {
		return data;
	}

	public String getData() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setData(String data) {
		// TODO Auto-generated method stub
		
	}

	public void tunePipelineContextToParam(PipelineContext pipelineContext) {
		// TODO Auto-generated method stub
		
	}

	public void tuneParamToPipelineContext(PipelineContext pipelineContext) {
		// TODO Auto-generated method stub
		
	}

}
