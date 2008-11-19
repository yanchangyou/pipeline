package org.software.matter.molecule.platform.pipeline.core.element;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.atom.entity.commons.NamedObject;

public class Input extends NamedObject {

	public Input() {
		super();
	}
	

	public String toString() {
		return new ToStringBuilder(this).toString();
	}
}
