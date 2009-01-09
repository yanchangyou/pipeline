package 核心.类;



import java.io.IOException;

import org.xml.sax.SAXException;

import 核心.常量;
import 核心.节点.乾坤包节点;


public class 系统 extends 乾坤包节点 {

	protected int 端口;
	
	protected 功能包 功能包;
	
	protected String 启动类型;
	
	public 功能包 get功能包() {
		return 功能包;
	}

	public void set功能包(功能包 功能包) {
		this.功能包 = 功能包;
	}

	public int get端口() {
		return 端口;
	}

	public void set端口(int 端口) {
		this.端口 = 端口;
	}

	public 系统() throws IOException, SAXException {
		super();
	}

	public void 添加服务(服务 服务) {
		this.放入(服务);
	}
	
	public 服务 获取服务(Integer 服务的ID) {
		return (服务) this.查看(服务的ID);
	}
	
	public 服务 获取服务(String 服务的名称) {
		return (服务) this.查看(服务的名称);
	}

	
	public void 启动主动服务() {
		for (int i = 0; i < this.下一个编号.intValue(); i++) {
			服务 服务 = 获取服务(new Integer(i));
			if (服务.get运行类型().equals(常量.服务运行类型.主动运行)) {
				服务.运行(this);
			}
		}
	}
	
	public String toString() {
		return super.toString() + ",  端口 : " + 端口 ;
	}

	public String get启动类型() {
		return 启动类型;
	}

	public void set启动类型(String 启动类型) {
		this.启动类型 = 启动类型;
	}
}
