package org.software.compont.atom.test.flow;

public class TestGYS {

	/**
	 * 求两个数的公约数:  辗转相除法---公约数通用解法----百度找到的 
	 * 注意,把大的放前面, 可以少计算一次
	 * @param bigger
	 * @param smaller
	 * @return
	 */
	public static int get_gys_2(int bigger, int smaller) {
		
		System.out.println("两数的辗转(" + bigger + ", " + smaller + ")"); //过程查看
		if (smaller == 0) {
			System.out.println("两数的辗转结束, 结果是:" + bigger);
			return bigger;
		}
		return get_gys_2(smaller, bigger % smaller);
	}
	
	/**
	 * 求n个数据的公约数 --- 根据版主的想法产生的
	 * @param array
	 * @param index
	 * @return
	 */
	public static int get_gys_n(int[] array, int index) {
		
		if (array == null || index > array.length-1 || index < 0) {
			throw new RuntimeException("数组为空, 或越界了");
		}
		
		if (index == 0) {
			return array[index];
		}
		int bigger = get_gys_n(array, index-1);
		int smaller = array[index];
		
		System.out.println("数组中前" + (index-1) + "个元素的最大公约数: " + bigger);
		
		if (bigger < smaller) { //顺序交换, 大的放在前面
			int temp = 0;
			temp = bigger;
			bigger = smaller;
			smaller = temp;
		}
		return get_gys_2(bigger, smaller);
	}
	
	public static void main(String[] args) {
		int array[] = { 6, 12, 48, 42, 15 }; // 将进行公约数求解的5个数
		System.out.println("结果是 : " + get_gys_n(array, array.length-1));
	}
}
