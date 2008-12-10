package org.software.sphere.society.platform.omega.common;

import org.software.sphere.society.platform.omega.core.data.Node;
import org.software.sphere.society.platform.omega.core.data.node0X.DefaultNode0X;

/**
 * 此类用于定义变量, 程序会把这些变量存储到上下文中
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-5 19:21:50
 * @file : Meta.java
 * @version : 0.1
 */

public class VarDefine extends DefaultNode0X {
	
	public void addVar(Node node) {
		this.addNextNode(node);
	}

	public Node getVar(java.lang.String name) {
		return this.getNextNode(name);
	}
}
