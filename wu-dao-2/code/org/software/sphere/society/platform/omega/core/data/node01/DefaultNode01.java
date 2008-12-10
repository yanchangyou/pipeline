package org.software.sphere.society.platform.omega.core.data.node01;

import org.software.sphere.society.platform.omega.core.data.Node;
import org.software.sphere.society.platform.omega.core.data.node0X.String;

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

	public Node getNextNode(String nodeName) {
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
}
