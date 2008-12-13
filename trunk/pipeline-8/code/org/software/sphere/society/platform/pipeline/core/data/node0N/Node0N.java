package org.software.sphere.society.platform.pipeline.core.data.node0N;

import org.software.sphere.society.platform.pipeline.core.data.Node;

public abstract class Node0N extends Node {

	protected Node[] NextNodes;
	
	public Node getChildAt(int index) {
		return NextNodes[index];
	};
	
	public void setChildAt(int index, Node node) {
		NextNodes[index] = node;
	}
	
	
}
