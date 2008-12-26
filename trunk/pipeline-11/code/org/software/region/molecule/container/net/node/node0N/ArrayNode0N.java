package org.software.region.molecule.container.net.node.node0N;

import org.software.region.molecule.container.net.node.Node;

public class ArrayNode0N extends Node0N {

	protected Node[] nodes;

	public Node getNextAt(int index) {
		return nodes[index];
	}

	public void setNextNodeAt(int index, Node node) {
		nodes[index] = node;
	}
		
}
