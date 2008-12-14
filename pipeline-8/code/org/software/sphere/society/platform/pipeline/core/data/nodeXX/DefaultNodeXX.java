package org.software.sphere.society.platform.pipeline.core.data.nodeXX;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.software.sphere.society.platform.pipeline.common.NodeDealer;
import org.software.sphere.society.platform.pipeline.core.data.DataNode;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;

public abstract class DefaultNodeXX extends NodeXX {

	protected Map preNodesMap;

	protected Map nextNodesMap;

	public DefaultNodeXX() {
		nextNodesMap = new HashMap();
	}

	public void addNextNode(DataNode node) {
		this.addNextNode(node.getNodeName(), node);
	}

	public void addNextNode(String name, DataNode node) {
		nextNodesMap.put(name, node);
	}

	public DataNode getNextNodeByPath(String[] pathNamesArray) {
		DataNode node = this;
		for (int i = 0; i < pathNamesArray.length; i++) {
			node = node.getNextNodeByName(pathNamesArray[i]);
		}
		return node;
	}

	public DataNode getNextNodeByPath(String pathNames) {
		java.lang.String path_javaString = pathNames.toJavaString();
		java.lang.String[] pathArray = path_javaString.split("\\.");
		String[] pathNameArray = new String[pathArray.length];
		for (int i = 0; i < pathNameArray.length; i++) {
			pathNameArray[i] = new String(pathArray[i]);
		}
		return this.getNextNodeByPath(pathNameArray);
	}

	public DataNode getNextNodeByName(String nextNodeName) {
		return (DataNode) nextNodesMap.get(nextNodeName);
	}
	public void addPreNode(DataNode node) {
		this.addPreNode(node.getNodeName(), node);
	}

	public void addPreNode(String name, DataNode node) {
		preNodesMap.put(name, node);
	}

	public DataNode getPreNodeByPath(String[] pathNamesArray) {
		DataNode node = this;
		for (int i = 0; i < pathNamesArray.length; i++) {
			node = node.getPreNodeByName(pathNamesArray[i]);
		}
		return node;
	}

	public DataNode getPreNodeByPath(String pathNames) {
		java.lang.String path_javaString = pathNames.toJavaString();
		java.lang.String[] pathArray = path_javaString.split("\\.");
		String[] pathNameArray = new String[pathArray.length];
		for (int i = 0; i < pathNameArray.length; i++) {
			pathNameArray[i] = new String(pathArray[i]);
		}
		return this.getPreNodeByPath(pathNameArray);
	}

	public DataNode getPreNode(String preNodeName) {
		return (DataNode) preNodesMap.get(preNodeName);
	}
	
	public void dealNextNode(NodeDealer nodeDealer) throws Exception {
		Set set = this.nextNodesMap.keySet();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			DataNode node = (DataNode) iter.next();
			nodeDealer.deal(node);
		}
	}
	
	//
	// public String toString() {
	// return "preNodeName : " + this.preNode.getName() + ", children : " +
	// this.getChildren();
	// }
	
}
