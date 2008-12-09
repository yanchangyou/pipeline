package org.software.sphere.society.platform.omega.core.common;

import org.software.sphere.society.platform.omega.core.data.node00.Node00;
import org.software.sphere.society.platform.omega.exception.data.DataGenerateException;

public class Property extends Node00 {

	private java.lang.String value;
	
	public void generateByString(String data) throws DataGenerateException {
		// TODO Auto-generated method stub
		
	}

	public java.lang.String getValue() {
		return value;
	}

	public void setValue(java.lang.String value) {
		this.value = value;
	}
	
	public java.lang.String toString() {
		return value;
	}
	
}
