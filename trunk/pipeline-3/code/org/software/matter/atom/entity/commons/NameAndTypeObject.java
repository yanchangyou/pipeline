package org.software.matter.atom.entity.commons;

public class NameAndTypeObject implements Namable, Typable {

	private String type;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
