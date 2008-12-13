package org.software.sphere.society.platform.omega.core.lang.execute;

import org.software.sphere.society.platform.omega.core.data.Node;
import org.software.sphere.society.platform.omega.core.data.node0X.DefaultNode0X;
import org.software.sphere.society.platform.omega.core.data.node0X.String;

public class RR  extends DefaultNode0X {
	
	/** 
	 * 添加变量<br>
	 * 直接放到上下文中去了
	 * 
	 * @param node
	 */
	public void addVar(Node node) {
		this.addNextNode(node);
	}
	/**
	 * 获取变量<br>
	 * 直接从上下文中获取
	 * 
	 * @param name
	 * @return
	 */
	public Node getVar(String name) {
		return this.getNextNodeByName(name);
	}

	
	public java.lang.String getData() {
		java.lang.String data = null;
		Node node = this.getNextNodeByName(KeyWords.DATA_KEY_WORLD);
		if (node != null && String.class.isInstance(node)) {
			data = ((String)node).toJavaString();
		} else if (node == null) {
			data = "";
		} else {
			throw new RuntimeException("错误的请求数据:" + node + ", 请求数据必须有数据并且是string类型的");
		}
		return data;
	}

}
