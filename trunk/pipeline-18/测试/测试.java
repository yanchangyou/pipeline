import 核心.节点.乾坤包节点;


public class 测试 {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		乾坤包节点 济公 = (乾坤包节点) Class.forName("地神").newInstance();
		
		System.out.println(济公);
	}
}
