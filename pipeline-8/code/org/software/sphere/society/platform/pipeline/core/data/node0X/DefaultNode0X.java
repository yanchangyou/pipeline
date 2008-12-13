package org.software.sphere.society.platform.pipeline.core.data.node0X;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.software.sphere.society.platform.pipeline.common.Commons;
import org.software.sphere.society.platform.pipeline.common.NodeDealer;
import org.software.sphere.society.platform.pipeline.core.data.Node;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.exception.data.MiddleNodeNotFountException;

public class DefaultNode0X extends Node0X {

	private Map nextNodesMap;
	
	public DefaultNode0X() {
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

	public void dealNextNode(NodeDealer nodeDealer) throws Exception {
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
		return null;
	}

	public void setPreNode(Node preNode) {
	}

	public Node getPreNodeByName(String nodeName) {
		return null;
	}

	public Node getPreNodeByPath(String pathName) throws MiddleNodeNotFountException {
		String[] pathNameArray = Commons.getPathNameArray(pathName);
		return getPreNodeByPath(pathNameArray);
	}

	public Node getPreNodeByPath(String[] pathNamesArray) throws MiddleNodeNotFountException {
		return null;
	}
	
	public int getNode1XPreLevel() {
		return -1;
	}
	
	
	public java.lang.String toString() {
		return super.toString() + "next : " + this.getNextNodesMap();
	}
}
