import 核心.抽象类.电脑;
import 核心.节点.乾坤包节点;


public class 测试 {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		单点测试();
		太极牌电脑测试();
	}

	public static void 单点测试() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		乾坤包节点 济公 = (乾坤包节点) Class.forName("地神").newInstance();
		
		System.out.println(济公);
	}
	
	public static void 太极牌电脑测试() throws InstantiationException,
		IllegalAccessException, ClassNotFoundException {
		电脑 太极牌电脑 = (电脑) Class.forName("太极牌电脑").newInstance();
	
		System.out.println(太极牌电脑);
	}
}
