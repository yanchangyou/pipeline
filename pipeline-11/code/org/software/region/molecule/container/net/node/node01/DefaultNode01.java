package org.software.region.molecule.container.net.node.node01;

import org.software.region.molecule.container.net.node.Node;

public class DefaultNode01 extends Node01 {

	protected Node next;

	public Node getNextNode() {
		return next;
	}

	public void setNextNode(Node next) {
		this.next = next;
	}
}
