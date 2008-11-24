package org.software.matter.molecule.platform.pipeline.core.element.rr;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.molecule.platform.pipeline.core.context.PipelineContext;

public abstract class Input {

	protected String data;
	
	public abstract String getData();

	public abstract void setData(String data);

	public abstract void tunePipelineContextToParam(PipelineContext pipelineContext);
	
	public Input() {
		super();
	}
	
	public String toString() {
		return new ToStringBuilder(this).toString();
	}
	
	public abstract String toStringType();

	public abstract String toXMLType();
	
	public abstract byte[] toByteType();
	
	public abstract String toJSONType();
}
