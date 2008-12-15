package org.software.sphere.society.platform.pipeline.core.data.node01;

import org.software.sphere.society.platform.pipeline.core.data.DataNode;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.exception.core.data.NextNodeNotFountException;
import org.software.sphere.society.platform.pipeline.exception.core.data.PreNodeNotFountException;

public class DefaultNode01 extends Node01 {

	protected DataNode next;

	public DataNode getNext() {
		return next;
	}

	public void setNext(DataNode next) {
		this.next = next;
	}

	public void generateByString(java.lang.String data) {
		
	}
	
	public java.lang.String toString() {
		return next.toString();
	}

	public DataNode getNextNodeByName(String nodeName) {
		if (this.next.getName().equals(nodeName)) {
			return this.next;
		}
		return null;
	}

	public DataNode getNextNodeByPath(String pathName) throws NextNodeNotFountException {
		java.lang.String[] javaPathNamesArray = pathName.toJavaString().split("\\.");
		String[] pathNamesArray = new String[javaPathNamesArray.length];
		for (int i = 0; i < pathNamesArray.length; i++) {
			pathNamesArray[i] = new String(javaPathNamesArray[i]);
		}
		return this.getNextNodeByPath(pathNamesArray);
	}

	public DataNode getNextNodeByPath(String[] pathNamesArray) throws NextNodeNotFountException {
		
		if (pathNamesArray != null && pathNamesArray.length > 0 && pathNamesArray[0].equals(this.next.getNodeName())) {
			String[] newPathNamesArray = new String[pathNamesArray.length-1];
			System.arraycopy(pathNamesArray, 1, newPathNamesArray, 0, newPathNamesArray.length);
			return this.next.getNextNodeByPath(newPathNamesArray);
		}
		return null;
	}

	public DataNode getPreNodeByName(String nodeName) {
		// TODO Auto-generated method stub
		return null;
	}

	public DataNode getPreNodeByPath(String pathName) throws PreNodeNotFountException {
		// TODO Auto-generated method stub
		return null;
	}

	public DataNode getPreNodeByPath(String[] pathNamesArray) throws PreNodeNotFountException {
		// TODO Auto-generated method stub
		return null;
	}
}
