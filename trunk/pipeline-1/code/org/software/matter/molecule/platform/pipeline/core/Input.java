package org.software.matter.molecule.platform.pipeline.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.software.matter.atom.entity.commons.NamedObject;

public class Input extends NamedObject {

	public Input() {
		super();
	}

	private Map localParamMap = new HashMap();

	private Map globalParamNameIndexMap = new HashMap();

	public Map getLocalParamMap() {
		return localParamMap;
	}

	public void setLocalParamMap(Map localParamMap) {
		this.localParamMap = localParamMap;
	}

	public Map getGlobalParamNameIndexMap() {
		return globalParamNameIndexMap;
	}

	public void setGlobalParamNameIndexMap(Map globalParamNameIndexMap) {
		this.globalParamNameIndexMap = globalParamNameIndexMap;
	}
	
	public void addLocalParam(LocalParam localParam) {
		this.localParamMap.put(localParam.getIndex(), localParam.getValue());
	}
	
	public void addGlobalParamNameIndexMap(GlobalParam globalParam) {
		this.globalParamNameIndexMap.put(globalParam.getName(), globalParam.getIndex());
	}

	public Map mergeLocalAndGlobalParam(Map globalVariable) throws Exception {

		Map param = new HashMap(localParamMap.size() + globalParamNameIndexMap.size());
		param.putAll(localParamMap);
		for (Iterator iter = globalParamNameIndexMap.keySet().iterator(); iter
				.hasNext();) {
			String globalName = (String) iter.next();

			if (param.containsKey(globalParamNameIndexMap.get(globalName))) {
				throw new Exception("参数位置重复, 此参数的位置："
						+ globalParamNameIndexMap.get(globalName) + "，原有参数如下"
						+ param);
			} else {
				param.put(globalParamNameIndexMap.get(globalName),
						globalVariable.get(globalName));
			}
		}
		return param;
	}
	
	public Map getParam(Map globalVariable) throws Exception {
		return mergeLocalAndGlobalParam(globalVariable);
	}
}
