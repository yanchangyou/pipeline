package org.software.sphere.society.platform.omega.core.data.pre;

import org.software.sphere.society.platform.omega.core.data.Node;
import org.software.sphere.society.platform.omega.core.data.length.Length1able;

public interface Pre1able extends Preable, Length1able {

	/**
	 * 获取最前面的节点, 因为此类型的前驱节点有且仅有一个, 所有可以获取到它的最前面的节点
	 * @return
	 */
	public Node getFirstNodeInSequencePre1ableNodes();
	
	public Node getPreNode();
	
}
