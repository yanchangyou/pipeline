package org.software.matter.molecule.platform.pipeline.core.element.pipeline.pr;

import org.software.matter.atom.entity.commons.NameValuable;

public class DRPR extends PR implements NameValuable {
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
