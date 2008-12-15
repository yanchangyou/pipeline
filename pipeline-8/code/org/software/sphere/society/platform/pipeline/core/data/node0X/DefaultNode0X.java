package org.software.sphere.society.platform.pipeline.core.data.node0X;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.software.sphere.society.platform.pipeline.common.Commons;
import org.software.sphere.society.platform.pipeline.common.NodeDealer;
import org.software.sphere.society.platform.pipeline.core.data.DataNode;
import org.software.sphere.society.platform.pipeline.exception.core.data.NextNodeNotFountException;
import org.software.sphere.society.platform.pipeline.exception.core.data.PreNodeNotFountException;

public class DefaultNode0X extends Node0X {

	private Map nextNodesMap;
	
	public DefaultNode0X() {
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
			if (node == null) {
				throw new NextNodeNotFountException("错误:中间节点为空, 此节点是:" + pathNamesArray[i] + ", 不存在的路径是:" + buf);
			}
		}
		return node;
	}

	public DataNode getNextNodeByPath(String pathName)  throws NextNodeNotFountException {
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
		return null;
	}

	public void setPreNode(DataNode preNode) {
	}

	public DataNode getPreNodeByName(String nodeName) {
		return null;
	}

	public DataNode getPreNodeByPath(String pathName) throws PreNodeNotFountException {
		String[] pathNameArray = Commons.getPathNameArray(pathName);
		return getPreNodeByPath(pathNameArray);
	}

	public DataNode getPreNodeByPath(String[] pathNamesArray) throws PreNodeNotFountException {
		return null;
	}
	
	public int getNode1XPreLevel() {
		return -1;
	}
	
	
	public java.lang.String toString() {
		return super.toString() + "next : " + this.getNextNodesMap();
	}
}
