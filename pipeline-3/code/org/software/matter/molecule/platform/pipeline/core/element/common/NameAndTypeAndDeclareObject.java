package org.software.matter.molecule.platform.pipeline.core.element.common;

import org.software.matter.atom.entity.commons.NameAndTypeObject;
import org.software.matter.molecule.platform.pipeline.core.element.pipeline.Declare;

public class NameAndTypeAndDeclareObject extends NameAndTypeObject{

	
	private Declare declare;
	
	public Declare getDeclare() {
		return declare;
	}

	public void setDeclare(Declare declare) {
		this.declare = declare;
	}
}
