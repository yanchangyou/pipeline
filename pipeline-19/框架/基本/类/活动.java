package 基本.类;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import 异常.异常;
import 核心.接口.可活动化的;
import 核心.接口.可流程化的;
import 核心.类.实体;
import 通用.类.乾坤包;

public class 活动 extends 基本的实体 implements 可活动化的 {

	protected 可流程化的 流程;
	
	protected 实体 实体;

	protected int 端口;

	protected 乾坤包 乾坤包 = new 乾坤包();
	
	public int get端口() {
		return 端口;
	}

	public void set端口(int 端口) {
		this.端口 = 端口;
	}

	public void 举办(实体 实体, 会话 会话) throws 异常{

		
	}
	private ServerSocket server;
	
	public void 去接待(实体 实体) throws 异常{
		this.实体 = 实体;
		this.去接待(this.端口);
	}
	
	public void 去接待(int 端口) throws 异常{
		try {
			server = new ServerSocket(端口);
			System.out.println("在[" + 端口 + "]端口");
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
				
				new Thread(new 会话(this, socket)).start();
				// 使用accept()阻塞等待客户请求，有客户
				// 请求到来则产生一个Socket对象，并继续执行
			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error." + e);
			// 出错，打印出错信息
		}

	}

	public void 去欢送(实体 实体) throws 异常{
		try {
			this.server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("活动结束!");
	}

	public String 获取活动状态() {
		return this.获取状态();
	}

	public 可流程化的 获取流程() {
		return this.get流程();
	}

	public void 设置流程(可流程化的 流程) {
		this.set流程(流程);
	}

	public void 举办(会话 会话) throws 异常{
		this.获取流程().执行(实体, 会话);
	}

	public 实体 获取实体() {
		return 实体;
	}

	public void 设置实体(实体 实体) {
		this.实体 = 实体;
	}

	public ServerSocket getServer() {
		return server;
	}

	public void setServer(ServerSocket server) {
		this.server = server;
	}

	public 可流程化的 get流程() {
		return 流程;
	}

	public void set流程(可流程化的 流程) {
		this.流程 = 流程;
	}

	public 乾坤包 get乾坤包() {
		return 乾坤包;
	}

	public void set乾坤包(乾坤包 乾坤包) {
		this.乾坤包 = 乾坤包;
	}

	public 实体 get实体() {
		return 实体;
	}

	public void set实体(实体 实体) {
		this.实体 = 实体;
	}
	
	public String toString() {
		return super.toString() + ", 端口 : " + 端口 + ", 流程 : " + 流程;
	}
}
