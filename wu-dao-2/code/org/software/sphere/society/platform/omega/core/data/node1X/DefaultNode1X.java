package org.software.sphere.society.platform.omega.core.data.node1X;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.software.sphere.society.platform.omega.core.common.NodeDealer;
import org.software.sphere.society.platform.omega.core.data.Node;

public class DefaultNode1X extends Node1X {

	protected Node preNode;
	
	private Map childNodesMap;
	
	public DefaultNode1X() {
		childNodesMap = new HashMap();
	}
	
	public void addChildNode(Node node) {
		this.addChildNode(node.getName(), node);
	}
	
	public void addChildNode(java.lang.String name, Node node) {
		childNodesMap.put(name, node);
	}
	
	public Node getChildNode(java.lang.String childNodeName) {
		return (Node) childNodesMap.get(childNodeName);
	}

	public void generateByString(String data) {
		// TODO Auto-generated method stub
		
	}
	
	public void dealChildNode(NodeDealer nodeDealer) {
		Set set = this.childNodesMap.keySet();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			Node node = (Node) iter.next();
			nodeDealer.deal(node);
		}
	}
//
//	public java.lang.String toString() {
//		return "preNodeName : " + this.preNode.getName() + ", children : " + this.getChildren();
//	}

	public Map getChildNodesMap() {
		return childNodesMap;
	}
}
