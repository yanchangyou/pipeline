package org.software.sphere.society.platform.pipeline.core.real;

import org.apache.commons.lang.StringUtils;
import org.software.sphere.society.platform.pipeline.common.ServiceNode;
import org.software.sphere.society.platform.pipeline.common.ServiceNodeDefine;
import org.software.sphere.society.platform.pipeline.core.core.RealNodeContext;
import org.software.sphere.society.platform.pipeline.core.data.DataNode;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.core.data.node1X.DefaultNode1X;
import org.software.sphere.society.platform.pipeline.core.flow.FlowNode;
import org.software.sphere.society.platform.pipeline.exception.core.core.DataDealException;
import org.software.sphere.society.platform.pipeline.exception.core.core.NoAvailableGodException;
import org.software.sphere.society.platform.pipeline.exception.core.core.NoFoundDefineServiceException;
import org.software.sphere.society.platform.pipeline.exception.core.data.NextNodeNotFountException;

/**
 * 所有现实节点的父类<br>
 * 
 * 每个现实节点由五部分构成<br>
 * 1, 上下文<br>
 * 上下文用于存储数据节点, 便于程序随时存取<br>
 * 2, 服务定义<br>
 * 服务定义用于定义当前节点所拥有的服务,并且每个服务有多个竞争者提供此服务<be> 
 * 3, 数据节点列表<br>
 * 定义当前节点的初始数据节点, 并且处理到上下文中, 所有数据节点都统一存储在上下文中 
 * 4, 前驱节点<br>
 * 父节点用于关联父节点, 便于整个real node 的向前遍历 
 * 5, 后续节点<br>
 * 用于整个real node 的向后遍历
 * 
 * 6, 起始流程节点
 * 
 * 
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-8 下午07:18:53
 * @file : RealNode.java
 * @version : 0.1
 */
public class RealNode extends DefaultNode1X {

	/**
	 * 上下文
	 */
	private RealNodeContext realNodeContext;

	/**
	 * 服务定义
	 */
	protected ServiceNodeDefine serviceNodeDefine;

	/**
	 * 构造节点, 同时初始化上下文
	 *
	 */
	public RealNode() {
		this.realNodeContext = new RealNodeContext(this);// 自动把上下文与此节点关联起来
		serviceNodeDefine = new ServiceNodeDefine(); //服务存储区
	}

	/**
	 * 获取上下文
	 * @return
	 */
	public RealNodeContext getRealNodeContext() {
		return realNodeContext;
	}

	/** 
	 * 添加数据节点<br>
	 * 直接放到上下文中去了
	 * 
	 * @param node
	 */
	public void addDataNode(DataNode node) {
		this.realNodeContext.addNextNode(node);
	}
	/**
	 * 获取数据节点<br>
	 * 直接从上下文中获取
	 * 
	 * @param name
	 * @return
	 */
	public DataNode getDataNode(String name) {
		return this.realNodeContext.getNextNodeByName(name);
	}

	public void addServiceNode(ServiceNode service) {
		this.serviceNodeDefine.addService(service);
		service.setPreNode(this);
	}

	public ServiceNode getService(String name) {
		return serviceNodeDefine.getService(name);
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

	public ServiceNodeDefine getServiceNodeDefine() {
		return serviceNodeDefine;
	}

	public void setServiceNodeDefine(ServiceNodeDefine serviceDefine) {
		this.serviceNodeDefine = serviceDefine;
	}

	public RealNode getPreRealNode() {
		return (RealNode) this.preNode;
	}
	
	
	public void appendFlow(FlowNode flow) {
		this.addNextNode(flow);
		flow.setPreNode(this);
	}
	
	public Object getGod(String godHome) throws NextNodeNotFountException, DataDealException, NoAvailableGodException, NoFoundDefineServiceException {
		java.lang.String[] part = godHome.toJavaString().split("@");
		java.lang.String defineServiceName = part[0];
		RealNode realNode = null;
		if (part.length == 1) {
			realNode = this;
		} else {
			java.lang.String realPath = part[1];
			if (!realPath.trim().startsWith("self")) {
				throw new RuntimeException("抱歉!现在只支持[self]星球的查找");
			}
			if (realPath.equals("self")) {
				realNode = this;
			} else {
				realNode = (RealNode) this.getNextNodeByPath(new String(realPath.substring(realPath.indexOf('.')+1)));	
			}
		}
		
		ServiceNode defineService = realNode.getService(new String(defineServiceName));
		
		if (defineService == null) {
			throw new NoFoundDefineServiceException("没有找到你定义的服务:" + godHome + ", 请检查名字是书写错误");
		}
		
		return defineService.getAvailableGod();
	}
	
	public java.lang.String toString() {
		int flowNodeLevel = this.getNode1XPreLevel();
		java.lang.String leftPad = StringUtils.leftPad(" ", flowNodeLevel * 4);
		StringBuffer buf = new StringBuffer();
		buf.append("\r\n").append(leftPad).append(super.toString());
		buf.append("\r\n").append(leftPad).append("context = {").append(getRealNodeContext().getNextNodesMap());
		buf.append("\r\n").append(leftPad).append("service : ").append(getServiceNodeDefine().getNextNodesMap());
		buf.append("\r\n").append(leftPad).append("next = {").append(this.getNextNodesMap().toString()).append("]");
		return buf.toString();
	}
}
