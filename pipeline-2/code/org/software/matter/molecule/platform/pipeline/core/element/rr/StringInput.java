package org.software.matter.molecule.platform.pipeline.core.element.rr;


public class StringInput extends Input {


	public byte[] toByteType() {
		return data.getBytes();
	}

	public String toStringType() {
		return data;
	}

	public String toXMLType() {
		// TODO Auto-generated method stub
		return data;
	}

	public String toJSONType() {
		// TODO Auto-generated method stub
		return data;
	}
}
