package ���.��Դ.ȫ��.����ʵ����.��;

import java.util.HashMap;
import java.util.Map;

public class ����ϵͳ {
	
	Map Ǭ���� = new HashMap();
	
	private int count = 0;
	
	public void ���ϵͳ(ϵͳ ϵͳ) {
		Ǭ����.put(ϵͳ.��ȡ����(), ϵͳ);
		Ǭ����.put("" + count , ϵͳ);	
		count ++;
	}
	
	public ϵͳ ��ȡϵͳ(int ϵͳID) {
		return (ϵͳ) Ǭ����.get("" + ϵͳID);
	}
	
	public ϵͳ ��ȡϵͳ(String ϵͳ����) {
		return (ϵͳ) Ǭ����.get(ϵͳ����);
	}

	public void ����() {
		for (int i = 0; i < count; i++) {
			ϵͳ ϵͳ = (ϵͳ) Ǭ����.get("" + i);
			ϵͳ.������������();
		}
	}
}
