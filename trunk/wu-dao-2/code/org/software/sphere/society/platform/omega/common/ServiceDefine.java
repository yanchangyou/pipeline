package org.software.sphere.society.platform.omega.common;

import org.software.sphere.society.platform.omega.data.node0X.DefaultNode0X;

public class ServiceDefine extends DefaultNode0X {

	public void addService(Service service) {
		this.addChildNode(service);
	}

	public Service getService(java.lang.String name) {
		return (Service) this.getChildNode(name);
	}
}
