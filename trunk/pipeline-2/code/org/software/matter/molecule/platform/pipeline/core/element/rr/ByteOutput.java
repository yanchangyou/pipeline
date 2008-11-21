package org.software.matter.molecule.platform.pipeline.core.element.rr;


public class ByteOutput extends Input {

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

}