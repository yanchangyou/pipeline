package org.software.sphere.society.platform.omega.core.flow;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.software.sphere.society.platform.omega.common.Logable;
import org.software.sphere.society.platform.omega.core.data.Node;
import org.software.sphere.society.platform.omega.core.data.node0X.String;
import org.software.sphere.society.platform.omega.core.data.node1X.DefaultNode1X;
import org.software.sphere.society.platform.omega.core.lang.execute.FlowNodeContext;
import org.software.sphere.society.platform.omega.core.lang.execute.Request;
import org.software.sphere.society.platform.omega.core.lang.execute.Response;
import org.software.sphere.society.platform.omega.core.lang.execute.Session;
import org.software.sphere.society.platform.omega.core.real.RealNode;
import org.software.sphere.society.platform.omega.exception.data.MiddleNodeNotFountException;

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
	 * @throws MiddleNodeNotFountException 
	 */
	public Node getVar(String name) {
		
		/**
		 * ��������ģ������
		 */
		FlowNode flowNode = this;
		Node value = null;
		try {
			value = flowNode.getFlowNodeContext().getNextNodeByPath(name);
		} catch (MiddleNodeNotFountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (value == null && flowNode.getPreNode() != null && (FlowNode.class.isInstance(flowNode.getPreNode()))) {
			flowNode = (FlowNode) flowNode.getPreNode();
			try {
				value = flowNode.getFlowNodeContext().getNextNodeByPath(name);
			} catch (MiddleNodeNotFountException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/**
		 * �ٵ� ��ʵģ����Ѱ��
		 */
		if (value == null) { //������ģ����û���ҵ�, ����������
			RealNode realNode = (RealNode) flowNode.getPreNode();
			try {
				value = realNode.getRealNodeContext().getNextNodeByPath(name);
			} catch (MiddleNodeNotFountException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			while (value == null && realNode.getPreNode() != null && realNode.getPreNode() instanceof RealNode) {
				realNode = (RealNode) realNode.getPreNode();
				try {
					value = realNode.getRealNodeContext().getNextNodeByPath(name);
				} catch (MiddleNodeNotFountException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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

	/**
	 * ���̴����ִ�нӿ�, ���������ʵ��
	 * @param clientSession
	 * @throws ConnectException
	 * @throws Exception
	 */
	public abstract void execute(Session clientSession)
			throws ConnectException, Exception;

	/**
	 * һ��ȱʡ��ִ��ģʽ:˳��ִ��
	 * @param clientSession
	 * @throws ConnectException
	 * @throws Exception
	 */
	public void defaultExecute(Session clientSession)
			throws ConnectException, Exception {
		for (Iterator iter = flowList.iterator(); iter.hasNext();) {
			FlowNode flow = (FlowNode) iter.next();
			flow.execute(clientSession);
		}
	}

	
	
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

