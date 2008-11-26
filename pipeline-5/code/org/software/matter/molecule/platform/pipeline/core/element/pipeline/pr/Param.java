package org.software.matter.molecule.platform.pipeline.core.element.pipeline.pr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.software.matter.molecule.platform.pipeline.core.context.Context;

public class Param extends PR {

	private Map paramMap = new HashMap(5);

	private Map defineParamMap = new HashMap(5);

	private List referParamList = new ArrayList(5);

	public void addDefineParam(DefineParam defineParam) {
		defineParamMap.put(defineParam.getName(), defineParam);
	}

	public Map getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map paramMap) {
		this.paramMap = paramMap;
	}

	public void addReferParam(ReferParam referParam) {
		referParamList.add(referParam.getName());
	}

	public void addParam(String name, Object obj) {
		paramMap.put(name, obj);
	}

	public Map getDefineParamMap() {
		return defineParamMap;
	}

	public List getReferParamList() {
		return referParamList;
	}

	public void dealDefineParam() {
		Set set = defineParamMap.keySet();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			DefineParam defineParam = (DefineParam) defineParamMap.get(name);
			addParam(name, defineParam.getValue());
		}
	}
	
	public void dealReferParam(Context context) {
		for (Iterator iter = referParamList.iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			paramMap.put(name, context.get(name));
		}
	}

	public Object getParam(String name) {
		return paramMap.get(name);
	}
}
