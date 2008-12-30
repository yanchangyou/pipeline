package org.software.region.molecule.container.net.node.node01;

import org.software.region.molecule.container.net.nodable.Nodable;
import org.software.region.molecule.container.net.nodable.Node01able;
import org.software.region.molecule.container.net.node.Node;

public abstract class Node01 extends Node implements Node01able {

	public abstract Node getNextNode();

	public abstract void setNextNode(Node next);
	
	public void addNextNode(Nodable node) {
		// TODO Auto-generated method stub
		
	}
}
