package ���.��Դ.ȫ��.����ʵ����.��;

import java.util.HashMap;
import java.util.Map;

public class ���� extends ���� {

	String ��������;
	
	int �˿�;
	public int ��ȡ�˿�() {
		return this.�˿�;
	}
	
	public void ���ö˿�(int �˿�) {
		this.�˿� = �˿�;
	}
	
	public String ��ȡ��������() {
		return this.��������;
	}
	
	public void ������������(String ��������) {
		this.�������� = ��������;
	}
	
	public void ����() {
		
	}
	
	public void �ر�() {
			
	}
	
	public void ����(ϵͳ ϵͳ) {
		for (int i = 0; i < count; i++) {
			�����̻��� ���� = (�����̻���) Ǭ����.get("" + i);
			����.ִ��(ϵͳ);
		}
	}
	
	Map Ǭ���� = new HashMap();
	
	private int count = 0;
	
	public void ��������(�����̻��� ����) {
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
	
	public static class ������������ {
		public static String �������� = "����";
//		String �������� = "��������";
//		String �������� = "��������";
	}
	
}
