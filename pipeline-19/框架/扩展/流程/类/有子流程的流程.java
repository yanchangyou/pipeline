package 扩展.流程.类;

import 异常.异常;
import 扩展.流程.接口.有子流程的;
import 核心.接口.可流程化的;
import 核心.类.实体;
import 基本.类.会话;
import 基本.类.流程;
import 通用.类.乾坤包;

public class 有子流程的流程 extends 流程 implements 有子流程的 {
	
	protected 乾坤包 乾坤包 = new 乾坤包();

	public 可流程化的 删除子流程(可流程化的 流程) {
		return (可流程化的)乾坤包.拿出(流程.获取名字());
	}

	public void 添加子流程(可流程化的 流程) {
		this.乾坤包.放入(流程);
	}

	public void 执行(实体 实体, 会话 会话) throws 异常{
		有子流程的流程的缺省执行(this, 实体, 会话);
	}
	
	public static void 有子流程的流程的缺省执行(有子流程的流程 有子流程的流程, 实体 实体, 会话 会话)  throws 异常{
		乾坤包 乾坤包 = 有子流程的流程.乾坤包;
		for (int i=0; i<乾坤包.获取下一个编号().intValue(); i++) {
			可流程化的 流程 = (可流程化的) 乾坤包.查看(new Integer(i));
			流程.执行(实体, 会话);
		}
	}
	

	public 可流程化的 获取子流程(String 子流程名字) {
		return (可流程化的)乾坤包.拿出(子流程名字);
	}
	
	public String toString() {
		return super.toString() + ", 乾坤包 : \n" + this.乾坤包.toString().replace(", ", "\r\n");
	}
}
