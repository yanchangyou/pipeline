package org.software.sphere.society.platform.pipeline.lang.code.datanode.program.unit;

public class String extends Unit {

	private java.lang.String javaString;

	public String() {

	}

	public String(java.lang.String javaString) {
		setJavaString(javaString);
	}

	public java.lang.String getJavaString() {
		return javaString;
	}

	public void setJavaString(java.lang.String javaString) {
		this.javaString = javaString;
	}

	public java.lang.String toString() {
		return javaString;
	}
}
