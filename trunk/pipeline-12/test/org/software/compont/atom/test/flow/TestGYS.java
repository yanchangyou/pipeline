package org.software.compont.atom.test.flow;

public class TestGYS {

	/**
	 * ���������Ĺ�Լ��:  շת�����---��Լ��ͨ�ýⷨ----�ٶ��ҵ��� 
	 * ע��,�Ѵ�ķ�ǰ��, �����ټ���һ��
	 * @param bigger
	 * @param smaller
	 * @return
	 */
	public static int get_gys_2(int bigger, int smaller) {
		
		System.out.println("������շת(" + bigger + ", " + smaller + ")"); //���̲鿴
		if (smaller == 0) {
			System.out.println("������շת����, �����:" + bigger);
			return bigger;
		}
		return get_gys_2(smaller, bigger % smaller);
	}
	
	/**
	 * ��n�����ݵĹ�Լ�� --- ���ݰ������뷨������
	 * @param array
	 * @param index
	 * @return
	 */
	public static int get_gys_n(int[] array, int index) {
		
		if (array == null || index > array.length-1 || index < 0) {
			throw new RuntimeException("����Ϊ��, ��Խ����");
		}
		
		if (index == 0) {
			return array[index];
		}
		int bigger = get_gys_n(array, index-1);
		int smaller = array[index];
		
		System.out.println("������ǰ" + (index-1) + "��Ԫ�ص����Լ��: " + bigger);
		
		if (bigger < smaller) { //˳�򽻻�, ��ķ���ǰ��
			int temp = 0;
			temp = bigger;
			bigger = smaller;
			smaller = temp;
		}
		return get_gys_2(bigger, smaller);
	}
	
	public static void main(String[] args) {
		int array[] = { 6, 12, 48, 42, 15 }; // �����й�Լ������5����
		System.out.println("����� : " + get_gys_n(array, array.length-1));
	}
}
