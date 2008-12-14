package org.software.sphere.society.platform.pipeline.common;

import org.software.sphere.society.platform.pipeline.core.data.node0X.DefaultNode0X;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
public class ServiceNodeDefine extends DefaultNode0X {

	public void addService(ServiceNode service) {
		this.addNextNode(service);
		service.setPreNode(this);
	}

	public ServiceNode getService(String name) {
		return (ServiceNode) this.getNextNodeByName(name);
	}
}
