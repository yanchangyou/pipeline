package org.software.sphere.society.platform.omega.common;

import org.software.sphere.society.platform.omega.core.data.node00.Node00;
import org.software.sphere.society.platform.omega.exception.data.DataGenerateException;

public class Competitor extends Node00 {

	private java.lang.String priority;

	public java.lang.String getPriority() {
		return priority;
	}

	public void setPriority(java.lang.String priority) {
		this.priority = priority;
	}
	
	public java.lang.String toString() {
		return "[" + this.getName() + ", " + this.priority + "]";
	}

	public void generateByString(java.lang.String data) throws DataGenerateException {
		// TODO Auto-generated method stub
		
	}

	public void fromJavaString(String data) throws DataGenerateException {
		// TODO Auto-generated method stub
		
	}

	public String toJavaString() {
		// TODO Auto-generated method stub
		return null;
	}
}
