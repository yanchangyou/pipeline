package org.software.sphere.society.platform.pipeline.core.data.nodeXX;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.software.sphere.society.platform.pipeline.common.NodeDealer;
import org.software.sphere.society.platform.pipeline.core.data.Node;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;

public abstract class DefaultNodeXX extends NodeXX {

	protected Map preNodesMap;

	protected Map nextNodesMap;

	public DefaultNodeXX() {
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
			node = node.getNextNodeByName(pathNamesArray[i]);
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

	public Node getNextNodeByName(String nextNodeName) {
		return (Node) nextNodesMap.get(nextNodeName);
	}
	public void addPreNode(Node node) {
		this.addPreNode(node.getNodeName(), node);
	}

	public void addPreNode(String name, Node node) {
		preNodesMap.put(name, node);
	}

	public Node getPreNodeByPath(String[] pathNamesArray) {
		Node node = this;
		for (int i = 0; i < pathNamesArray.length; i++) {
			node = node.getPreNodeByName(pathNamesArray[i]);
		}
		return node;
	}

	public Node getPreNodeByPath(String pathNames) {
		java.lang.String path_javaString = pathNames.toJavaString();
		java.lang.String[] pathArray = path_javaString.split("\\.");
		String[] pathNameArray = new String[pathArray.length];
		for (int i = 0; i < pathNameArray.length; i++) {
			pathNameArray[i] = new String(pathArray[i]);
		}
		return this.getPreNodeByPath(pathNameArray);
	}

	public Node getPreNode(String preNodeName) {
		return (Node) preNodesMap.get(preNodeName);
	}
	
	public void dealNextNode(NodeDealer nodeDealer) throws Exception {
		Set set = this.nextNodesMap.keySet();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			Node node = (Node) iter.next();
			nodeDealer.deal(node);
		}
	}
	
	//
	// public String toString() {
	// return "preNodeName : " + this.preNode.getName() + ", children : " +
	// this.getChildren();
	// }
	
}
