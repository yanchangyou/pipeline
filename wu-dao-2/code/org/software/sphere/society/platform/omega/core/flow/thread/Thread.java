package org.software.sphere.society.platform.omega.core.flow.thread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.software.sphere.society.platform.omega.common.RuleReadNetDataByOmega;
import org.software.sphere.society.platform.omega.core.data.Node;
import org.software.sphere.society.platform.omega.core.data.node0X.String;
import org.software.sphere.society.platform.omega.core.execute.Request;
import org.software.sphere.society.platform.omega.core.execute.Response;
import org.software.sphere.society.platform.omega.core.execute.Session;
import org.software.sphere.society.platform.omega.core.flow.FlowNode;

public class Thread extends FlowNode {

	private java.lang.String times;
	
	private java.lang.String port;

	public Map portSocketListMap = new HashMap();
	
	public java.lang.String getPort() {
		return port;
	}

	public void setPort(java.lang.String port) {
		this.port = port;
	}

	public void execute(Session clientSession) throws ConnectException,
			Exception {
		start();// 开启线程服务
	}

	/**
	 * service 对外的三个核心方法, start, stop, service
	 * 
	 * 首先通过 strat, 对外启动监听, 开始接收请求 然后通过 service, 对每个请求进行服务 最后通过 stop, 关闭服务
	 */

	ServerSocket server = null;
	/**
	 * @throws Exception 
	 * 
	 */
	public void start() throws Exception {
		try {
			 server = new ServerSocket(Integer.parseInt(port));
			log.info("在[" + port + "]端口处开启服务, 等待请求");
			// 创建一个ServerSocket在端口监听客户请求
		} catch (Throwable e) {
			log.error("创建服务失败, 可能是端口被占用, 当前使用的端口是:" + port);
			log.error("内部错误信息:" + e);
			throw new Exception("内部错误信息:" + e.getMessage());
			// 出错，打印出错信息
		}
		List socketList = new ArrayList();
		portSocketListMap.put(""+port, socketList);
		try {
			do {
				Socket socket = server.accept();
				socketList.add(socket);
				log.info(socket.getRemoteSocketAddress().toString()
						.substring(1)
						+ " 请求服务 ");

				new ServiceThread(socket, this).start();
				// 使用accept()阻塞等待客户请求，有客户
				// 请求到来则产生一个Socket对象，并继续执行
			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("启动服务失败, 可能是端口被占用, 当前使用的端口是:" + port);
			log.error("内部错误信息:" + e);
			throw new Exception("内部错误信息:" + e.getMessage());
			// 出错，打印出错信息
		}
	}

	protected int getIntPort() {
		return Integer.parseInt(port);
	}

	private class ServiceThread extends java.lang.Thread {

		Socket socket;

		Thread thread;

		public ServiceThread(Socket socket, Thread thread) {
			this.socket = socket;
			this.thread = thread;
		}

		public void run() {
			log.info("[" + Thread.this.getName() + "]服务开始");
			thread.service(socket);
			log.info("[" + Thread.this.getName() + "]服务结束");
		}
	}

	public void service(Socket socket) {
		try {
			int number_times = 0;
			if (times == null || times.trim().equals("")) {
				number_times = 1;
			} else if (times.trim().equalsIgnoreCase("X")) {
				number_times = Integer.MAX_VALUE;
			} else {
				number_times = Integer.parseInt(times);
			}
			for (int i=0; i<number_times; i++){
				log.info("服务[" + this.getName() + "]建立socket");
				Request request = this.getRequest();
				Response response = this.getResponse();
				
				String requestData = null;
				String responseData = null;
				
				
				/**
				 * 读取数据 采用&续行, && 换行的处理规则
				 */
				if (request != null) { //接收客户端请求
					log.info("服务[" + this.getName() + "]接收客户端输入数据");
					requestData = new String(RuleReadNetDataByOmega.readData(socket.getInputStream()));
//					Tree requestTree = OmegaNetCompiler.compile(requestData);
//					requestTree.execute(this.getContext());
//					this.getContext().merge(this.request.getName(), requestTree);
					System.out.println("请求数据是 : " + requestData);
					log.info("服务[" + this.getName() + "]客户端输入数据完毕");
				}
				
				log.info("服务[" + this.getName() + "]开始处理");
				
				log.info("服务[" + this.getName() + "]完成处理");
				
				if (response != null) { //响应客户端请求
					log.info("服务[" + this.getName() + "]向客户端输出结果");
					PrintWriter os = new PrintWriter(socket.getOutputStream());
//					responseData = response.getResponseData();
//					Tree responseTree = OmegaNetCompiler.compile(responseData);
//					responseTree.execute(this.getContext());
//					this.getContext().merge(response.getName(), responseTree, DefaultTree3D.class);
//					this.getContext().getParent().merger(response.getName(), responseTree);
//					os.print(responseTree.getResult());
					Node node = this.getVar(requestData.trim());
					if (node != null) {
						responseData = new String(node.toString());
					} else {
						responseData = new String("变量没有找到");
					}
					
					os.print(responseData + "\r\n");
					os.flush();
					log.info("服务[" + this.getName() + "]向客户端输出结果完毕");
				}
				
			}
			
			socket.close(); // 关闭Socket
			log.info("服务[" + this.getName() + "]关闭socket");
		} catch (ConnectException e) {
			log.error("获取[" + this.getName() + "]服务失败---原因" + e.getMessage());
		} 
//		catch (DataNotFoundException e) {
//			log.error("处理[" + this.getName() + "]服务失败---原因是 :" + e.getMessage());
////			e.printStackTrace();
//		} 
		catch (IOException e) {
			log.error("处理[" + this.getName() + "]服务失败---io异常");
			e.printStackTrace();
		} 
		 catch (Exception e) {
			 log.error("处理[" + this.getName() + "]服务失败---错误信息:" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				log.error("关闭[" + this.getName() + "]服务失败---错误信息:" + e.getMessage());
//				e.printStackTrace();
			} // 关闭Socket
		}
	}

	public void stop() throws Exception {
		this.server.close();
	}

	public java.lang.String getTimes() {
		return times;
	}

	public void setTimes(java.lang.String times) {
		this.times = times;
	}

}
