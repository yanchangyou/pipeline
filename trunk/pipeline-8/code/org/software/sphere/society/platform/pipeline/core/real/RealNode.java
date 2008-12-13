package org.software.sphere.society.platform.pipeline.core.real;

import org.apache.commons.lang.StringUtils;
import org.software.sphere.society.platform.pipeline.common.Service;
import org.software.sphere.society.platform.pipeline.common.ServiceDefine;
import org.software.sphere.society.platform.pipeline.core.data.Node;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.core.data.node1X.DefaultNode1X;
import org.software.sphere.society.platform.pipeline.core.lang.execute.RealNodeContext;

/**
 * 所有现实节点的父类<br>
 * 
 * 每个现实节点由五部分构成<br>
 * 1, 上下文<br>
 * 上下文用于存储变量, 便于程序随时存取<br>
 * 2, 服务定义<br>
 * 服务定义用于定义当前节点所拥有的服务,并且每个服务有多个竞争者提供此服务<be> 
 * 3, 变量列表<br>
 * 定义当前节点的初始变量, 并且处理到上下文中, 所有变量都统一存储在上下文中 
 * 4, 前驱节点<br>
 * 父节点用于关联父节点, 便于整个real node 的向前遍历 
 * 5, 后续节点<br>
 * 用于整个real node 的向后遍历
 * 
 * 
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-8 下午07:18:53
 * @file : RealNode.java
 * @version : 0.1
 */
public abstract class RealNode extends DefaultNode1X {

	/**
	 * 上下文
	 */
	private RealNodeContext realNodeContext;

	/**
	 * 服务定义
	 */
	protected ServiceDefine serviceDefine;

	/**
	 * 构造节点, 同时初始化上下文
	 *
	 */
	public RealNode() {
		this.realNodeContext = new RealNodeContext(this);// 自动把上下文与此节点关联起来
		serviceDefine = new ServiceDefine(); //服务存储区
	}

	/**
	 * 获取上下文
	 * @return
	 */
	public RealNodeContext getRealNodeContext() {
		return realNodeContext;
	}

	/** 
	 * 添加变量<br>
	 * 直接放到上下文中去了
	 * 
	 * @param node
	 */
	public void addVar(Node node) {
		this.realNodeContext.addNextNode(node);
	}
	/**
	 * 获取变量<br>
	 * 直接从上下文中获取
	 * 
	 * @param name
	 * @return
	 */
	public Node getVar(String name) {
		return this.realNodeContext.getNextNodeByName(name);
	}

	public void addService(Service service) {
		this.serviceDefine.addService(service);
		service.setPreNode(this);
	}

	public Service getService(String name) {
		return serviceDefine.getService(name);
	}

	
	/**
	 * 按名获取后一个后续节点
	 * @param NextNodeName
	 * @return
	 */
	public RealNode getNextRealNode(String NextNodeName) {
		return (RealNode) this.getNextNodeByName(NextNodeName);
	}

	/**
	 * 添加后续节点
	 * 
	 * @param realNode
	 */
	public void addNextRealNode(RealNode realNode) {
		this.addNextNode(realNode);
		realNode.setPreNode(this);
	}

	/**
	 * 获取节点层次数 根节点定义为0
	 * 
	 * @return 层次数
	 */
	public int getRealNodeLevel() {
		int realNodeLevel = 0;
		RealNode realNode = this;
		while (realNode.getPreNode() != null) {
			realNodeLevel++;
			realNode = (RealNode) realNode.getPreNode();
		}
		return realNodeLevel;
	}

	public ServiceDefine getServiceDefine() {
		return serviceDefine;
	}

	public void setServiceDefine(ServiceDefine serviceDefine) {
		this.serviceDefine = serviceDefine;
	}

	public RealNode getPreRealNode() {
		return (RealNode) this.preNode;
	}
	
	public java.lang.String toString() {
		int flowNodeLevel = this.getNode1XPreLevel();
		java.lang.String leftPad = StringUtils.leftPad(" ", flowNodeLevel * 4);
		StringBuffer buf = new StringBuffer();
		buf.append("\r\n").append(leftPad).append(super.toString());
		buf.append("\r\n").append(leftPad).append("context = {").append(getRealNodeContext().getNextNodesMap());
		buf.append("\r\n").append(leftPad).append("service : ").append(getServiceDefine().getNextNodesMap());
		buf.append("\r\n").append(leftPad).append("next = {").append(this.getNextNodesMap().toString()).append("]");
		return buf.toString();
	}
}
