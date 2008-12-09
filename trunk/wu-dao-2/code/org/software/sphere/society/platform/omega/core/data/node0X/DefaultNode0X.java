package org.software.sphere.society.platform.omega.core.data.node0X;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.software.sphere.society.platform.omega.core.common.NodeDealer;
import org.software.sphere.society.platform.omega.core.data.Node;

public class DefaultNode0X extends Node0X {

	private Map node0XMap = new HashMap();

	public void addChildNode(Node node) {
		node0XMap.put(node.getName(), node);
	}

	public Node getChildNode(java.lang.String name) {
		return (Node) node0XMap.get(name);
	}

	public boolean containNode(java.lang.String name) {
		return node0XMap.containsKey(name);
	}

	public boolean containNode(Node node) {
		return node0XMap.containsValue(node);
	}

	public java.lang.String toString() {
		return this.node0XMap.toString();
	}

	public void generateByString(String data) {
		// TODO Auto-generated method stub

	}
	
	public void dealChildNode(NodeDealer nodeDealer) {
		Set set = this.node0XMap.keySet();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			Node node = (Node) iter.next();
			nodeDealer.deal(node);
		}
	}

	public void generateByString(java.lang.String data) {
		// TODO Auto-generated method stub
		
	}

	public Map getNode0XMap() {
		return node0XMap;
	}
	
}