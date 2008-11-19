package org.software.matter.molecule.platform.pipeline.core.element;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Response {
	private Output output;

	public Output getOutput() {
		return output;
	}

	public void setOutput(Output output) {
		this.output = output;
	}
	

	public String toString() {
		return new ToStringBuilder(this).toString();
	}
}
