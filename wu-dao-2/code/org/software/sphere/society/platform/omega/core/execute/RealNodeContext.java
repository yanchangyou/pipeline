package org.software.sphere.society.platform.omega.core.execute;

import java.util.Map;

import org.software.sphere.society.platform.omega.core.data.node0X.String;
import org.software.sphere.society.platform.omega.core.data.node1X.DefaultNode1X;
import org.software.sphere.society.platform.omega.core.real.RealNode;

/**
 * 上下文<br>
 * 目标 1, 元素导航---向上向下导航<br>
 * 目标 2, 变量存储<br>
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-2 下午09:30:51
 * @file : RealNodeContext.java
 * @version : 0.1
 */
public class RealNodeContext extends DefaultNode1X {

	public RealNodeContext(RealNode thisRealNode) {
		super();
		this.preNode = thisRealNode;
//		this.parent = null;
	}
	
	public RealNode getSuitablePathRealNode(java.lang.String path) {
		RealNode realNode = null;
		if (path.startsWith(KeyWords.THIS_KEY_WORLD + ".")) {
			realNode = getRelativePathRealNode(path.substring(KeyWords.THIS_KEY_WORLD.length() + 1));
		} else {
			realNode = getAbsolutePathRealNode(path);
		}
		return realNode;
	}
	
	public RealNodeContext getSuitablePathRealNodeContext(java.lang.String path) {
		return null;//getSuitablePathRealNode(path).getRealNodeContext();
	}

	public RealNode getRelativePathRealNode(java.lang.String path) {
		RealNodeContext RealNodeContext = this;
		RealNode realNode = null;

		java.lang.String[] pathArray = path.split("\\.");

		for (int i = 0; i < pathArray.length; i++) {
			if (pathArray[i].equals(KeyWords.SUPER_KEY_WORLD)) {
				realNode = RealNodeContext.getParentRealNode();
				RealNodeContext = realNode.getRealNodeContext();
			} else {
				realNode = RealNodeContext.getChildRealNode(pathArray[i]);
				RealNodeContext = realNode.getRealNodeContext();
			}
		}
		return realNode;
	}
	
	public RealNodeContext getRelativePathRealNodeContext(java.lang.String path) {
		return getRelativePathRealNode(path).getRealNodeContext();
	}

	public RealNode getAbsolutePathRealNode(java.lang.String path) {
		RealNode realNode = this.getRootRealNode();
		RealNodeContext RealNodeContext = realNode.getRealNodeContext();
		java.lang.String[] pathArray = path.split("\\.");
		for (int i = 0; i < pathArray.length; i++) {
			realNode = RealNodeContext.getChildRealNode(pathArray[i]);
			RealNodeContext = realNode.getRealNodeContext();
		}
		return realNode;
	}
	
	public RealNodeContext getAbsolutePathRealNodeContext(java.lang.String path) {
		return getAbsolutePathRealNode(path).getRealNodeContext();
	}
		
	public RealNode getThisRealNode() {
		return (RealNode) this.preNode;
	}

	public RealNode getRootRealNode() {
//		RealNode realNode = this.getThisRealNode();
//		RealNodeContext RealNodeContext = realNode.getRealNodeContext();
//		while (RealNodeContext.getParent() != null) {
//			realNode = RealNodeContext.getParentRealNode();
//			RealNodeContext = realNode.getRealNodeContext();
//		}
//		
		return null;
	}
	
	public RealNodeContext getRootRealNodeContext() {
		return getRootRealNode().getRealNodeContext();
	}
	
	public RealNode getChildRealNode(java.lang.String childName) {
		return null;//(RealNode) children.get(childName);
	}
	
//	public void addChild(RealNode realNode) {
////		if (realNode == null) {
////			throw new RuntimeException("添加的元素为空, 请检查");
////		}
////		if (realNode.getName() == null) {
////			realNode.setName("" + index);
////			index ++;
////		}
////		this.children.put(realNode.getName(), realNode);
//	}
	
	public void addChild(String name, RealNode realNode) {
		realNode.setNodeName(name);
//		this.addChild(realNode);
	}

	public void setChildren(Map children) {
//		this.children = children;
	}

	public void setParentRealNode(RealNode parent) {
//		this.setParent(parent);
	}

	public RealNode getParentRealNode() {
		return null;//(RealNode) this.getParent();
	}

	public java.lang.String toString() {
		return this.getChildNodesMap().toString();
	}
}
