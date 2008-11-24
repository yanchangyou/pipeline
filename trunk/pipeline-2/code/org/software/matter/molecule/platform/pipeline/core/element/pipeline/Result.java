package org.software.matter.molecule.platform.pipeline.core.element.pipeline;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Result {

	private Map resultMap = new HashMap(5);

	private Map defineResultMap = new HashMap(5);

	private Map referResultMap = new HashMap(5);

	public void addDefineResult(DefineResult defineResult) {
		defineResultMap.put(defineResult.getName(), defineResult);
		resultMap.put(defineResult.getName(), defineResult);
	}

	public void addReferResult(ReferResult referResult) {
		referResultMap.put(referResult.getName(), referResult);
	}

	public void dealReferResult(String responseData) {
		Set set = referResultMap.keySet();

		for (Iterator iter = set.iterator(); iter.hasNext();) {
			String name = (String) iter.next();
//			String value = (String) referResultMap.get(name);
			
			resultMap.put(name, responseData);
		}
	}

	public Object getResult(String name) {
		return resultMap.get(name);
	}

	public Map getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map resultMap) {
		this.resultMap = resultMap;
	}
}
