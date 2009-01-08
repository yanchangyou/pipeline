package 软件.开源.全球.自由实验室.神;

public class 写 extends 功能 implements 可流程化的 {

	String 内容;
	
	public void 设置内容(String 内容) {
		this.内容 = 内容;
	}
	
	public String 获取内容() {
		return this.内容;
	}
	
	public void 写出(可写的 可写的神) {
		可写的神.写(this.获取内容());
	}

	public void 执行(系统 系统) {
		写出(系统);
	}
}
