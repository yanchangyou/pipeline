package org.software.region.molecule.container.net.node.node0N;

import org.software.region.molecule.container.net.node.Node;

public abstract class Node0N extends Node {

	public abstract Node getNextAt(int index);
	
	public abstract void setNextNodeAt(int index, Node node);
	
}
