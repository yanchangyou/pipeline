package org.software.sphere.atom.entity.commons;

public class NameAndTypeObject implements Namable, Typable {

	protected String type;

	protected String name;

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
