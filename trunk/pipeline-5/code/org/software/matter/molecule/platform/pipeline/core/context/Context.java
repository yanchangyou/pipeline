package org.software.matter.molecule.platform.pipeline.core.context;

import java.util.HashMap;
import java.util.Map;

public class Context {
	
	protected Context parent;
	
	private Map tempSpace = new HashMap();
	
	public void put(String name, Object obj) {
		tempSpace.put(name, obj);
	}
	
	public void put(Map map) {
		tempSpace.putAll(map);
	}
	
	public Object get(String name) {
		return tempSpace.get(name);
	}

	public Context getParent() {
		return parent;
	}

	public void setParent(Context parent) {
		this.parent = parent;
	}

	public Map getTempSpace() {
		return tempSpace;
	}

	public void setTempSpace(Map tempSpace) {
		this.tempSpace = tempSpace;
	}
}
