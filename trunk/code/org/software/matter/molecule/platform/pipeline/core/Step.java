package org.software.matter.molecule.platform.pipeline.core;

import java.util.Map;

import org.software.matter.atom.entity.commons.NamadObject;

public class Step extends NamadObject {

	Boolean needStore;

	String globalName;

	Algorithm algorithm;

	ParamList paramList;

	public Object deal(Map globalVariable) throws Exception {
		if (algorithm == null) {
			throw new Exception("«Î…Ë÷√À„∑®");
		}
		if (paramList != null) {
			paramList.dealGlobalVariable(globalVariable);
		}
		
		return algorithm.deal(paramList);
	}

	public Algorithm getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
	}

	public ParamList getParamList() {
		return paramList;
	}

	public void setParamList(ParamList paramList) {
		this.paramList = paramList;
	}

	public Step(String name) {
		super(name);
	}

	public Step() {
		super();
	}

	public String getGlobalName() {
		return globalName;
	}

	public void setGlobalName(String globalName) {
		this.globalName = globalName;
	}

	public Boolean getNeedStore() {
		return needStore;
	}

	public void setNeedStore(Boolean needStore) {
		this.needStore = needStore;
	}
	
	public void setNeedStore(String needStore) {
		this.needStore = new Boolean(needStore);
	}

}
