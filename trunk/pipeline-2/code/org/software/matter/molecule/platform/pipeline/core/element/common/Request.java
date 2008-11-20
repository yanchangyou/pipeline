package org.software.matter.molecule.platform.pipeline.core.element.common;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Request {

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
