package org.software.sphere.society.platform.omega.core.data.node00;

import org.software.sphere.society.platform.omega.common.JavaStringable;
import org.software.sphere.society.platform.omega.core.data.Node;
import org.software.sphere.society.platform.omega.core.data.node0X.String;
import org.software.sphere.society.platform.omega.exception.data.MiddleNodeNotFountException;

public abstract class Node00 extends Node implements JavaStringable {

	public Node getNextNodeByName(String nodeName) {
		return null;
	}

	public Node getNextNodeByPath(String pathName) {
		return null;
	}

	public Node getNextNodeByPath(String[] pathNamesArray) {
		return null;
	}
	public Node getPreNodeByName(String nodeName) {
		return null;
	}

	public Node getPreNodeByPath(String pathName) throws MiddleNodeNotFountException {
		return null;
	}

	public Node getPreNodeByPath(String[] pathNamesArray) throws MiddleNodeNotFountException {
		return null;
	}
}
