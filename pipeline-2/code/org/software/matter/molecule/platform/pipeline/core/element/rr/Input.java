package org.software.matter.molecule.platform.pipeline.core.element.rr;

import org.apache.commons.lang.builder.ToStringBuilder;

public abstract class Input {

	protected String data;
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

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
