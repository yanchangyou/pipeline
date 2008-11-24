package org.software.matter.molecule.platform.pipeline.core.element.pipeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.software.matter.molecule.platform.pipeline.core.context.PipelineContext;

public class Declare {

	private Map paramMap = new HashMap(5);

	private Map defineParamMap = new HashMap(5);

	private List referParamList = new ArrayList(5);

	public void addDefineParam(DefineParam aDefineParam) {
		defineParamMap.put(aDefineParam.getName(), aDefineParam);
		addParam(aDefineParam.getName(), aDefineParam);
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

	public void dealReferParam(PipelineContext pipelineContext) {
		for (Iterator iter = referParamList.iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			paramMap.put(name, pipelineContext.get(name));
		}
	}

	public Object getParam(String name) {
		return paramMap.get(name);
	}
}
