package org.software.sphere.society.platform.pipeline.core.data.pre;

import org.software.sphere.society.platform.pipeline.core.data.Node;
import org.software.sphere.society.platform.pipeline.core.data.length.Length1able;

public interface Pre1able extends Preable, Length1able {

	/**
	 * ��ȡ��ǰ��Ľڵ�, ��Ϊ�����͵�ǰ���ڵ����ҽ���һ��, ���п��Ի�ȡ��������ǰ��Ľڵ�
	 * @return
	 */
	public Node getFirstNodeInSequencePre1ableNodes();
	
	public Node getPreNode();
	
}
