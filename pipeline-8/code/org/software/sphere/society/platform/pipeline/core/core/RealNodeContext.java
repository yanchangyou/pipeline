package org.software.sphere.society.platform.pipeline.core.core;

import org.software.sphere.society.platform.pipeline.common.Commons;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.core.data.node1X.DefaultNode1X;
import org.software.sphere.society.platform.pipeline.core.real.RealNode;
import org.software.sphere.society.platform.pipeline.exception.core.data.NextNodeNotFountException;

/**
 * ������<br>
 * Ŀ�� 1, Ԫ�ر�����ǰ������<br>
 * Ŀ�� 2, ���ݽڵ�洢<br>
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
	 * @throws NextNodeNotFountException 
	 */
	public RealNode getSuitablePathRealNode(String path) throws NextNodeNotFountException {
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
	 * @throws NextNodeNotFountException 
	 */
	public RealNodeContext getSuitablePathRealNodeContext(String path) throws NextNodeNotFountException {
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
	 * @throws NextNodeNotFountException 
	 */
	public RealNode getAbsolutePathRealNode(String path) throws NextNodeNotFountException {
		return (RealNode) this.getNextNodeByPath(path);
	}
	
	/**
	 * ��ȡ����·���ڵ��������
	 * 
	 * @param path
	 * @return
	 * @throws NextNodeNotFountException 
	 */
	public RealNodeContext getAbsolutePathRealNodeContext(String path) throws NextNodeNotFountException {
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
	 * ��ȡRealNode�ڵ�<br>
	 * 
	 * ���ϵ���ǰ����
	 * 
	 * @return
	 */
//	public RealNode getRealNodeNode() {
//		RealNode realNode = this.getThisRealNode();
//		while (realNode.getPreNode() != null) {
//			realNode = realNode.getPreRealNode();
//		}
//		return ((Root)(Object)realNode).getRealNode();
//	}
	
	/**
	 * RealNode��������
	 * @return
	 */
//	public RealNodeContext getRealNodeNodeContext() {
//		return getRealNodeNode().getRealNodeContext();
//	}
	
	public java.lang.String toString() {
		return this.getNextNodesMap().toString();
	}
}