package ���.��Դ.ȫ��.����ʵ����.��;

/**
 * �������� ����������
 * 
 * @author yanchangyou@gmail.com
 * @date : 2009-1-6 ����11:23:19
 * @file : ����.java
 * @version : 0.1
 */
public class ���� implements �����Ƶ� {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void ��������(String ����) {
		this.setName(����);
	}
	
	public String ��ȡ����() {
		return this.getName();
	}
}
