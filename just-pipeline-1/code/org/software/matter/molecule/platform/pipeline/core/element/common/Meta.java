package org.software.matter.molecule.platform.pipeline.core.element.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 此类用于存储SOA层的元信息, 与pipeline层的上下文同理
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-11-28 下午08:17:35
 * @file : Meta.java
 * @version : 0.1
 */
public class Meta {

	protected Meta parent;

	private Map propertyMap = new HashMap();

	public void put(String name, Object value) {
		this.propertyMap.put(name, value);
	}

	public void put(Map map) {
		propertyMap.putAll(map);
	}

	public boolean contain(String name) {
		return propertyMap.containsKey(name);
	}

	/**
	 * 依次从下往上的找
	 * 
	 * @param name
	 * @return
	 */
	public Object smartGet(String name) {
		Object value = propertyMap.get(name);
		Meta meta = this;
		while (value == null && meta.getParent() != null) {
			meta = meta.getParent();
			value = meta.get(name);
		}
		return value;
	}
	
	public Object get(String name) {
		return propertyMap.get(name);
	}
	
	public void addProperty(Property property) {
		propertyMap.put(property.getName(), property.getValue());
	}

	public void addProperty(String name, String value) {
		propertyMap.put(name, value);
	}

	public String getProperty(String name) {
		return propertyMap.get(name).toString();
	}

	public String toString() {
		return propertyMap.toString();
	}

	public Meta getParent() {
		return parent;
	}

	public void setParent(Meta parent) {
		this.parent = parent;
	}

	public Map getPropertyMap() {
		return propertyMap;
	}
}
