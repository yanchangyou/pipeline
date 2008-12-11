package org.software.sphere.society.platform.omega.core.data.node1X;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.software.sphere.society.platform.omega.common.Commons;
import org.software.sphere.society.platform.omega.common.NodeDealer;
import org.software.sphere.society.platform.omega.core.data.Node;
import org.software.sphere.society.platform.omega.core.data.node0X.String;
import org.software.sphere.society.platform.omega.exception.data.MiddleNodeNotFountException;

public class DefaultNode1X extends Node1X {

	protected Node preNode;
	
	private Map nextNodesMap;
	
	public DefaultNode1X() {
		nextNodesMap = new HashMap();
	}
	
	public void addNextNode(Node node) {
		this.addNextNode(node.getNodeName(), node);
	}
	
	public void addNextNode(String name, Node node) {
		nextNodesMap.put(name, node);
	}

	public Node getNextNodeByPath(String[] pathNamesArray) throws MiddleNodeNotFountException {
		Node node = this;
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < pathNamesArray.length; i++) {
			node = node.getNextNodeByName(pathNamesArray[i]);
			buf.append(".").append(pathNamesArray[i]);
			if (node == null) {
				throw new MiddleNodeNotFountException("错误:中间节点为空, 此节点是:" + pathNamesArray[i] + ", 不存在的路径是:" + buf);
			}
		}
		return node;
	}

	public Node getNextNodeByPath(String pathName)  throws MiddleNodeNotFountException {
		String[] pathNameArray = Commons.getPathNameArray(pathName);
		return this.getNextNodeByPath(pathNameArray);
	}
	
	
	public Node getNextNodeByName(String nextNodeName) {
		return (Node) nextNodesMap.get(nextNodeName);
	}

	public void dealNextNode(NodeDealer nodeDealer) {
		Set set = this.nextNodesMap.keySet();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			Node node = (Node) iter.next();
			nodeDealer.deal(node);
		}
	}

	public Map getNextNodesMap() {
		return nextNodesMap;
	}

	public Node getPreNode() {
		return preNode;
	}

	public void setPreNode(Node preNode) {
		this.preNode = preNode;
	}

	public Node getPreNodeByName(String nodeName) {
		if (this.preNode.getNodeName().equals(nodeName)) {
			return this.preNode;
		}
		return null;
	}

	public Node getPreNodeByPath(String pathName) throws MiddleNodeNotFountException {
		String[] pathNameArray = Commons.getPathNameArray(pathName);
		return getPreNodeByPath(pathNameArray);
	}

	public Node getPreNodeByPath(String[] pathNamesArray) throws MiddleNodeNotFountException {

		if (pathNamesArray != null && pathNamesArray.length == 1 && pathNamesArray[0].equals(this.preNode.getNodeName())) {
			return this.preNode;
		}
		return null;
	}
	
	public int getNode1XPreLevel() {
		DefaultNode1X node1X = this;
		int level = 0;
		while (node1X.preNode != null) {
			level ++;
			node1X = (DefaultNode1X) node1X.preNode;
		}
		return level;
	}
//
//	public String toString() {
//		return "preNodeName : " + this.preNode.getName() + ", children : " + this.getChildren();
//	}
}
