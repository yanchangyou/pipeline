package ���.��Դ.ȫ��.����ʵ����.��;

import java.util.HashMap;
import java.util.Map;

public class ϵͳ extends ���� implements ��д�� {
	
	int �˿�;
	
	���ܰ� ���ܰ�;
	
	Map Ǭ���� = new HashMap();
	
	private int count = 0;
	
	public void ��ӷ���(���� ����) {
		Ǭ����.put(����.��ȡ����(), ����);
		Ǭ����.put("" + count , ����);	
		count ++;
	}
	
	public ���� ��ȡ����(int ����ID) {
		return (����) Ǭ����.get("" + ����ID);
	}
	
	public ���� ��ȡ����(String ��������) {
		return (����) Ǭ����.get(��������);
	}
	
	public void ���ù���(���ܰ� ���ܰ�) {
		this.���ܰ� = ���ܰ�;
	}
	
	public ���ܰ� ��ȡ���ܰ�() {
		return this.���ܰ�;
	}
	
	public int ��ȡ�˿�() {
		return this.�˿�;
	}
	
	public void ���ö˿�(int �˿�) {
		this.�˿� = �˿�;
	}

	public void д(String ����) {
		System.out.println(����);
	}
	
	public void ����() {
		
	}
	
	public void ������������() {
		for (int i = 0; i < count; i++) {
			���� ������ = (����) Ǭ����.get("" + i);
			if (������.��ȡ��������().equals(����.������������.��������)) {
				������.����(this);
			}
		}
	}
}
