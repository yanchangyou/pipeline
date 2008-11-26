package org.software.matter.molecule.platform.pipeline.core.element.rr;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.molecule.platform.pipeline.core.context.Context;

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
