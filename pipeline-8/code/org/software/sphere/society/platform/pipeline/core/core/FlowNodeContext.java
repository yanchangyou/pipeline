package org.software.sphere.society.platform.pipeline.core.core;

import org.software.sphere.society.platform.pipeline.common.Commons;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.core.data.node1X.DefaultNode1X;
import org.software.sphere.society.platform.pipeline.core.flow.FlowNode;

/**
 * ������<br>
 * Ŀ�� 1, Ԫ�ر�����ǰ������<br>
 * Ŀ�� 2, ���ݽڵ�洢<br>
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-2 ����09:30:51
 * @file : FlowNodeContext.java
 * @version : 0.1
 */
public class FlowNodeContext extends DefaultNode1X {

	public FlowNodeContext(FlowNode thisFlowNode) {
		super();
		this.preNode = thisFlowNode;
	}
	
	/**
	 * ��ȡ���ʵ������Ľڵ�---���ļ�·��������ͬ<br>
	 * 
	 * ���ֹ��� :<br>
	 * 1, ��[this]�ؼ��ֿ�ͷ��ʹ�����·��,  this��java��һ��, this������Ը� super, '
	 *     ���� this.super.super��ʾ���ϲ�������, ������ļ�·���Ĺ���һ��<br>
	 * 2, ��[root]�ؼ��ֿ�ͷ��ʹ�þ���·��<br>
	 * 3, ��[super]�ؼ��ֿ�ͷ һ��super������ǰһ�� 
	 * 4, �����������ؼ��ֿ�ͷ��ͳһʹ�� ���·��, ������ļ��ļ�����Ӧ
	 * 
	 * @param path
	 * @return
	 */
	public FlowNode getSuitablePathFlowNode(String path) {
		FlowNode flowNode = null;
		
		if (path.toJavaString().startsWith(KeyWords.THIS_KEY_WORLD + ".")) {
			flowNode = getRelativePathFlowNode(new String(path.toJavaString().substring(KeyWords.THIS_KEY_WORLD.toJavaString().length() + 1)));
		} else if (path.toJavaString().startsWith(KeyWords.ROOT_KEY_WORLD + ".")) {
			flowNode = getAbsolutePathFlowNode(new String(path.toJavaString().substring(KeyWords.ROOT_KEY_WORLD.toJavaString().length() + 1)));
		} else {
			flowNode = getRelativePathFlowNode(path);
		}
		return flowNode;
	}
	
	/**
	 * ��ȡ�ʺϽڵ��������
	 * @param path
	 * @return
	 */
	public FlowNodeContext getSuitablePathFlowNodeContext(String path) {
		return getSuitablePathFlowNode(path).getFlowNodeContext();
	}

	/**
	 * ��ȡ���·���ڵ�
	 * 
	 * @param path
	 * @return
	 */
	public FlowNode getRelativePathFlowNode(String path) {
		FlowNode flowNode = this.getThisFlowNode();

		String[] pathArray = Commons.convertToStringArray(path.toJavaString().split("\\."));

		for (int i = 0; i < pathArray.length; i++) {
			if (pathArray[i].equals(KeyWords.SUPER_KEY_WORLD)) {
				flowNode = flowNode.getPreFlowNode();
			} else {
				flowNode = flowNode.getNextFlowNode(pathArray[i]);
			}
		}
		return flowNode;
	}
	
	/**
	 * ��ȡ���·���ڵ��������
	 * 
	 * @param path
	 * @return
	 */
	public FlowNodeContext getRelativePathFlowNodeContext(String path) {
		return getRelativePathFlowNode(path).getFlowNodeContext();
	}
	/**
	 * ��ȡ����·���Ľڵ�
	 * 
	 * @param path
	 * @return
	 */
	public FlowNode getAbsolutePathFlowNode(String path) {
		FlowNode flowNode = this.getSelfFlowNode();
		String[] pathArray = Commons.convertToStringArray(path.toJavaString().split("\\."));
		for (int i = 0; i < pathArray.length; i++) {
			flowNode = flowNode.getNextFlowNode(pathArray[i]);
		}
		return flowNode;
	}
	
	/**
	 * ��ȡ����·���ڵ��������
	 * 
	 * @param path
	 * @return
	 */
	public FlowNodeContext getAbsolutePathFlowNodeContext(String path) {
		return getAbsolutePathFlowNode(path).getFlowNodeContext();
	}
		
	/**
	 * ��ȡ�������ĵĽڵ�
	 * 
	 * @return
	 */
	public FlowNode getThisFlowNode() {
		return (FlowNode) this.preNode;
	}

	/**
	 * ��ȡGlobal�ڵ�<br>
	 * 
	 * ���ϵ���ǰ����
	 * 
	 * @return
	 */
	public FlowNode getSelfFlowNode() {
		FlowNode flowNode = this.getThisFlowNode();
		while (flowNode.getPreNode() != null) {
			flowNode = flowNode.getPreFlowNode();
		}
		return flowNode;
	}
	
	/**
	 * Global��������
	 * @return
	 */
	public FlowNodeContext getSelfFlowNodeContext() {
		return getSelfFlowNode().getFlowNodeContext();
	}
	
	public java.lang.String toString() {
		return super.toString() + "" + this.getNextNodesMap().toString();
	}
}