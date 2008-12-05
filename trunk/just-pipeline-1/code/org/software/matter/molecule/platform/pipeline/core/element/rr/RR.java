package org.software.matter.molecule.platform.pipeline.core.element.rr;

import org.software.matter.molecule.platform.pipeline.core.element.Element;
import org.software.matter.molecule.platform.pipeline.core.element.common.Data;

public class RR extends Element {

	protected Data data;

	public RR() {
		super();
	}
	
	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

}
