package org.software.sphere.society.platform.pipeline.core.data.node01;

import org.software.sphere.society.platform.pipeline.core.data.Node;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.exception.data.MiddleNodeNotFountException;

public class DefaultNode01 extends Node01 {

	protected Node next;

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public void generateByString(java.lang.String data) {
		
	}
	
	public java.lang.String toString() {
		return next.toString();
	}

	public Node getNextNodeByName(String nodeName) {
		if (this.next.getName().equals(nodeName)) {
			return this.next;
		}
		return null;
	}

	public Node getNextNodeByPath(String pathName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Node getNextNodeByPath(String[] pathNamesArray) {
		
		if (pathNamesArray != null && pathNamesArray.length == 1 && pathNamesArray[0].equals(pathNamesArray)) {
			return this.next;
		}
		return null;
	}

	public Node getPreNodeByName(String nodeName) {
		// TODO Auto-generated method stub
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
}
