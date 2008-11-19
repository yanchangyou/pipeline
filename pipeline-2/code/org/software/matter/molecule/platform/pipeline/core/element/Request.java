package org.software.matter.molecule.platform.pipeline.core.element;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.atom.entity.commons.NamedObject;

public class Request extends NamedObject {

	private Input input;

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}
	

	public String toString() {
		return new ToStringBuilder(this).toString();
	}
}
