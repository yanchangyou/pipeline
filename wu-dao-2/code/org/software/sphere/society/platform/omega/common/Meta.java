package org.software.sphere.society.platform.omega.common;

import java.util.HashMap;
import java.util.Map;

/**
 * �������ڴ洢SOA���Ԫ��Ϣ, ��pipeline���������ͬ��
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-11-28 ����08:17:35
 * @file : Meta.java
 * @version : 0.1
 */
public class Meta {

	private Map propertyMap = new HashMap();

	public boolean contain(String name) {
		return propertyMap.containsKey(name);
	}
	
	public void addProperty(Property property) {
		propertyMap.put(property.getName(), property);
	}

	public void addProperty(String name, String value) {
		propertyMap.put(name, value);
	}

	public String getProperty(java.lang.String name) {
		String value = null;
		if (propertyMap.containsKey(name)) {
//			value = ((Property)propertyMap.get(name)).getValue();
		} else {
			value = null;
		}
		return value;
	}

	public String toString() {
		return propertyMap.toString() + "\n";
	}

	public Map getPropertyMap() {
		return propertyMap;
	}
}
