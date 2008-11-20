package org.software.matter.molecule.platform.pipeline.core.element.pipeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Declare {

	private Map defineParamMap = new HashMap(5);

	private List referParamList = new ArrayList(5);

	public void addDefineParam(DefineParam aDefineParam) {
		defineParamMap.put(aDefineParam.getName(), aDefineParam);
	}

	public void addReferParam(DefineParam aDefineParam) {
		defineParamMap.put(aDefineParam.getName(), aDefineParam);
	}

	public void addReferParam(ReferParam referParam) {
		referParamList.add(referParam.getName());
	}

	public Map getDefineParamMap() {
		return defineParamMap;
	}

	public List getReferParamList() {
		return referParamList;
	}

}
