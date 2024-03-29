package org.software.sphere.society.platform.pipeline.core.data.pre;

import org.software.sphere.society.platform.pipeline.core.data.DataNode;
import org.software.sphere.society.platform.pipeline.core.data.length.Length1able;

public interface Pre1able extends Preable, Length1able {

	/**
	 * 获取最前面的节点, 因为此类型的前驱节点有且仅有一个, 所有可以获取到它的最前面的节点
	 * @return
	 */
	public DataNode getFirstNodeInSequencePre1ableNodes();
	
	public DataNode getPreNode();
	
}
