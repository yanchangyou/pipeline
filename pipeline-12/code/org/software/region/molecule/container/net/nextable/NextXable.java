package org.software.region.molecule.container.net.nextable;

import org.software.region.molecule.container.net.nodable.Nodable;
import org.software.region.molecule.container.net.numberable.NumberXable;

/**
 * 可以添加后面节点的
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-26 下午05:29:31
 * @file : NextXable.java
 * @version : 0.1
 */
public interface NextXable extends Nextable, NumberXable {

	/**
	 * 添加下一个节点
	 * @param node
	 */
	public void addNextNode(Nodable node);
}
