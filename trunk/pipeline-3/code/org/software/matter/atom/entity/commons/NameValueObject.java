package org.software.matter.atom.entity.commons;

public class NameValueObject implements NameValuable {
	protected String name;

	protected String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
