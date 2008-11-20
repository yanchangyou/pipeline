package org.software.matter.molecule.platform.pipeline.core.element.soa;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.atom.entity.commons.NameAndTypeAndMetaObject;
import org.software.matter.molecule.platform.pipeline.core.element.common.Meta;

public class Server extends NameAndTypeAndMetaObject {
	
	private Meta meta;
	
	private Map serviceMap = new HashMap();
	/**
	 * ÃÌº”service
	 * @param service
	 */
	public void addService(Service service) {
		serviceMap.put(service.getName(), service);
	}
	

	/**
	 * ªÒ»°service
	 * 
	 */
	public Service getService(String serviceName) {
		return (Service) serviceMap.get(serviceName);
	}


	private String type;
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Meta getMeta() {
		return meta;
	}


	public void setMeta(Meta meta) {
		this.meta = meta;
	}
	
	
	public String toString() {
		return new ToStringBuilder(this).toString() + this.getName() + " : " + serviceMap.toString();
	}

}
