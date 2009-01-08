package 核心.抽象类;



import java.io.IOException;

import org.xml.sax.SAXException;

import 核心.接口.可流程化的;
import 神.神;


public abstract class 服务 extends 神 {

	protected String 运行类型;
	
	protected int 端口;
		
	public 服务() throws IOException, SAXException {
		super();
	}
	
	public String get运行类型() {
		return 运行类型;
	}

	public void set运行类型(String 运行类型) {
		this.运行类型 = 运行类型;
	}

	public int get端口() {
		return 端口;
	}

	public void set端口(int 端口) {
		this.端口 = 端口;
	}

	public void 开启() {
		
	}
	
	public void 关闭() {
			
	}
	
	public void 运行(系统 系统) {
		for (int i = 0; i < count; i++) {
			可流程化的 流程 = (可流程化的) 乾坤包.get("" + i);
			流程.执行(系统);
		}
	}
	

	public void 添加流程(可流程化的 流程) {
		this.放入(流程);
	}
	
	public 可流程化的 获取流程(int 流程的ID) {
		return (可流程化的) this.拿出(流程的ID);
	}
	
	public 可流程化的 获取流程(String 流程的名称) {
		return (可流程化的) this.拿出(流程的名称);
	}
	
	
}
