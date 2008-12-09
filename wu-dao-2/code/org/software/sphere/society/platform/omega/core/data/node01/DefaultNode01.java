package org.software.sphere.society.platform.omega.core.data.node01;

import org.software.sphere.society.platform.omega.core.data.Node;

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
	
}
