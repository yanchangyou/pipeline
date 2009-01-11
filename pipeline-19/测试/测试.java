import 基本.类.系统;


public class 测试 {

	public static void main(String[] args) throws Exception {
		系统();
	}
	
	public static void 系统() throws Exception {

		系统 系统 = (系统) Class.forName("第一个应用实体").newInstance();
	
		系统.构建();
		
		System.out.println(系统);
		
		系统.启动();
	}
}
