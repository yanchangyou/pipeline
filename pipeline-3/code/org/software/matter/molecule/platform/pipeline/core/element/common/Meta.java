package org.software.matter.molecule.platform.pipeline.core.element.common;

import java.util.HashMap;
import java.util.Map;

public class Meta {

	private Map propertyMap = new HashMap();
	
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
}
