package org.software.matter.molecule.platform.pipeline.core.element.rr;



public class JSONOutput extends Input {

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
}
