package org.software.sphere.society.platform.pipeline.core.data.node00;

import org.software.sphere.society.platform.pipeline.common.JavaStringable;
import org.software.sphere.society.platform.pipeline.core.data.DataNode;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.exception.core.data.PreNodeNotFountException;

public abstract class Node00 extends DataNode implements JavaStringable {

	public DataNode getNextNodeByName(String nodeName) {
		return null;
	}

	public DataNode getNextNodeByPath(String pathName) {
		return null;
	}

	public DataNode getNextNodeByPath(String[] pathNamesArray) {
		return null;
	}
	public DataNode getPreNodeByName(String nodeName) {
		return null;
	}

	public DataNode getPreNodeByPath(String pathName) throws PreNodeNotFountException {
		return null;
	}

	public DataNode getPreNodeByPath(String[] pathNamesArray) throws PreNodeNotFountException {
		return null;
	}

	public void addNextNode(DataNode node) {
		// TODO Auto-generated method stub
		
	}

	public void addNextNode(org.software.sphere.society.platform.pipeline.core.data.node0X.String name, DataNode node) {
		// TODO Auto-generated method stub
		
	}
}
