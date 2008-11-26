package org.software.matter.molecule.platform.pipeline.core.element.rr;

import org.software.matter.molecule.platform.pipeline.core.context.PipelineContext;

public class ByteInput extends Input {

	byte[] data;
	public byte[] toByteType() {
		return data;
	}

	public String toStringType() {
		return new String(data);
	}

	public String toXMLType() {
		// TODO Auto-generated method stub
		return new String(data);
	}

	public String toJSONType() {
		// TODO Auto-generated method stub
		return new String(data);
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
