package org.software.matter.atom.entity.commons;

public abstract class NamadObject {

	String name;

	public NamadObject() {
		
	}
	
	public NamadObject(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
