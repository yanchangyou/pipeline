package 核心.类;


import java.io.IOException;

import org.xml.sax.SAXException;

import 核心.接口.可流程化的;
import 核心.节点.乾坤包节点;


public abstract class 流程 extends 乾坤包节点 implements 可流程化的 {

	public 流程() throws IOException, SAXException {
		super();
	}

	public void 添加流程(可流程化的 流程) {
		this.放入(流程);
	}
	
	public 可流程化的 获取流程(Integer 流程的ID) {
		return (可流程化的) this.查看(流程的ID);
	}
	
	public 可流程化的 获取流程(String 流程的名称) {
		return (可流程化的) this.查看(流程的名称);
	}
}
