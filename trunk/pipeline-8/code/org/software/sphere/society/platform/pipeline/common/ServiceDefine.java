package org.software.sphere.society.platform.pipeline.common;

import org.software.sphere.society.platform.pipeline.core.data.node0X.DefaultNode0X;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
public class ServiceDefine extends DefaultNode0X {

	public void addService(Service service) {
		this.addNextNode(service);
		service.setPreNode(this);
	}

	public Service getService(String name) {
		return (Service) this.getNextNodeByName(name);
	}
}
