package org.software.sphere.society.platform.pipeline.core.real;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.software.sphere.society.platform.pipeline.common.Logable;
import org.software.sphere.society.platform.pipeline.core.core.Request;
import org.software.sphere.society.platform.pipeline.core.core.Response;
import org.software.sphere.society.platform.pipeline.core.core.Session;
import org.software.sphere.society.platform.pipeline.core.flow.FlowNode;

/**
 * ��һ������ӿ�<br>
 * ͬ��Ҳ����ʵ�ڵ������̽ڵ�Ľӿ�<br>
 * Ҳ����ʵ�ڵ��Ҷ�ڵ�<br>
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-16 ����12:51:39
 * @file : Service.java
 * @version : 0.1
 */
public class Service extends RealNode implements Logable {

	private java.lang.String protocol;

	private java.lang.String port;

	private java.lang.String host;

	private Request request;

	private Response response;

	/**
	 * �����б�
	 */
	protected List flowList = new ArrayList();

	public Service() {
	}

	/**
	 * ��������, Ȼ����˳��ִ��
	 */
	public void appendFlow(FlowNode flow) {
		flowList.add(flow);
		flow.setPreNode(this);
		this.addNextNode(flow);
	}

	public int getIntPort() {
		return Integer.parseInt(port);
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	/**
	 * �����ṩ����
	 * 
	 * @param clientSession
	 * @throws ConnectException
	 * @throws Exception
	 */
	public void service() throws ConnectException, Exception {
		Session clientSession = new Session(this.getRequest(), this
				.getResponse(), null);
		for (Iterator iter = flowList.iterator(); iter.hasNext();) {
			FlowNode flow = (FlowNode) iter.next();
			flow.execute(clientSession);
		}
	}

	public java.lang.String getHost() {
		return host;
	}

	public void setHost(java.lang.String host) {
		this.host = host;
	}

	public java.lang.String getProtocol() {
		return protocol;
	}

	public void setProtocol(java.lang.String protocol) {
		this.protocol = protocol;
	}

	public String toString() {
		int flowNodeLevel = this.getNode1XPreLevel();
		java.lang.String leftPad = StringUtils.leftPad(" ", flowNodeLevel * 4);
		StringBuffer buf = new StringBuffer();
		buf.append("\r\n").append(leftPad).append(super.toString());
		buf.append("\r\n").append(leftPad).append("flowList = {").append(
				flowList);
		buf.append("\r\n").append(leftPad).append("request : ").append(request);
		buf.append("\r\n").append(leftPad).append("response : ").append(
				response).append("]");
		return buf.toString();
	}
}
