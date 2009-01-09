package 核心.类;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.xml.sax.SAXException;

import 核心.接口.可流程化的;
import 核心.节点.乾坤包节点;


public class 服务 extends 乾坤包节点 {

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
	private ServerSocket server;
	
	public void 开启(系统 系统, int 端口) {
		if (端口 == 0) {
			端口 = this.端口;
		}
		
		try {
			server = new ServerSocket(端口);
			// 创建一个ServerSocket在端口监听客户请求
		} catch (Exception e) {
			System.out.println("can not listen to:" + e);
			// 出错，打印出错信息
		}
		try {
			do {
				Socket socket = server.accept();
				
				System.out.println(socket.getRemoteSocketAddress().toString()
						.substring(1)+ " 请求服务 ");
				
				new ServiceThread(系统, socket, this).start();
				// 使用accept()阻塞等待客户请求，有客户
				// 请求到来则产生一个Socket对象，并继续执行
			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error." + e);
			// 出错，打印出错信息
		}
	
	}

	private class ServiceThread extends Thread {

		Socket socket;

		服务 服务;
		
		系统 系统;
		
		public ServiceThread(系统 系统, Socket socket, 服务 服务) {
			this.socket = socket;
			this.服务 = 服务;
			this.系统 = 系统;
		}

		public void run() {
			System.out.println("本次服务开始");
			服务.运行(this.系统, socket);
			System.out.println("本次服务结束");
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public void 关闭(int 端口) {
			
	}
	
	public void 运行(系统 系统) {
		运行(系统, null);
	}
	
	public void 运行(系统 系统, Socket socket) {
		for (int i = 0; i < this.下一个编号.intValue(); i++) {
			可流程化的 流程 = 获取流程(new Integer(i));
			流程.执行(系统, socket);
		}
	}
	

	public void 编排流程(可流程化的 流程) {
		this.放入(流程);
	}
	
	public 可流程化的 获取流程(Integer 流程的ID) {
		return (可流程化的) this.查看(流程的ID);
	}
	
	public 可流程化的 获取流程(String 流程的名称) {
		return (可流程化的) this.查看(流程的名称);
	}
	
}
