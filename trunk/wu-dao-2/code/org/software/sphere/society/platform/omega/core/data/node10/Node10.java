package org.software.sphere.society.platform.omega.core.data.node10;

import org.software.sphere.society.platform.omega.core.data.Node;
import org.software.sphere.society.platform.omega.core.data.node0X.String;
import org.software.sphere.society.platform.omega.core.data.pre.Pre1able;
import org.software.sphere.society.platform.omega.exception.data.MiddleNodeNotFountException;

public class Node10 extends Node implements Pre1able {

	protected Node preNode;
	
	public Node getNextNodeByName(String nodeName) {
		return null;
	}

	public Node getNextNodeByPath(String pathName) throws MiddleNodeNotFountException {
		return null;
	}

	public Node getNextNodeByPath(String[] pathNamesArray) throws MiddleNodeNotFountException {
		return null;
	}

	public Node getPreNodeByName(String nodeName) {

		if (preNode != null && preNode.getName().equals(nodeName)) {
			return this.preNode;
		}
		return null;
	}

	public Node getPreNodeByPath(String pathName) throws MiddleNodeNotFountException {
		// TODO Auto-generated method stub
		return null;
	}

	public Node getPreNodeByPath(String[] pathNamesArray) throws MiddleNodeNotFountException {
		// TODO Auto-generated method stub
		return null;
	}

	public Node getFirstNodeInSequencePre1ableNodes() {
		Pre1able pre1ableNode = this;
		while (pre1ableNode != null && Pre1able.class.isInstance(pre1ableNode.getPreNode())) {
			pre1ableNode = (Pre1able) pre1ableNode.getPreNode();
		}
		return (Node) pre1ableNode;
	}

	public Node getPreNode() {
		return this.preNode;
	}

	public void setPreNode(Node preNode) {
		this.preNode = preNode;
	}
}
