package org.software.sphere.society.platform.omega.core.flow.unit;

import org.software.sphere.society.platform.omega.core.data.Node;
import org.software.sphere.society.platform.omega.core.data.node0X.String;
import org.software.sphere.society.platform.omega.core.execute.Request;
import org.software.sphere.society.platform.omega.core.execute.Response;
import org.software.sphere.society.platform.omega.core.flow.FlowNode;

public abstract class Unit extends FlowNode {

	/**
	 * ����, ��������ķ���, ���ܶ�Ӧ�����ʵ�ķ���
	 */
	protected java.lang.String service;
	
	public abstract void encodeRequestData(Request request, Node paramater);
	
	public abstract void decodeResponseData(Response response, String responseData);

	
	public abstract Object getGod() throws Exception;
	
	public java.lang.String getService() {
		return service;
	}

	public void setService(java.lang.String service) {
		this.service = service;
	}
}
