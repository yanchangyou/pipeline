package org.software.sphere.society.platform.omega.core.real;

import org.apache.commons.lang.StringUtils;
import org.software.sphere.society.platform.omega.common.ServiceDefine;
import org.software.sphere.society.platform.omega.core.data.Node;
import org.software.sphere.society.platform.omega.core.data.node0X.String;
import org.software.sphere.society.platform.omega.core.data.node1X.DefaultNode1X;
import org.software.sphere.society.platform.omega.core.execute.KeyWords;
import org.software.sphere.society.platform.omega.core.execute.RealNodeContext;

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
 * 4, 父节点<br>
 * 父节点用于关联父节点, 便于整个real node 的向上导航 
 * 5, 子节点列表<br>
 * 用于整个real node 的向下导航
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
	 * 父节点
	 */
	protected RealNode parentNode;

	/**
	 * 上下文
	 */
	protected RealNodeContext realNodeContext;

	/**
	 * 变量定义
	 */
//	protected VarDefine varDefine;

	/**
	 * 服务定义
	 */
	protected ServiceDefine serviceDefine;

	public RealNode() {
		this.realNodeContext = new RealNodeContext(this);// 自动把上下文与此节点关联起来
	}

	public RealNodeContext getRealNodeContext() {
		return realNodeContext;
	}

	public void setRealNodeContext(RealNodeContext realNodeContext) {
		this.realNodeContext = realNodeContext;
	}
//
//	public VarDefine getVarDefine() {
//		return varDefine;
//	}
//
//	public void setVarDefine(VarDefine varDefine) {
//		this.varDefine = varDefine;
//	}

	public void addVar(Node node) {
		this.realNodeContext.addChildNode(node);
	}

	public Node getVar(String name) {
		return this.realNodeContext.getChildNode(name);
	}

	public RealNode getChildRealNode(String childNodeName) {
		return (RealNode) this.getChildNode(childNodeName);
	}

	/**
	 * 添加子节点并且设置parent
	 * 
	 * @param realNode
	 */
	public void addChildRealNode(RealNode realNode) {
		this.addChildNode(realNode);
		realNode.setParentNode(this);
//		realNode.tuneVarToRealNodeContext();
	}
//
//	public void tuneVarToRealNodeContext() {
//		this.varDefine.dealChildNode(new NodeDealer() {
//			public void deal(Node node) {
//				RealNode.this.realNodeContext.addChildNode(node);
//			}
//		});
//	}

	// public

	public java.lang.String toString() {
		int RealNodeLevel = getRealNodeLevel();
		java.lang.String leftPad = StringUtils.leftPad(" ", RealNodeLevel * 4);
		return "\n" + leftPad + super.toString() + " = \n" + leftPad
//				+ "{var = {" + this.getVarDefine() + "}, \n" + leftPad
				+ "{context = {" + this.getRealNodeContext() + "}, \n" + 
				leftPad	+ "{service = {" + this.getServiceDefine() + "}, \n" + 
				leftPad	+ "children = {\n" + 
				leftPad + this.getChildNodesMap()+ "}]\n";
	}

	public int getRealNodeLevel() {
		int realNodeLevel = 0;
		RealNode realNode = this;
		while (realNode.getParentNode() != null) {
			realNodeLevel++;
			realNode = (RealNode) realNode.getParentNode();
		}
		return realNodeLevel;
	}

	public RealNode getSuitablePathRealNode(java.lang.String path) {
		RealNode RealNode = null;
		if (path.startsWith(KeyWords.THIS_KEY_WORLD + ".")) {
			RealNode = getRelativePathRealNode(path
					.substring(KeyWords.THIS_KEY_WORLD.length() + 1));
		} else {
			RealNode = getAbsolutePathRealNode(path);
		}
		return RealNode;
	}

	public RealNode getRelativePathRealNode(java.lang.String path) {
		RealNode RealNode = this;

//		java.lang.String[] pathArray = path.split("\\.");

		// for (int i = 0; i < pathArray.length; i++) {
		// if (pathArray[i].equals(KeyWords.SUPER_KEY_WORLD)) {
		// RealNode = (RealNode) RealNode.getParent();
		// } else {
		// RealNode = (RealNode) RealNode.getChild(pathArray[i]);
		// }
		// }
		return RealNode;
	}

	public RealNode getAbsolutePathRealNode(java.lang.String path) {
		RealNode RealNode = this.getRootRealNode();
		java.lang.String[] pathArray = path.split("\\.");
		for (int i = 0; i < pathArray.length; i++) {
			// RealNode = (RealNode) RealNode.getChild(pathArray[i]);
		}
		return RealNode;
	}

	public RealNode getRootRealNode() {
		RealNode RealNode = this;
		// while (RealNode.getParent() != null) {
		// RealNode = (RealNode) RealNode.getParent();
		// }
		return RealNode;
	}

	public RealNodeContext getRootRealNodeContext() {
		return getRootRealNode().getRealNodeContext();
	}

	public ServiceDefine getServiceDefine() {
		return serviceDefine;
	}

	public void setServiceDefine(ServiceDefine serviceDefine) {
		this.serviceDefine = serviceDefine;
	}

	public RealNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(RealNode parentNode) {
		this.parentNode = parentNode;
	}

}
