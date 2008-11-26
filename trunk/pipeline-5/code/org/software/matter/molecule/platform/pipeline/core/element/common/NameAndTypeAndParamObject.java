package org.software.matter.molecule.platform.pipeline.core.element.common;

import org.software.matter.atom.entity.commons.NameAndTypeObject;
import org.software.matter.molecule.platform.pipeline.core.element.pipeline.pr.Param;

public class NameAndTypeAndParamObject extends NameAndTypeObject{

	private Param param;
	
	public Param getParam() {
		return param;
	}

	public void setParam(Param param) {
		this.param = param;
	}
}
