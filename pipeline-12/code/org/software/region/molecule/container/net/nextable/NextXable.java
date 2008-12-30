package org.software.region.molecule.container.net.nextable;

import org.software.region.molecule.container.net.nodable.Nodable;
import org.software.region.molecule.container.net.numberable.NumberXable;

/**
 * ������Ӻ���ڵ��
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-26 ����05:29:31
 * @file : NextXable.java
 * @version : 0.1
 */
public interface NextXable extends Nextable, NumberXable {

	/**
	 * �����һ���ڵ�
	 * @param node
	 */
	public void addNextNode(Nodable node);
}
