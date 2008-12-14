package org.software.sphere.society.platform.pipeline.core.real;

import org.apache.commons.lang.StringUtils;
import org.software.sphere.society.platform.pipeline.common.ServiceNode;
import org.software.sphere.society.platform.pipeline.common.ServiceNodeDefine;
import org.software.sphere.society.platform.pipeline.core.core.RealNodeContext;
import org.software.sphere.society.platform.pipeline.core.data.DataNode;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.core.data.node1X.DefaultNode1X;

/**
 * ������ʵ�ڵ�ĸ���<br>
 * 
 * ÿ����ʵ�ڵ����岿�ֹ���<br>
 * 1, ������<br>
 * ���������ڴ洢���ݽڵ�, ���ڳ�����ʱ��ȡ<br>
 * 2, ������<br>
 * ���������ڶ��嵱ǰ�ڵ���ӵ�еķ���,����ÿ�������ж���������ṩ�˷���<be> 
 * 3, ���ݽڵ��б�<br>
 * ���嵱ǰ�ڵ�ĳ�ʼ���ݽڵ�, ���Ҵ�����������, �������ݽڵ㶼ͳһ�洢���������� 
 * 4, ǰ���ڵ�<br>
 * ���ڵ����ڹ������ڵ�, ��������real node ����ǰ���� 
 * 5, �����ڵ�<br>
 * ��������real node ��������
 * 
 * 
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-8 ����07:18:53
 * @file : RealNode.java
 * @version : 0.1
 */
public abstract class RealNode extends DefaultNode1X {

	/**
	 * ������
	 */
	private RealNodeContext realNodeContext;

	/**
	 * ������
	 */
	protected ServiceNodeDefine serviceNodeDefine;

	/**
	 * ����ڵ�, ͬʱ��ʼ��������
	 *
	 */
	public RealNode() {
		this.realNodeContext = new RealNodeContext(this);// �Զ�����������˽ڵ��������
		serviceNodeDefine = new ServiceNodeDefine(); //����洢��
	}

	/**
	 * ��ȡ������
	 * @return
	 */
	public RealNodeContext getRealNodeContext() {
		return realNodeContext;
	}

	/** 
	 * ������ݽڵ�<br>
	 * ֱ�ӷŵ���������ȥ��
	 * 
	 * @param node
	 */
	public void addDataNode(DataNode node) {
		this.realNodeContext.addNextNode(node);
	}
	/**
	 * ��ȡ���ݽڵ�<br>
	 * ֱ�Ӵ��������л�ȡ
	 * 
	 * @param name
	 * @return
	 */
	public DataNode getDataNode(String name) {
		return this.realNodeContext.getNextNodeByName(name);
	}

	public void addServiceNode(ServiceNode service) {
		this.serviceNodeDefine.addService(service);
		service.setPreNode(this);
	}

	public ServiceNode getService(String name) {
		return serviceNodeDefine.getService(name);
	}

	
	/**
	 * ������ȡ��һ�������ڵ�
	 * @param NextNodeName
	 * @return
	 */
	public RealNode getNextRealNode(String NextNodeName) {
		return (RealNode) this.getNextNodeByName(NextNodeName);
	}

	/**
	 * ��Ӻ����ڵ�
	 * 
	 * @param realNode
	 */
	public void addNextRealNode(RealNode realNode) {
		this.addNextNode(realNode);
		realNode.setPreNode(this);
	}

	/**
	 * ��ȡ�ڵ����� ���ڵ㶨��Ϊ0
	 * 
	 * @return �����
	 */
	public int getRealNodeLevel() {
		int realNodeLevel = 0;
		RealNode realNode = this;
		while (realNode.getPreNode() != null) {
			realNodeLevel++;
			realNode = (RealNode) realNode.getPreNode();
		}
		return realNodeLevel;
	}

	public ServiceNodeDefine getServiceNodeDefine() {
		return serviceNodeDefine;
	}

	public void setServiceNodeDefine(ServiceNodeDefine serviceDefine) {
		this.serviceNodeDefine = serviceDefine;
	}

	public RealNode getPreRealNode() {
		return (RealNode) this.preNode;
	}
	
	public java.lang.String toString() {
		int flowNodeLevel = this.getNode1XPreLevel();
		java.lang.String leftPad = StringUtils.leftPad(" ", flowNodeLevel * 4);
		StringBuffer buf = new StringBuffer();
		buf.append("\r\n").append(leftPad).append(super.toString());
		buf.append("\r\n").append(leftPad).append("context = {").append(getRealNodeContext().getNextNodesMap());
		buf.append("\r\n").append(leftPad).append("service : ").append(getServiceNodeDefine().getNextNodesMap());
		buf.append("\r\n").append(leftPad).append("next = {").append(this.getNextNodesMap().toString()).append("]");
		return buf.toString();
	}
}
