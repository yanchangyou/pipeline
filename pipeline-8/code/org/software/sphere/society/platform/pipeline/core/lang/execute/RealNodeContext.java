package org.software.sphere.society.platform.pipeline.core.lang.execute;

import org.software.sphere.society.platform.pipeline.common.Commons;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.core.data.node1X.DefaultNode1X;
import org.software.sphere.society.platform.pipeline.core.real.RealNode;

/**
 * ������<br>
 * Ŀ�� 1, Ԫ�ر�����ǰ������<br>
 * Ŀ�� 2, �����洢<br>
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-2 ����09:30:51
 * @file : RealNodeContext.java
 * @version : 0.1
 */
public class RealNodeContext extends DefaultNode1X {

	public RealNodeContext(RealNode thisRealNode) {
		super();
		this.preNode = thisRealNode;
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
	public RealNode getSuitablePathRealNode(String path) {
		RealNode realNode = null;
		
		if (path.toJavaString().startsWith(KeyWords.THIS_KEY_WORLD + ".")) {
			realNode = getRelativePathRealNode(new String(path.toJavaString().substring(KeyWords.THIS_KEY_WORLD.toJavaString().length() + 1)));
		} else if (path.toJavaString().startsWith(KeyWords.ROOT_KEY_WORLD + ".")) {
			realNode = getAbsolutePathRealNode(new String(path.toJavaString().substring(KeyWords.ROOT_KEY_WORLD.toJavaString().length() + 1)));
		} else {
			realNode = getRelativePathRealNode(path);
		}
		return realNode;
	}
	
	/**
	 * ��ȡ�ʺϽڵ��������
	 * @param path
	 * @return
	 */
	public RealNodeContext getSuitablePathRealNodeContext(String path) {
		return getSuitablePathRealNode(path).getRealNodeContext();
	}

	/**
	 * ��ȡ���·���ڵ�
	 * 
	 * @param path
	 * @return
	 */
	public RealNode getRelativePathRealNode(String path) {
		RealNode realNode = this.getThisRealNode();

		String[] pathArray = Commons.convertToStringArray(path.toJavaString().split("\\."));

		for (int i = 0; i < pathArray.length; i++) {
			if (pathArray[i].equals(KeyWords.SUPER_KEY_WORLD)) {
				realNode = realNode.getPreRealNode();
			} else {
				realNode = realNode.getNextRealNode(pathArray[i]);
			}
		}
		return realNode;
	}
	
	/**
	 * ��ȡ���·���ڵ��������
	 * 
	 * @param path
	 * @return
	 */
	public RealNodeContext getRelativePathRealNodeContext(String path) {
		return getRelativePathRealNode(path).getRealNodeContext();
	}
	/**
	 * ��ȡ����·���Ľڵ�
	 * 
	 * @param path
	 * @return
	 */
	public RealNode getAbsolutePathRealNode(String path) {
		RealNode realNode = this.getGlobalNode();
		String[] pathArray = Commons.convertToStringArray(path.toJavaString().split("\\."));
		for (int i = 0; i < pathArray.length; i++) {
			realNode = realNode.getNextRealNode(pathArray[i]);
		}
		return realNode;
	}
	
	/**
	 * ��ȡ����·���ڵ��������
	 * 
	 * @param path
	 * @return
	 */
	public RealNodeContext getAbsolutePathRealNodeContext(String path) {
		return getAbsolutePathRealNode(path).getRealNodeContext();
	}
		
	/**
	 * ��ȡ�������ĵĽڵ�
	 * 
	 * @return
	 */
	public RealNode getThisRealNode() {
		return (RealNode) this.preNode;
	}

	/**
	 * ��ȡGlobal�ڵ�<br>
	 * 
	 * ���ϵ���ǰ����
	 * 
	 * @return
	 */
	public RealNode getGlobalNode() {
		RealNode realNode = this.getThisRealNode();
		while (realNode.getPreNode() != null) {
			realNode = realNode.getPreRealNode();
		}
		return ((Root)(Object)realNode).getGlobal();
	}
	
	/**
	 * Global��������
	 * @return
	 */
	public RealNodeContext getGlobalNodeContext() {
		return getGlobalNode().getRealNodeContext();
	}
	
	public java.lang.String toString() {
		return this.getNextNodesMap().toString();
	}
}
