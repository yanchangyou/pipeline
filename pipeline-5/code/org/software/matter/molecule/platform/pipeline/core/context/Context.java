package org.software.matter.molecule.platform.pipeline.core.context;

import java.util.HashMap;
import java.util.Map;

public class Context {

	protected Context parent;

	private Map tempSpace = new HashMap();

	/**
	 * 处理逻辑, 从父上下文向上找, 直到找到了名字的变量, 也就是已经定义了, 就把值赋值给它 如果没有, 就直接放到父上下文中
	 * 
	 * @param name
	 * @param obj
	 */
	public void smartPut(String name, Object obj) {
		Context parent = this.getParent();
		while (parent != null && !parent.contain(name)) {
			parent = parent.getParent();
		}
		if (parent != null) {
			parent.put(name, obj); // 找到了, 就放在相应位置
		} else {
			this.getParent().put(name, obj); // 没有找到就放在父上下文中
		}
	}

	public void put(String name, Object value) {
		this.tempSpace.put(name, value);
	}

	public void put(Map map) {
		tempSpace.putAll(map);
	}

	public boolean contain(String name) {
		return tempSpace.containsKey(name);
	}

	/**
	 * 依次从下往上的找
	 * 
	 * @param name
	 * @return
	 */
	public Object smartGet(String name) {
		Object value = tempSpace.get(name);
		Context context = this;
		while (value == null && context.getParent() != null) {
			context = context.getParent();
			value = context.smartGet(name);
		}
		return value;
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
