package org.software.matter.molecule.platform.pipeline.core.element.rr;

import org.software.matter.molecule.platform.pipeline.core.context.Context;


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

	public String getData() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setData(String data) {
		// TODO Auto-generated method stub
		
	}

	public void tuneContextToParam(Context context) {
		// TODO Auto-generated method stub
		
	}

	public void tuneParamToContext(Context context) {
		// TODO Auto-generated method stub
		
	}
}
