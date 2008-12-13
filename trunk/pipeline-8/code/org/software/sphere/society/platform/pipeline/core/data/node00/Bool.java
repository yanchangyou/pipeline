package org.software.sphere.society.platform.pipeline.core.data.node00;

import org.software.sphere.society.platform.pipeline.exception.data.DataGenerateException;

public class Bool extends Node00 {

	protected Boolean bool;

	public Bool() {
		
	}
	
	public Bool(java.lang.String value) {
		bool = new Boolean(value);
	}
	
	public Boolean getBool() {
		return bool;
	}

	public void setBool(Boolean bool) {
		this.bool = bool;
	}
	
	public java.lang.String toString() {
		return super.toString() + " = " + this.bool;
	}

	public void fromJavaString(String data) throws DataGenerateException {
		bool = new Boolean(data);
	}

	public String toJavaString() {
		// TODO Auto-generated method stub
		return "" + bool;
	}
}
