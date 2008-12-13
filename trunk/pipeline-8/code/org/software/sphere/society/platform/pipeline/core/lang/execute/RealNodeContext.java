package org.software.sphere.society.platform.pipeline.core.lang.execute;

import org.software.sphere.society.platform.pipeline.common.Commons;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.core.data.node1X.DefaultNode1X;
import org.software.sphere.society.platform.pipeline.core.real.RealNode;

/**
 * 上下文<br>
 * 目标 1, 元素遍历向前向后遍历<br>
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
	public RealNode getSuitablePathRealNode(String path) {
		RealNode realNode = null;
		
		if (path.toJavaString().startsWith(KeyWords.THIS_KEY_WORLD + ".")) {
			realNode = getRelativePathRealNode(new String(path.toJavaString().substring(KeyWords.THIS_KEY_WORLD.toJavaString().length() + 1)));
		} else if (path.toJavaString().startsWith(KeyWords.ROOT_KEY_WORLD + ".")) {
			realNode = getAbsolutePathRealNode(new String(path.toJavaString().substring(KeyWords.ROOT_KEY_WORLD.toJavaString().length() + 1)));
		} else {
			realNode = getRelativePathRealNode(path);
		}
		return realNode;
	}
	
	/**
	 * 获取适合节点的上下文
	 * @param path
	 * @return
	 */
	public RealNodeContext getSuitablePathRealNodeContext(String path) {
		return getSuitablePathRealNode(path).getRealNodeContext();
	}

	/**
	 * 获取相对路径节点
	 * 
	 * @param path
	 * @return
	 */
	public RealNode getRelativePathRealNode(String path) {
		RealNode realNode = this.getThisRealNode();

		String[] pathArray = Commons.convertToStringArray(path.toJavaString().split("\\."));

		for (int i = 0; i < pathArray.length; i++) {
			if (pathArray[i].equals(KeyWords.SUPER_KEY_WORLD)) {
				realNode = realNode.getPreRealNode();
			} else {
				realNode = realNode.getNextRealNode(pathArray[i]);
			}
		}
		return realNode;
	}
	
	/**
	 * 获取相对路径节点的上下文
	 * 
	 * @param path
	 * @return
	 */
	public RealNodeContext getRelativePathRealNodeContext(String path) {
		return getRelativePathRealNode(path).getRealNodeContext();
	}
	/**
	 * 获取绝对路径的节点
	 * 
	 * @param path
	 * @return
	 */
	public RealNode getAbsolutePathRealNode(String path) {
		RealNode realNode = this.getGlobalNode();
		String[] pathArray = Commons.convertToStringArray(path.toJavaString().split("\\."));
		for (int i = 0; i < pathArray.length; i++) {
			realNode = realNode.getNextRealNode(pathArray[i]);
		}
		return realNode;
	}
	
	/**
	 * 获取绝对路径节点的上下文
	 * 
	 * @param path
	 * @return
	 */
	public RealNodeContext getAbsolutePathRealNodeContext(String path) {
		return getAbsolutePathRealNode(path).getRealNodeContext();
	}
		
	/**
	 * 获取此上下文的节点
	 * 
	 * @return
	 */
	public RealNode getThisRealNode() {
		return (RealNode) this.preNode;
	}

	/**
	 * 获取Global节点<br>
	 * 
	 * 不断的向前遍历
	 * 
	 * @return
	 */
	public RealNode getGlobalNode() {
		RealNode realNode = this.getThisRealNode();
		while (realNode.getPreNode() != null) {
			realNode = realNode.getPreRealNode();
		}
		return ((Root)(Object)realNode).getGlobal();
	}
	
	/**
	 * Global的上下文
	 * @return
	 */
	public RealNodeContext getGlobalNodeContext() {
		return getGlobalNode().getRealNodeContext();
	}
	
	public java.lang.String toString() {
		return this.getNextNodesMap().toString();
	}
}
