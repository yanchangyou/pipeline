package org.software.matter.molecule.platform.pipeline.core.element.common;

import org.apache.commons.lang.builder.ToStringBuilder;

public class XMLInput extends Input{


	public String toString() {
		return new ToStringBuilder(this).toString();
	}

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
		// TODO Auto-generated method stub
		return data;
	}
}
