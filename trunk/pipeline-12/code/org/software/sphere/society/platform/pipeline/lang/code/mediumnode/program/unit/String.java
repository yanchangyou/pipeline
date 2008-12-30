package org.software.sphere.society.platform.pipeline.lang.code.mediumnode.program.unit;

/**
 * ×Ö·ûÄÚÈİ
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-26 ÏÂÎç03:36:28
 * @file : String.java
 * @version : 0.1
 */
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
