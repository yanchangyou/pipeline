package ���.��Դ.ȫ��.����ʵ����.��;

import java.util.HashMap;
import java.util.Map;

public class ���� extends ���� {

	Map Ǭ���� = new HashMap();
	
	private int count = 0;
	
	public void ��ӵ���(���� ����) {
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
	
}
