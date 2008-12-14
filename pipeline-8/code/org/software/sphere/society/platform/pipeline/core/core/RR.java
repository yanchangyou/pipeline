package org.software.sphere.society.platform.pipeline.core.core;

import org.software.sphere.society.platform.pipeline.core.data.DataNode;
import org.software.sphere.society.platform.pipeline.core.data.node0X.DefaultNode0X;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;

public class RR  extends DefaultNode0X {
	
	/** 
	 * 添加数据节点<br>
	 * 直接放到上下文中去了
	 * 
	 * @param node
	 */
	public void addDataNode(DataNode node) {
		this.addNextNode(node);
	}
	/**
	 * 获取数据节点<br>
	 * 直接从上下文中获取
	 * 
	 * @param name
	 * @return
	 */
	public DataNode getDataNode(String name) {
		return this.getNextNodeByName(name);
	}

	
	public java.lang.String getData() {
		java.lang.String data = null;
		DataNode node = this.getNextNodeByName(KeyWords.DATA_KEY_WORLD);
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
