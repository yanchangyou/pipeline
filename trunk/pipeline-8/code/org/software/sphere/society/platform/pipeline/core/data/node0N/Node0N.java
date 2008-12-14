package org.software.sphere.society.platform.pipeline.core.data.node0N;

import org.software.sphere.society.platform.pipeline.core.data.DataNode;

public abstract class Node0N extends DataNode {

	protected DataNode[] NextNodes;
	
	public DataNode getChildAt(int index) {
		return NextNodes[index];
	};
	
	public void setChildAt(int index, DataNode node) {
		NextNodes[index] = node;
	}
	
	
}
