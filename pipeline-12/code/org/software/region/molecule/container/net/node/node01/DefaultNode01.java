package org.software.region.molecule.container.net.node.node01;

import org.software.region.molecule.container.net.nodable.Nodable;
import org.software.region.molecule.container.net.node.Node;

public class DefaultNode01 extends Node01 {

	protected Node next;

	public Node getNextNode() {
		return next;
	}

	public void setNextNode(Node next) {
		this.next = next;
	}

	public void addNextNodeInList(Nodable node) {
		// TODO Auto-generated method stub
		
	}

	public void addNextNodeInMap(String name, Nodable node) {
		// TODO Auto-generated method stub
		
	}

	
}
