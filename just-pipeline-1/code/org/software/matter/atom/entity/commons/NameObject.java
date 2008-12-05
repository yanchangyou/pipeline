package org.software.matter.atom.entity.commons;

import org.apache.commons.lang.builder.ToStringBuilder;

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
		return new ToStringBuilder(this).toString();
	}
}
