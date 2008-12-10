package org.software.sphere.society.platform.omega.core.data.node0X;

import java.util.HashMap;
import java.util.Map;

import org.software.sphere.society.platform.omega.common.JavaStringable;
import org.software.sphere.society.platform.omega.common.NodeDealer;
import org.software.sphere.society.platform.omega.core.data.Node;
import org.software.sphere.society.platform.omega.core.data.node00.Char2;
import org.software.sphere.society.platform.omega.exception.data.DataGenerateException;

public class String extends Node0X implements JavaStringable {

	private java.lang.String string;

	public String(java.lang.String string) {
		this.string = string;
	}
	
	public void generateByString(java.lang.String string) {
		this.string = string;
	}

	public java.lang.String toJavaString() {
		return string;
	}

	public void dealNextNode(NodeDealer nodeDealer) {
		for (int i = 0; i < string.length(); i++) {
			Char2 char2 = new Char2();
			try {
				char2.fromJavaString(string.charAt(i)+"");
			} catch (DataGenerateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			nodeDealer.deal(char2);
		}
	}

	public void fromJavaString(java.lang.String data) throws DataGenerateException {
		this.string = data;
	}
	
	public java.lang.String toString() {
		return this.toJavaString();
	}

	public Node getNextNode(String nodeName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Node getNextNodeByPath(String pathName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Node getNextNodeByPath(String[] pathNamesArray) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean equals(Object node) {
		if (node != null && (node instanceof String)) {
			String string = (String)node;
			if (string.toJavaString().equals(this.string)) {
				return true;
			}
		}
		return false;
	}
	
	public int hashCode() {
		return this.string.hashCode();
	}
	
	public static void main(java.lang.String[] args) {
		
		Map map = new HashMap();
		
		String name = new String("name");
		Object obj = new Object();
		map.put(name, obj);
		
		
		String name2 = new String("name");
		
		System.out.println(map.get(name2));
	}
}
