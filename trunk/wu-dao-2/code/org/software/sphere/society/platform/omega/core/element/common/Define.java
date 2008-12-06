package org.software.sphere.society.platform.omega.core.element.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 此类用于定义变量, 程序会把这些变量存储到上下文中
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-5 19:21:50
 * @file : Meta.java
 * @version : 0.1
 */

public class Define {

	final public static int DEFAUL_LENGTH_OF_MAP = 5;
	
	private Map varMap = new HashMap(DEFAUL_LENGTH_OF_MAP);

	public boolean contain(String name) {
		return varMap.containsKey(name);
	}
	
	public void addVar(Var var) {
		varMap.put(var.getName(), var);
	}

	public void addVar(String name, String value) {
		varMap.put(name, value);
	}

	public String getVar(java.lang.String name) {
		String value = null;
		if (varMap.containsKey(name)) {
			value = ((Var)varMap.get(name)).getValue();
		} else {
			value = null;
		}
		return value;
	}

	public String toString() {
		return varMap.toString();
	}

	public Map getVarMap() {
		return varMap;
	}
}
