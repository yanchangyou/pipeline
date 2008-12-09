package org.software.sphere.society.platform.omega.core.data.node00;

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

	public void generateByString(String data) {
		this.bool = new Boolean(data);		
	}
	
	public java.lang.String toString() {
		return super.toString() + " = " + this.bool.toString();
	}
}
