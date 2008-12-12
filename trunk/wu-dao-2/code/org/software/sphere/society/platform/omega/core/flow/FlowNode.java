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
	 * 上下文存储变量
	 */
	protected FlowNodeContext flowNodeContext;

	/**
	 * 流程列表
	 */
	protected List flowList = new ArrayList();

	/**
	 * 发出的请求
	 */
	protected Request request;

	/**
	 * 返回的响应
	 */
	protected Response response;

	

	/** 
	 * 添加变量<br>
	 * 直接放到上下文中去了
	 * 
	 * @param node
	 */
	public void addVar(Node node) {
		this.flowNodeContext.addNextNode(node);
	}
	/**
	 * 获取变量<br>
	 * 直接从上下文中获取<br>
	 * 本层没有找到就从上层中去寻找<br>
	 * 
	 * @param name
	 * @return
	 */
	public Node getVar(String name) {
		
		/**
		 * 先在流程模型里找
		 */
		FlowNode flowNode = this;
		Node value = flowNode.getFlowNodeContext().getNextNodeByName(name);
		
		while (value == null && flowNode.getPreNode() != null && (FlowNode.class.isInstance(flowNode.getPreNode()))) {
			flowNode = (FlowNode) flowNode.getPreNode();
			value = flowNode.getFlowNodeContext().getNextNodeByName(name);
		}
		
		/**
		 * 再到 现实模型中寻找
		 */
		if (value == null) { //在流程模型中没有找到, 继续往上找
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
	 * 获取节点层次数 根节点定义为0
	 * 
	 * @return 层次数
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

