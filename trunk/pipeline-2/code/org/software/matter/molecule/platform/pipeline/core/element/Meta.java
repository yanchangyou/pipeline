package org.software.matter.molecule.platform.pipeline.core.element;

import java.util.HashMap;
import java.util.Map;

public class Meta {

	private Map propertyMap = new HashMap();
	
	public void addProperty(Property property) {
		propertyMap.put(property.getName(), property.getValue());
	}
	
	public String getProperty(String name) {
		return propertyMap.get(name).toString();
	}
}
