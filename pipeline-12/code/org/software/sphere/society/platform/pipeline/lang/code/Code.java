package org.software.sphere.society.platform.pipeline.lang.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.software.region.molecule.container.net.nextable.Nextable;
import org.software.region.molecule.container.net.nodable.Nodable;
import org.software.sphere.society.platform.pipeline.common.Namable;

public class Code implements Nodable, Nextable, Namable {
	
	/**
	 * 添加后面的节点
	 */
	protected Map nextNodeMap = new HashMap();
	
	protected List nextNodeList = new ArrayList();
	
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	public void addNextNodeInList(Nodable node) {
		nextNodeList.add(node);
	}

	public void addNextNodeInMap(String name, Nodable node) {
		nextNodeMap.put(name, node);
	}

	public void addNextNode(Nodable node) {
		addNextNodeInList(node);
	}
}
