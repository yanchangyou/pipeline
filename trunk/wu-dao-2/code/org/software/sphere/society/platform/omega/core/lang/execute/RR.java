package org.software.sphere.society.platform.omega.core.lang.execute;

import org.software.sphere.society.platform.omega.core.data.Node;
import org.software.sphere.society.platform.omega.core.data.node0X.DefaultNode0X;
import org.software.sphere.society.platform.omega.core.data.node0X.String;

public class RR  extends DefaultNode0X {
	
	/** 
	 * ��ӱ���<br>
	 * ֱ�ӷŵ���������ȥ��
	 * 
	 * @param node
	 */
	public void addVar(Node node) {
		this.addNextNode(node);
	}
	/**
	 * ��ȡ����<br>
	 * ֱ�Ӵ��������л�ȡ
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
			throw new RuntimeException("�������������:" + node + ", �������ݱ��������ݲ�����string���͵�");
		}
		return data;
	}

}
