package org.software.sphere.society.platform.omega.core.real;

import org.apache.commons.lang.StringUtils;
import org.software.sphere.society.platform.omega.common.ServiceDefine;
import org.software.sphere.society.platform.omega.core.data.Node;
import org.software.sphere.society.platform.omega.core.data.node0X.String;
import org.software.sphere.society.platform.omega.core.data.node1X.DefaultNode1X;
import org.software.sphere.society.platform.omega.core.execute.KeyWords;
import org.software.sphere.society.platform.omega.core.execute.RealNodeContext;

/**
 * ������ʵ�ڵ�ĸ���<br>
 * 
 * ÿ����ʵ�ڵ����岿�ֹ���<br>
 * 1, ������<br>
 * ���������ڴ洢����, ���ڳ�����ʱ��ȡ<br>
 * 2, ������<br>
 * ���������ڶ��嵱ǰ�ڵ���ӵ�еķ���,����ÿ�������ж���������ṩ�˷���<be> 
 * 3, �����б�<br>
 * ���嵱ǰ�ڵ�ĳ�ʼ����, ���Ҵ�����������, ���б�����ͳһ�洢���������� 
 * 4, ���ڵ�<br>
 * ���ڵ����ڹ������ڵ�, ��������real node �����ϵ��� 
 * 5, �ӽڵ��б�<br>
 * ��������real node �����µ���
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
	 * ���ڵ�
	 */
	protected RealNode parentNode;

	/**
	 * ������
	 */
	protected RealNodeContext realNodeContext;

	/**
	 * ��������
	 */
//	protected VarDefine varDefine;

	/**
	 * ������
	 */
	protected ServiceDefine serviceDefine;

	public RealNode() {
		this.realNodeContext = new RealNodeContext(this);// �Զ�����������˽ڵ��������
	}

	public RealNodeContext getRealNodeContext() {
		return realNodeContext;
	}

	public void setRealNodeContext(RealNodeContext realNodeContext) {
		this.realNodeContext = realNodeContext;
	}
//
//	public VarDefine getVarDefine() {
//		return varDefine;
//	}
//
//	public void setVarDefine(VarDefine varDefine) {
//		this.varDefine = varDefine;
//	}

	public void addVar(Node node) {
		this.realNodeContext.addChildNode(node);
	}

	public Node getVar(String name) {
		return this.realNodeContext.getChildNode(name);
	}

	public RealNode getChildRealNode(String childNodeName) {
		return (RealNode) this.getChildNode(childNodeName);
	}

	/**
	 * ����ӽڵ㲢������parent
	 * 
	 * @param realNode
	 */
	public void addChildRealNode(RealNode realNode) {
		this.addChildNode(realNode);
		realNode.setParentNode(this);
//		realNode.tuneVarToRealNodeContext();
	}
//
//	public void tuneVarToRealNodeContext() {
//		this.varDefine.dealChildNode(new NodeDealer() {
//			public void deal(Node node) {
//				RealNode.this.realNodeContext.addChildNode(node);
//			}
//		});
//	}

	// public

	public java.lang.String toString() {
		int RealNodeLevel = getRealNodeLevel();
		java.lang.String leftPad = StringUtils.leftPad(" ", RealNodeLevel * 4);
		return "\n" + leftPad + super.toString() + " = \n" + leftPad
//				+ "{var = {" + this.getVarDefine() + "}, \n" + leftPad
				+ "{context = {" + this.getRealNodeContext() + "}, \n" + 
				leftPad	+ "{service = {" + this.getServiceDefine() + "}, \n" + 
				leftPad	+ "children = {\n" + 
				leftPad + this.getChildNodesMap()+ "}]\n";
	}

	public int getRealNodeLevel() {
		int realNodeLevel = 0;
		RealNode realNode = this;
		while (realNode.getParentNode() != null) {
			realNodeLevel++;
			realNode = (RealNode) realNode.getParentNode();
		}
		return realNodeLevel;
	}

	public RealNode getSuitablePathRealNode(java.lang.String path) {
		RealNode RealNode = null;
		if (path.startsWith(KeyWords.THIS_KEY_WORLD + ".")) {
			RealNode = getRelativePathRealNode(path
					.substring(KeyWords.THIS_KEY_WORLD.length() + 1));
		} else {
			RealNode = getAbsolutePathRealNode(path);
		}
		return RealNode;
	}

	public RealNode getRelativePathRealNode(java.lang.String path) {
		RealNode RealNode = this;

//		java.lang.String[] pathArray = path.split("\\.");

		// for (int i = 0; i < pathArray.length; i++) {
		// if (pathArray[i].equals(KeyWords.SUPER_KEY_WORLD)) {
		// RealNode = (RealNode) RealNode.getParent();
		// } else {
		// RealNode = (RealNode) RealNode.getChild(pathArray[i]);
		// }
		// }
		return RealNode;
	}

	public RealNode getAbsolutePathRealNode(java.lang.String path) {
		RealNode RealNode = this.getRootRealNode();
		java.lang.String[] pathArray = path.split("\\.");
		for (int i = 0; i < pathArray.length; i++) {
			// RealNode = (RealNode) RealNode.getChild(pathArray[i]);
		}
		return RealNode;
	}

	public RealNode getRootRealNode() {
		RealNode RealNode = this;
		// while (RealNode.getParent() != null) {
		// RealNode = (RealNode) RealNode.getParent();
		// }
		return RealNode;
	}

	public RealNodeContext getRootRealNodeContext() {
		return getRootRealNode().getRealNodeContext();
	}

	public ServiceDefine getServiceDefine() {
		return serviceDefine;
	}

	public void setServiceDefine(ServiceDefine serviceDefine) {
		this.serviceDefine = serviceDefine;
	}

	public RealNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(RealNode parentNode) {
		this.parentNode = parentNode;
	}

}
