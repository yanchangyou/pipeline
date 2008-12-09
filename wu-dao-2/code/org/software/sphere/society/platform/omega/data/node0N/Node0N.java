package org.software.sphere.society.platform.omega.data.node0N;

import org.software.sphere.society.platform.omega.data.Node;

public abstract class Node0N extends Node {

	protected Node[] childNodes;
	
	public Node getChildAt(int index) {
		return childNodes[index];
	};
	
	public void setChildAt(int index, Node node) {
		childNodes[index] = node;
	}
	
	
}
