package org.software.sphere.atom.entity.commons;


public class NameObject implements Namable {

	protected String name;

	public NameObject() {
		
	}
	
	public NameObject(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}
}
