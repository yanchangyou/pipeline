package org.software.sphere.society.platform.pipeline.core.data.node1X;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.software.sphere.society.platform.pipeline.common.Commons;
import org.software.sphere.society.platform.pipeline.common.NodeDealer;
import org.software.sphere.society.platform.pipeline.core.data.DataNode;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.core.data.pre.Pre1able;
import org.software.sphere.society.platform.pipeline.exception.core.data.NextNodeNotFountException;
import org.software.sphere.society.platform.pipeline.exception.core.data.PreNodeNotFountException;

public class DefaultNode1X extends Node1X {

	protected DataNode preNode;
	
	private Map nextNodesMap;
	
	public DefaultNode1X(DataNode node) {
		this();		
		this.preNode = node;
	}
	
	public DefaultNode1X() {
		nextNodesMap = new HashMap();
	}
	
	public void addNextNode(DataNode node) {
		this.addNextNode(node.getNodeName(), node);
	}
	
	public void addNextNode(String name, DataNode node) {
		nextNodesMap.put(name, node);
	}

	public DataNode getNextNodeByPath(String[] pathNamesArray) throws NextNodeNotFountException {
		DataNode node = this;
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < pathNamesArray.length; i++) {
			node = node.getNextNodeByName(pathNamesArray[i]);
			buf.append(".").append(pathNamesArray[i]);
			if (node == null && i != pathNamesArray.length - 1) {
				throw new NextNodeNotFountException("错误:路径节点为空, 此节点是:" + pathNamesArray[i] + ", 不存在的路径是:" + buf);
			}
		}
		return node;
	}

	public DataNode getNextNodeByPath(String pathName) throws NextNodeNotFountException {
		String[] pathNameArray = Commons.getPathNameArray(pathName);
		return this.getNextNodeByPath(pathNameArray);
	}
	
	
	public DataNode getNextNodeByName(String nextNodeName) {
		return (DataNode) nextNodesMap.get(nextNodeName);
	}

	public void dealNextNode(NodeDealer nodeDealer) throws Exception {
		Set set = this.nextNodesMap.keySet();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			DataNode node = (DataNode) iter.next();
			nodeDealer.deal(node);
		}
	}

	public Map getNextNodesMap() {
		return nextNodesMap;
	}

	public DataNode getPreNode() {
		return preNode;
	}

	public void setPreNode(DataNode preNode) {
		this.preNode = preNode;
	}

	public DataNode getPreNodeByName(String nodeName) {
		if (this.preNode.getNodeName().equals(nodeName)) {
			return this.preNode;
		}
		return null;
	}

	public DataNode getPreNodeByPath(String pathName) throws PreNodeNotFountException {
		String[] pathNameArray = Commons.getPathNameArray(pathName);
		return getPreNodeByPath(pathNameArray);
	}

	public DataNode getPreNodeByPath(String[] pathNamesArray) throws PreNodeNotFountException {

		if (pathNamesArray != null && pathNamesArray.length == 1 && pathNamesArray[0].equals(this.preNode.getNodeName())) {
			return this.preNode;
		}
		return null;
	}
	
	public int getNode1XPreLevel() {
		int level = 0;
		Pre1able pre1ableNode = this;
		while (pre1ableNode != null && Pre1able.class.isInstance(pre1ableNode.getPreNode())) {
			level ++;
			pre1ableNode = (Pre1able) pre1ableNode.getPreNode();
		}
		return level;
	}
//
//	public String toString() {
//		return "preNodeName : " + this.preNode.getName() + ", children : " + this.getChildren();
//	}

	public DataNode getFirstNodeInSequencePre1ableNodes() {
		Pre1able pre1ableNode = this;
		DataNode firstNode = null;
		while (pre1ableNode.getPreNode() != null && Pre1able.class.isInstance(pre1ableNode.getPreNode())) {
//			firstNode = (DataNode) pre1ableNode;
			pre1ableNode = (Pre1able) pre1ableNode.getPreNode();
		}
		return (DataNode) firstNode;
	}

}
