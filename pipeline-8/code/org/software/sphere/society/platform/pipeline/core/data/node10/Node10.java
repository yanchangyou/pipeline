package org.software.sphere.society.platform.pipeline.core.data.node10;

import org.software.sphere.society.platform.pipeline.core.data.DataNode;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.core.data.pre.Pre1able;
import org.software.sphere.society.platform.pipeline.exception.core.data.NextNodeNotFountException;
import org.software.sphere.society.platform.pipeline.exception.core.data.PreNodeNotFountException;

public class Node10 extends DataNode implements Pre1able {

	protected DataNode preNode;
	
	public DataNode getNextNodeByName(String nodeName) {
		return null;
	}

	public DataNode getNextNodeByPath(String pathName) throws NextNodeNotFountException {
		return null;
	}

	public DataNode getNextNodeByPath(String[] pathNamesArray) throws NextNodeNotFountException {
		return null;
	}

	public DataNode getPreNodeByName(String nodeName) {

		if (preNode != null && preNode.getName().equals(nodeName)) {
			return this.preNode;
		}
		return null;
	}

	public DataNode getPreNodeByPath(String pathName) throws PreNodeNotFountException {
		// TODO Auto-generated method stub
		return null;
	}

	public DataNode getPreNodeByPath(String[] pathNamesArray) throws PreNodeNotFountException {
		// TODO Auto-generated method stub
		return null;
	}

	public DataNode getFirstNodeInSequencePre1ableNodes() {
		Pre1able pre1ableNode = this;
		while (pre1ableNode != null && Pre1able.class.isInstance(pre1ableNode.getPreNode())) {
			pre1ableNode = (Pre1able) pre1ableNode.getPreNode();
		}
		return (DataNode) pre1ableNode;
	}

	public DataNode getPreNode() {
		return this.preNode;
	}

	public void setPreNode(DataNode preNode) {
		this.preNode = preNode;
	}

	public void addNextNode(DataNode node) {
		// TODO Auto-generated method stub
		
	}

	public void addNextNode(String name, DataNode node) {
		// TODO Auto-generated method stub
		
	}
}
