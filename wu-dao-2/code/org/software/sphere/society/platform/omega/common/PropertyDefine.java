package org.software.sphere.society.platform.omega.common;

import org.software.sphere.society.platform.omega.core.data.node0X.DefaultNode0X;

public class PropertyDefine extends DefaultNode0X {

	public void addProperty(Property property) {
		this.addChildNode(property);
	}

	public Property getProperty(java.lang.String name) {
		return (Property) this.getChildNode(name);
	}
	
	
}
