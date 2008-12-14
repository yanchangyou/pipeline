package org.software.sphere.society.platform.pipeline.core.core;

import org.software.sphere.society.platform.pipeline.common.Commons;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.core.data.node1X.DefaultNode1X;
import org.software.sphere.society.platform.pipeline.core.flow.FlowNode;

/**
 * 上下文<br>
 * 目标 1, 元素遍历向前向后遍历<br>
 * 目标 2, 数据节点存储<br>
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-2 下午09:30:51
 * @file : FlowNodeContext.java
 * @version : 0.1
 */
public class FlowNodeContext extends DefaultNode1X {

	public FlowNodeContext(FlowNode thisFlowNode) {
		super();
		this.preNode = thisFlowNode;
	}
	
	/**
	 * 获取合适的上下文节点---和文件路径概念相同<br>
	 * 
	 * 三种规则 :<br>
	 * 1, 以[this]关键字开头的使用相对路径,  this和java中一样, this后面可以跟 super, '
	 *     比如 this.super.super表示向上查找两层, 这个和文件路径的规则一样<br>
	 * 2, 以[root]关键字开头的使用绝对路径<br>
	 * 3, 以[super]关键字开头 一个super代表向前一层 
	 * 4, 不以这三个关键字开头的统一使用 相对路径, 这个和文件文件相适应
	 * 
	 * @param path
	 * @return
	 */
	public FlowNode getSuitablePathFlowNode(String path) {
		FlowNode flowNode = null;
		
		if (path.toJavaString().startsWith(KeyWords.THIS_KEY_WORLD + ".")) {
			flowNode = getRelativePathFlowNode(new String(path.toJavaString().substring(KeyWords.THIS_KEY_WORLD.toJavaString().length() + 1)));
		} else if (path.toJavaString().startsWith(KeyWords.ROOT_KEY_WORLD + ".")) {
			flowNode = getAbsolutePathFlowNode(new String(path.toJavaString().substring(KeyWords.ROOT_KEY_WORLD.toJavaString().length() + 1)));
		} else {
			flowNode = getRelativePathFlowNode(path);
		}
		return flowNode;
	}
	
	/**
	 * 获取适合节点的上下文
	 * @param path
	 * @return
	 */
	public FlowNodeContext getSuitablePathFlowNodeContext(String path) {
		return getSuitablePathFlowNode(path).getFlowNodeContext();
	}

	/**
	 * 获取相对路径节点
	 * 
	 * @param path
	 * @return
	 */
	public FlowNode getRelativePathFlowNode(String path) {
		FlowNode flowNode = this.getThisFlowNode();

		String[] pathArray = Commons.convertToStringArray(path.toJavaString().split("\\."));

		for (int i = 0; i < pathArray.length; i++) {
			if (pathArray[i].equals(KeyWords.SUPER_KEY_WORLD)) {
				flowNode = flowNode.getPreFlowNode();
			} else {
				flowNode = flowNode.getNextFlowNode(pathArray[i]);
			}
		}
		return flowNode;
	}
	
	/**
	 * 获取相对路径节点的上下文
	 * 
	 * @param path
	 * @return
	 */
	public FlowNodeContext getRelativePathFlowNodeContext(String path) {
		return getRelativePathFlowNode(path).getFlowNodeContext();
	}
	/**
	 * 获取绝对路径的节点
	 * 
	 * @param path
	 * @return
	 */
	public FlowNode getAbsolutePathFlowNode(String path) {
		FlowNode flowNode = this.getSelfFlowNode();
		String[] pathArray = Commons.convertToStringArray(path.toJavaString().split("\\."));
		for (int i = 0; i < pathArray.length; i++) {
			flowNode = flowNode.getNextFlowNode(pathArray[i]);
		}
		return flowNode;
	}
	
	/**
	 * 获取绝对路径节点的上下文
	 * 
	 * @param path
	 * @return
	 */
	public FlowNodeContext getAbsolutePathFlowNodeContext(String path) {
		return getAbsolutePathFlowNode(path).getFlowNodeContext();
	}
		
	/**
	 * 获取此上下文的节点
	 * 
	 * @return
	 */
	public FlowNode getThisFlowNode() {
		return (FlowNode) this.preNode;
	}

	/**
	 * 获取Global节点<br>
	 * 
	 * 不断的向前遍历
	 * 
	 * @return
	 */
	public FlowNode getSelfFlowNode() {
		FlowNode flowNode = this.getThisFlowNode();
		while (flowNode.getPreNode() != null) {
			flowNode = flowNode.getPreFlowNode();
		}
		return flowNode;
	}
	
	/**
	 * Global的上下文
	 * @return
	 */
	public FlowNodeContext getSelfFlowNodeContext() {
		return getSelfFlowNode().getFlowNodeContext();
	}
	
	public java.lang.String toString() {
		return super.toString() + "" + this.getNextNodesMap().toString();
	}
}