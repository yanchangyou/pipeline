package org.software.sphere.society.platform.omega.core.data.node1X;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.software.sphere.society.platform.omega.common.NodeDealer;
import org.software.sphere.society.platform.omega.core.data.Node;
import org.software.sphere.society.platform.omega.core.data.node0X.String;

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

	public Node getNextNodeByPath(String[] pathNamesArray) {
		Node node = this;
		for (int i = 0; i < pathNamesArray.length; i++) {
			node = node.getNextNode(pathNamesArray[i]);
		}
		return node;
	}

	public Node getNextNodeByPath(String pathNames) {
		java.lang.String path_javaString = pathNames.toJavaString();
		java.lang.String[] pathArray = path_javaString.split("\\.");
		String[] pathNameArray = new String[pathArray.length];
		for (int i = 0; i < pathNameArray.length; i++) {
			pathNameArray[i] = new String(pathArray[i]);
		}
		return this.getNextNodeByPath(pathNameArray);
	}
	
	
	public Node getNextNode(String nextNodeName) {
		return (Node) nextNodesMap.get(nextNodeName);
	}

	public void dealNextNode(NodeDealer nodeDealer) {
		Set set = this.nextNodesMap.keySet();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			Node node = (Node) iter.next();
			nodeDealer.deal(node);
		}
	}
//
//	public String toString() {
//		return "preNodeName : " + this.preNode.getName() + ", children : " + this.getChildren();
//	}

	public Map getNextNodesMap() {
		return nextNodesMap;
	}
}
