package org.software.matter.molecule.platform.pipeline.core.context;

import java.util.HashMap;
import java.util.Map;

public class Context {

	protected Context parent;

	private Map tempSpace = new HashMap();

	/**
	 * �����߼�, �Ӹ�������������, ֱ���ҵ������ֵı���, Ҳ�����Ѿ�������, �Ͱ�ֵ��ֵ���� ���û��, ��ֱ�ӷŵ�����������
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
			parent.put(name, obj); // �ҵ���, �ͷ�����Ӧλ��
		} else {
			this.getParent().put(name, obj); // û���ҵ��ͷ��ڸ���������
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
	 * ���δ������ϵ���
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
