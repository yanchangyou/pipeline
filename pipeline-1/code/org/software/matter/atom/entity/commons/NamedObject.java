package org.software.matter.atom.entity.commons;

public abstract class NamedObject {

	String name;

	public NamedObject() {
		
	}
	
	public NamedObject(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
