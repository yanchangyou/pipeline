package 软件.开源.全球.自由实验室.神;

/**
 * 名称限制 不能是数字
 * 
 * @author yanchangyou@gmail.com
 * @date : 2009-1-6 下午11:23:19
 * @file : 名称.java
 * @version : 0.1
 */
public class 名称 implements 有名称的 {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void 设置名称(String 名称) {
		this.setName(名称);
	}
	
	public String 获取名称() {
		return this.getName();
	}
}
