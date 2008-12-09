package org.software.sphere.society.platform.omega.core.common;

import org.software.sphere.society.platform.omega.core.data.node00.Node00;
import org.software.sphere.society.platform.omega.exception.data.DataGenerateException;

public class Competitor extends Node00 {

	private String priority;

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public java.lang.String toString() {
		return "[" + this.name + ", " + this.priority + "]";
	}

	public void generateByString(String data) throws DataGenerateException {
		// TODO Auto-generated method stub
		
	}
}
