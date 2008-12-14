package org.software.sphere.society.platform.pipeline.core.core;

import org.software.sphere.society.platform.pipeline.core.data.DataNode;
import org.software.sphere.society.platform.pipeline.core.data.node0X.DefaultNode0X;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;

public class RR  extends DefaultNode0X {
	
	/** 
	 * ������ݽڵ�<br>
	 * ֱ�ӷŵ���������ȥ��
	 * 
	 * @param node
	 */
	public void addDataNode(DataNode node) {
		this.addNextNode(node);
	}
	/**
	 * ��ȡ���ݽڵ�<br>
	 * ֱ�Ӵ��������л�ȡ
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
			throw new RuntimeException("�������������:" + node + ", �������ݱ��������ݲ�����string���͵�");
		}
		return data;
	}

}
