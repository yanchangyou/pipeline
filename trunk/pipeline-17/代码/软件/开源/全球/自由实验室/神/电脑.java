package ���.��Դ.ȫ��.����ʵ����.��;

import java.util.HashMap;
import java.util.Map;

public class ���� extends ���� {

	Map Ǭ���� = new HashMap();
	
	����ϵͳ ����;
	
	private int count = 0;
	
	public void ��װϵͳ(ϵͳ ϵͳ) {
		Ǭ����.put(ϵͳ.��ȡ����(), ϵͳ);
		Ǭ����.put("" + count , ϵͳ);	
		count ++;
	}
	
	public ���� ��ȡϵͳ(int ϵͳID) {
		return (����) Ǭ����.get("" + ϵͳID);
	}
	
	public ���� ��ȡϵͳ(String ϵͳ����) {
		return (����) Ǭ����.get(ϵͳ����);
	}
	
	public void ��������(����ϵͳ ����) {
		this.���� = ����;
	}
	
	public void ����() {
		����.����();
	}
	
}
