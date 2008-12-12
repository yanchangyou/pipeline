package org.software.sphere.society.platform.omega.core.flow;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.software.sphere.society.platform.omega.common.Logable;
import org.software.sphere.society.platform.omega.core.data.Node;
import org.software.sphere.society.platform.omega.core.data.node0X.String;
import org.software.sphere.society.platform.omega.core.data.node1X.DefaultNode1X;
import org.software.sphere.society.platform.omega.core.execute.FlowNodeContext;
import org.software.sphere.society.platform.omega.core.execute.Request;
import org.software.sphere.society.platform.omega.core.execute.Response;
import org.software.sphere.society.platform.omega.core.execute.Session;
import org.software.sphere.society.platform.omega.core.real.RealNode;

public abstract class FlowNode extends DefaultNode1X implements Logable {

	protected FlowNode() {
		this.flowNodeContext = new FlowNodeContext(this);
	}
	/**
	 * �����Ĵ洢����
	 */
	protected FlowNodeContext flowNodeContext;

	/**
	 * �����б�
	 */
	protected List flowList = new ArrayList();

	/**
	 * ����������
	 */
	protected Request request;

	/**
	 * ���ص���Ӧ
	 */
	protected Response response;

	

	/** 
	 * ��ӱ���<br>
	 * ֱ�ӷŵ���������ȥ��
	 * 
	 * @param node
	 */
	public void addVar(Node node) {
		this.flowNodeContext.addNextNode(node);
	}
	/**
	 * ��ȡ����<br>
	 * ֱ�Ӵ��������л�ȡ<br>
	 * ����û���ҵ��ʹ��ϲ���ȥѰ��<br>
	 * 
	 * @param name
	 * @return
	 */
	public Node getVar(String name) {
		
		/**
		 * ��������ģ������
		 */
		FlowNode flowNode = this;
		Node value = flowNode.getFlowNodeContext().getNextNodeByName(name);
		
		while (value == null && flowNode.getPreNode() != null && (FlowNode.class.isInstance(flowNode.getPreNode()))) {
			flowNode = (FlowNode) flowNode.getPreNode();
			value = flowNode.getFlowNodeContext().getNextNodeByName(name);
		}
		
		/**
		 * �ٵ� ��ʵģ����Ѱ��
		 */
		if (value == null) { //������ģ����û���ҵ�, ����������
			RealNode realNode = (RealNode) flowNode.getPreNode();
			value = realNode.getRealNodeContext().getNextNodeByName(name);
			
			while (value == null && realNode.getPreNode() != null && realNode instanceof RealNode) {
				realNode = (RealNode) realNode.getPreNode();
				value = realNode.getRealNodeContext().getNextNodeByName(name);
			}
		}
		
		return value;
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
	

	public void appendFlow(FlowNode flow) {
		flowList.add(flow);
		flow.setPreNode(this);
		this.addNextNode(flow);
	}

	public List getFlowList() {
		return flowList;
	}

	public void setFlowList(List flowList) {
		this.flowList = flowList;
	}

	public abstract void execute(Session clientSession)
			throws ConnectException, Exception;

	public FlowNodeContext getFlowNodeContext() {
		return flowNodeContext;
	}

	public void setContext(FlowNodeContext context) {
		this.flowNodeContext = context;
	}

	public FlowNode getPreFlowNode() {
		return (FlowNode) this.preNode;
	}

	public FlowNode getNextFlowNode(String nodeName) {
		return (FlowNode) this.getNextNodeByName(nodeName);
	}
	
	/**
	 * ��ȡ�ڵ����� ���ڵ㶨��Ϊ0
	 * 
	 * @return �����
	 */
	public int getFlowNodePreLevel() {
		int flowNodeLevel = 0;
		FlowNode flowNode = this;
		while (flowNode.getPreNode() != null) {
			flowNodeLevel++;
			if (FlowNode.class.isInstance(flowNode.getPreNode())) {
				flowNode = (FlowNode) flowNode.getPreNode();
			} else {
				break;
			}
		}
		return flowNodeLevel;
	}
	
	public java.lang.String toString() {
		int flowNodeLevel = this.getNode1XPreLevel();
		java.lang.String leftPad = StringUtils.leftPad(" ", flowNodeLevel * 4);
		StringBuffer buf = new StringBuffer();
		buf.append("\r\n").append(leftPad).append(super.toString());
		buf.append("\r\n").append(leftPad).append("context = {").append(getFlowNodeContext().getNextNodesMap());
		buf.append("\r\n").append(leftPad).append("request : ").append(request);
		buf.append("\r\n").append(leftPad).append("response : ").append(response);
		buf.append("\r\n").append(leftPad).append("next = {").append(this.getNextNodesMap().toString());
		return buf.toString();
	}
}

