package org.software.sphere.society.platform.pipeline.core.flow.collaborate;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.software.sphere.society.platform.pipeline.common.RuleReadNetDataByPipeline;
import org.software.sphere.society.platform.pipeline.core.core.Request;
import org.software.sphere.society.platform.pipeline.core.core.Response;
import org.software.sphere.society.platform.pipeline.core.core.Session;
import org.software.sphere.society.platform.pipeline.core.flow.FlowNode;

public class Collaborate extends FlowNode {

	private java.lang.String port;

	public Map portServerSocketMap = new HashMap();
	
	public Map portSocketListMap = new HashMap();
	
	public java.lang.String getPort() {
		return port;
	}

	public void setPort(java.lang.String port) {
		this.port = port;
	}

	public void execute(Session clientSession) throws ConnectException,
			Exception {
		start(this.getIntPort());// 开启线程服务
	}

	/**
	 * service 对外的三个核心方法, start, stop, service
	 * 
	 * 首先通过 strat, 对外启动监听, 开始接收请求 然后通过 service, 对每个请求进行服务 最后通过 stop, 关闭服务
	 */

	/**
	 * @throws Exception 
	 * 
	 */
	public void start(int port) throws Exception {
		ServerSocket server = null;
		try {
			 server = new ServerSocket(port);
			portServerSocketMap.put("" + port, server);
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

		Collaborate thread;

		public ServiceThread(Socket socket, Collaborate thread) {
			this.socket = socket;
			this.thread = thread;
		}

		public void run() {
			log.info("[" + Collaborate.this.getName() + "]服务开始");
			thread.service(socket);
			log.info("[" + Collaborate.this.getName() + "]服务结束");
		}
	}

	public void service(Socket socket) {
		try {

			log.info("服务[" + this.getName() + "]建立socket");
			Request request = null;//this.getRequest();
			Response response = null;//this.getResponse();

			String command = null;
//			String responseData = null;

			PrintWriter os = new PrintWriter(socket.getOutputStream());

			StringBuffer welcomeMsg = new StringBuffer();
			welcomeMsg.append("================欢迎来到合作工作室================")
					.append("\r\n");
			welcomeMsg.append("     你可以和你的同事一起执行以下操作, 注意不要打架哦1, 可以舍己为人的").append("\r\n");
			welcomeMsg.append("     1, [start at port]-----在指定的端口[port](数字)开启这项服务").append("\r\n");
			welcomeMsg.append("     2, [run at port]-----执行指定的端口[port](数字)的此项服务").append("\r\n");
			welcomeMsg.append("     3, [stop at port]-----停止指定端口[port](数字)的此项服务").append("\r\n");
			welcomeMsg.append("   某处可以[开启],[执行],[停止]其它端口处的这项服务, 它都是平等的, 可以互相操作的").append("\r\n");
			welcomeMsg.append("   一旦你开启了某端口处的服务, 那么你就必须等待你开的端口执行完成后才能继续操作").append("\r\n");
			welcomeMsg.append("=============================================")
					.append("\r\n");
			os.print(welcomeMsg);
			os.flush();
			while (!socket.isClosed()) {
				os.print("请输入请求命令>");
				os.flush();

				command = RuleReadNetDataByPipeline.readData(socket.getInputStream());

				if (command == null) {
					log.error(" 违法输入, 服务停止");
					break;
				}
				log.info("服务[" + this.getName() + "]开始处理");

				Session clientSession = new Session(request, response, socket);
				log.info("开始执行命令:" + command);
				String msg = "";
				try {

					executeCommand(command, clientSession);
					msg = "执行[" + command + "]命令成功";
					log.info("开始执行命令" + command + "成功");
				} catch (Exception e) {
					msg = "执行[" + command + "]命令失败, 失败的原因是 : "
							+ e.getMessage();
					msg = msg + "\r\n\r\n\r\n" + welcomeMsg;
					log.info("开始执行命令" + command + "失败");
					e.printStackTrace();
//					System.out.println("无效命令:" + command);
//					System.in.read();
				}

				os.print(msg + "\r\n");
				os.flush();
				log.info("服务[" + this.getName() + "]向客户端输出结果完毕");
			}
		} catch (ConnectException e) {
			log.error("获取[" + this.getName() + "]服务失败---原因" + e.getMessage());
		}
		// catch (DataNotFoundException e) {
		// log.error("处理[" + this.getName() + "]服务失败---原因是 :" + e.getMessage());
		// // e.printStackTrace();
		// }
		catch (IOException e) {
			log.error("处理[" + this.getName() + "]服务失败---io异常");
			e.printStackTrace();
		} catch (Exception e) {
			log
					.error("处理[" + this.getName() + "]服务失败---错误信息:"
							+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				log.error("关闭[" + this.getName() + "]服务失败---错误信息:"
						+ e.getMessage());
				// e.printStackTrace();
			} // 关闭Socket
		}
	}

	public void stop(int port) throws Exception {
		ServerSocket server = (ServerSocket) portServerSocketMap.get("" + port);
		List socketList = (List) portSocketListMap.get("" + port);
		if (server == null) {
			throw new Exception("错误:服务没有启动, 不用结束");
		} else {
			server.close();
			for (Iterator iter = socketList.iterator(); iter.hasNext();) {
				Socket socket = (Socket) iter.next();
				socket.close();
			}
		}
	}

	public void run(Session clientSession, int port) throws ConnectException,
			Exception {
		for (Iterator iter = flowList.iterator(); iter.hasNext();) {
			FlowNode flow = (FlowNode) iter.next();
			flow.execute(clientSession);
		}
	}

	public void executeCommand(String command, Session clientSession)
			throws Exception {

		if (command == null || !command.matches("^\\w+\\s+at\\s+\\d{1,5}")) {
			throw new Exception("无效命令 :" + command);
		}
		
		String[] token = command.split("\\s+at\\s+");

		String action = token[0];
		int port = Integer.parseInt(token[1]);

		if (action.equals("start")) {
			start(port);
		} else if (action.equals("stop")) {
			stop(port);
//			clientSession.getClientSocket().close();
		} else if (action.equals("run")) {
			run(clientSession, port);
		} else {
//			System.out.println("无效命令:" + command);
//			System.in.read();
			throw new Exception("无效命令" + command);
		}
	}

}
