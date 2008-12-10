package org.software.sphere.society.platform.omega.core.flow.thread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;

import org.software.sphere.society.platform.omega.common.RuleReadNetDataByOmega;
import org.software.sphere.society.platform.omega.core.execute.Request;
import org.software.sphere.society.platform.omega.core.execute.Response;
import org.software.sphere.society.platform.omega.core.execute.Session;
import org.software.sphere.society.platform.omega.core.flow.Flow;

public class Thread extends Flow {

	private java.lang.String port;

	public java.lang.String getPort() {
		return port;
	}

	public void setPort(java.lang.String port) {
		this.port = port;
	}

	public void execute(Session clientSession) throws ConnectException,
			Exception {
		start();//开启线程服务
	}
	
	/**
	 * service 对外的三个核心方法, start, stop, service
	 * 
	 * 首先通过 strat, 对外启动监听, 开始接收请求 然后通过 service, 对每个请求进行服务 最后通过 stop, 关闭服务
	 */
	private ServerSocket server;
	/**
	 * 
	 */
	public void start() {
		
		try {
			int port = this.getIntPort();
			server = new ServerSocket(port);
			// 创建一个ServerSocket在端口监听客户请求
		} catch (Throwable e) {
			log.error("创建服务失败, 可能是端口被占用, 当前使用的端口是:" + port);
			log.error("内部错误信息:" + e);
			// 出错，打印出错信息
		}
		try {
			do {
				Socket socket = server.accept();
				
				log.info(socket.getRemoteSocketAddress().toString()
						.substring(1)+ " 请求服务 ");
				
				new ServiceThread(socket, this).start();
				// 使用accept()阻塞等待客户请求，有客户
				// 请求到来则产生一个Socket对象，并继续执行
			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("启动服务失败, 可能是端口被占用, 当前使用的端口是:" +port);
			log.error("内部错误信息:" + e);
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
			thread.service(thread, socket);
			log.info("[" + Thread.this.getName() + "]服务结束");
		}

	}

	public void service(Thread thread, Socket socket) {
		try {

			log.info("服务[" + this.getName() + "]建立socket");
			Request request = thread.getRequest();
			Response response = thread.getResponse();
//			Context context = new Context();

			Session clientSession = new Session(request, response, socket);
			/**
			 * 读取数据 采用&续行, && 换行的处理规则
			 */
			if (request != null) { //接收客户端请求
				log.info("服务[" + this.getName() + "]接收客户端输入数据");
				String requestData = RuleReadNetDataByOmega.readData(socket);
//				Tree requestTree = OmegaNetCompiler.compile(requestData);
//				requestTree.execute(this.getContext());
//				this.getContext().merge(this.request.getName(), requestTree);
				System.out.println("请求数据是 : " + requestData);
				log.info("服务[" + this.getName() + "]客户端输入数据完毕");
			}
			
//			Flow flow = service.getFlow();

//			flow.setRoot(service.getRoot());
//			flow.setParentContext(context);

			// TODO CHECH THIS
//			this.addChildElement(flow);
			
			log.info("服务[" + this.getName() + "]开始处理");
			
//			flow.deal(clientSession);

			for (Iterator iter = flowList.iterator(); iter.hasNext();) {
				Flow flow = (Flow) iter.next();
				flow.execute(clientSession);
			}
			
			log.info("服务[" + this.getName() + "]完成处理");
			
			if (response != null) { //响应客户端请求
				log.info("服务[" + this.getName() + "]向客户端输出结果");
				PrintWriter os = new PrintWriter(socket.getOutputStream());
				String responseData = response.getResponseData();
//				Tree responseTree = OmegaNetCompiler.compile(responseData);
//				responseTree.execute(this.getContext());
//				this.getContext().merge(response.getName(), responseTree, DefaultTree3D.class);
//				this.getContext().getParent().merger(response.getName(), responseTree);
//				os.print(responseTree.getResult());
				os.print(responseData);
				os.flush();
				log.info("服务[" + this.getName() + "]向客户端输出结果完毕");
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
		if (server == null) {
			throw new Exception("错误:服务没有启动, 不用结束");
		} else {
			server.close();
		}
	}

}
