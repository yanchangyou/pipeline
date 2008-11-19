package org.software.matter.molecule.platform.pipeline.core.element;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.atom.entity.commons.NamedObject;

public class Response extends NamedObject {
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
